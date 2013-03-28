package com.tpg.tmjug.aop.userstore.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tpg.tmjug.aop.userstore.entity.User;

public class UserStore {

	private final Map<Long, User> db = new HashMap<>();
	private long dbIndex = 1;
	
	public User save(final User user) {
		try {
			Thread.sleep(21);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		user.setId(dbIndex++);
		
		db.put(user.getId(), user);
		
		return user;
	}
	
	public User find(final long id) {
		try {
			Thread.sleep(21);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return db.get(id);
	}
	
	public List<User> findAll() {
		try {
			Thread.sleep(21);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>(db.values());
	}
}
