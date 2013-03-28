package com.tpg.tmjug.aop.repository;

import java.util.List;

import com.tpg.tmjug.aop.entity.User;
import com.tpg.tmjug.aop.store.UserStore;

public class UserRepositoryImpl implements UserRepository {

	private final UserStore store = UserStore.getStore();
	
	@Override
	public User save(User entity) {
		return store.save(entity);
	}

	@Override
	public User find(long id) {
		return store.find(id);
	}

	@Override
	public List<User> findAll() {
		return store.findAll();
	}

}
