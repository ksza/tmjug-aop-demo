package org.tmjug.spring.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tmjug.spring.demo.exceptions.UserNotFoundException;
import org.tmjug.spring.demo.exceptions.ValidationException;
import org.tmjug.spring.demo.transport.GenericResponse;
import org.tmjug.spring.demo.transport.UserTO;
import org.tmjug.spring.demo.transport.UsersResponse;

@RequestMapping(value = "/users/")
public interface UsersController {

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody UsersResponse getUserByUserName(@PathVariable String userName) throws UserNotFoundException;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody UsersResponse getAll(@RequestParam(required = false) String name);

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse save(@RequestBody UserTO user) throws ValidationException;
}
