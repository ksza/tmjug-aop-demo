package org.tmjug.spring.demo.service;

import org.tmjug.spring.demo.exceptions.UserNotFoundException;
import org.tmjug.spring.demo.exceptions.ValidationException;
import org.tmjug.spring.demo.transport.UserTO;

import java.util.List;

public interface UsersService {
    public UserTO getByUserName(String userName) throws UserNotFoundException;

    public List<UserTO> getAllUsers(String name);

    public void save(UserTO user) throws ValidationException;
}
