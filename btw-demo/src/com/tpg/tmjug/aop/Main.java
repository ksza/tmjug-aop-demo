package com.tpg.tmjug.aop;

import com.tpg.tmjug.aop.entity.User;
import com.tpg.tmjug.aop.repository.UserRepository;
import com.tpg.tmjug.aop.repository.UserRepositoryImpl;

public class Main {

	public static void main(String[] args) {
		
		final UserRepository repository = new UserRepositoryImpl();
		
		final User user1 = new User("John_1", 21);
		final User user2 = new User("John_2", 22);
		final User user3 = new User("John_3", 23);
		
		repository.save(user1);
		repository.save(user2);
		repository.save(user3);
		
		System.out.println(repository.find(user2.getId()));
	}
}
