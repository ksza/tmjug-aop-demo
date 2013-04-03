package org.tmjug.spring.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmjug.spring.demo.components.UserProcessingComponent;
import org.tmjug.spring.demo.dao.UsersDAO;
import org.tmjug.spring.demo.entities.User;
import org.tmjug.spring.demo.exceptions.UserNotFoundException;
import org.tmjug.spring.demo.exceptions.ValidationException;
import org.tmjug.spring.demo.transport.UserTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private UserProcessingComponent userProcessingComponent;

    public UserTO getByUserName(String userName) throws UserNotFoundException {
        User user = usersDAO.getByUserName(userName);
        if (user == null) {
            log.warn("There is no user with the username '{}'", userName);
            throw new UserNotFoundException("There is no user with the username '" + userName + "'");
        }

        userProcessingComponent.processUser(user);

        return buildUserTO(user);
    }

    public List<UserTO> getAllUsers(String name) {
        List<User> users = usersDAO.getAll(name);
        if (users == null || users.size() == 0) {
            log.warn("There are no users with the first name '{}'", name);
            return null;
        }

        List<UserTO> userTOs = new ArrayList<UserTO>(users.size());
        for (User user : users) {
            userTOs.add(buildUserTO(user));
        }

        return userTOs;
    }

    @Override
    public void save(UserTO user) throws ValidationException {
        User entity = new User();

        entity.setUserId(new Random().nextInt());
        entity.setUserName(user.getUserName());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());

        usersDAO.save(entity);
    }

    private static UserTO buildUserTO(User user) {
        UserTO userTO = new UserTO();

        userTO.setUserName(user.getUserName());
        userTO.setFirstName(user.getFirstName());
        userTO.setLastName(user.getLastName());

        return userTO;
    }
}
