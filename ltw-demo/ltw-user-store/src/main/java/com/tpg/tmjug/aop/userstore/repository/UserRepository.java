package com.tpg.tmjug.aop.userstore.repository;

import java.util.List;

import com.tpg.tmjug.aop.userstore.entity.User;

public interface UserRepository {

	User save(User entity);
	
	User find(long id);
	
	List<User> findAll();
}
