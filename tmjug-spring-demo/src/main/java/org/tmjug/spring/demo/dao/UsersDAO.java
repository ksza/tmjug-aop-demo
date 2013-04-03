package org.tmjug.spring.demo.dao;

import org.tmjug.spring.demo.entities.User;

import java.util.List;

public interface UsersDAO {
    public User getByUserName(String userName);

    public List<User> getAll(String name);

    public void save(User user);
}

