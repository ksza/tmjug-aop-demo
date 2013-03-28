package com.tpg.tmjug.aop.aspect;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tpg.tmjug.aop.userstore.entity.User;
import com.tpg.tmjug.aop.userstore.repository.UserRepository;
import com.tpg.tmjug.aop.userstore.repository.UserRepositoryImpl;

/**
 * Setting the <b>name</b> property of the {@link User} should be validated by
 * the {@link ValidationAspect}. The aspect should validate any
 * attempt to assign a value to the <b>name</b> attribute.
 */
public class ValidationAspectTest {

	private UserRepository repository;
	
	@Before
	public void setUp() {
		repository = new UserRepositoryImpl();
	}
	
	@Test
	public void should_persist_user() {
		final User user = new User("User_1", 21);
		repository.save(user);
		
		final User foundUser = repository.find(user.getId());
		
		assertEquals(user, foundUser);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_for_null_name_in_constructor() {
		new User(null, 21);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_for_null_name_in_setter() {
		final User user = new User("User_1", 21);
		
		user.setName(null);
	}
}
