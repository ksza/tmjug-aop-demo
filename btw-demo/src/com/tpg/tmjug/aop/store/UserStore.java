package com.tpg.tmjug.aop.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tpg.tmjug.aop.entity.User;

public final class UserStore {

	private static UserStore store;
	
	private final Map<Long, User> db = new HashMap<>();
	private long dbIndex = 1;
	
	private UserStore() { }
	
	public static synchronized UserStore getStore() {
		if(store == null) {
			store = new UserStore();
		}
		
		return store;
	}
	
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
