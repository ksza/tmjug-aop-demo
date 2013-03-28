package com.tpg.tmjug.aop.repository;

import java.util.List;

import com.tpg.tmjug.aop.entity.User;

public interface UserRepository {

	User save(User entity);
	
	User find(long id);
	
	List<User> findAll();
}
