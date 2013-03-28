package com.tpg.tmjug.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ValidationAspect {

	@Before("validateMethods()")
	public void validateMethodArguments(final JoinPoint jp) {
		
		for(final Object o: jp.getArgs()) {
			if(o == null) {
				throw new IllegalArgumentException("Null is unacceptable!");
			}
		}
	}
	
	@Pointcut("set(private String com.tpg.tmjug.aop.userstore.entity.User.name)")
	public void validateMethods() { }
}