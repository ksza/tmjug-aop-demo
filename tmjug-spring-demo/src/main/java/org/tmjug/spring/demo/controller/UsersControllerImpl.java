package org.tmjug.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tmjug.spring.demo.exceptions.UserNotFoundException;
import org.tmjug.spring.demo.exceptions.ValidationException;
import org.tmjug.spring.demo.service.UsersService;
import org.tmjug.spring.demo.transport.GenericResponse;
import org.tmjug.spring.demo.transport.UserTO;
import org.tmjug.spring.demo.transport.UsersResponse;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsersControllerImpl implements UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersControllerImpl.class);

    @Autowired
    private UsersService usersService;

    public UsersResponse getUserByUserName(@PathVariable String userName) throws UserNotFoundException {
        UsersResponse response = new UsersResponse();

        UserTO userTO = usersService.getByUserName(userName);
        if (userTO != null) {
            response.setUsers(Arrays.asList(userTO));
        } else {
            response.setErrorMessage("There is no user with the username '" + userName + "'");
        }

        return response;
    }

    @Override
    public UsersResponse getAll(@RequestParam(required = false) String name) {
        UsersResponse response = new UsersResponse();

        List<UserTO> userTO = usersService.getAllUsers(name);
        if (userTO != null) {
            response.setUsers(userTO);
        } else {
            response.setErrorMessage("There are no users with the name '" + name + "'");
        }

        return response;
    }

    @Override
    public GenericResponse save(@RequestBody UserTO user) throws ValidationException {
        GenericResponse response = new GenericResponse();

        usersService.save(user);

        return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody UsersResponse userNotFoundException(UserNotFoundException e) {
        UsersResponse response = new UsersResponse();

        response.setErrorMessage(e.getMessage());

        return response;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody GenericResponse validationException(ValidationException e) {
        GenericResponse response = new GenericResponse();

        response.setErrorMessage(e.getMessage());

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String internalServerError(Exception e) {
        e.printStackTrace();
        return "Internal server error";
    }
}
