package com.tpg.tmjug.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect("pertypewithin(com.tpg.tmjug.aop.userstore.repository.UserRepository+)")
public class LoggingAspect {

	private Logger logger;
	
	@After("staticinitialization(*)")
	public void instantiateLogger(JoinPoint.StaticPart jps) {
		logger = Logger.getLogger(jps.getSignature().getDeclaringTypeName());
	}
	
	@Before("loggableMethod()") 
	public void log(JoinPoint jp) {
		final StringBuilder sb = new StringBuilder("\nExecuting ");
		sb.append(jp.getSignature().getName());
		sb.append(" with: ");
		
		for(final Object o: jp.getArgs()) {
			sb.append(o.getClass().getSimpleName()+ " = " + o.toString() + "\t");
		}
		
		sb.append("\n");
		
		logger.info(sb.toString());
	}
	
	@Pointcut("execution(* com.tpg.tmjug.aop.userstore.repository.UserRepository+.*(..))")
	public void loggableMethod() { }
}
