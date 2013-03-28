package com.tpg.tmjug.aop.aspect;

import org.apache.log4j.Logger;

import com.tpg.tmjug.aop.repository.UserRepository;

public aspect LoggingAspect pertypewithin(UserRepository+) {
	
	private Logger logger;

	after() : staticinitialization(*) {
		logger = Logger.getLogger(getWithinTypeName());
	}

	pointcut loggableMethods() : execution(* UserRepository+.*(..));
	
//	before() : loggableMethods() {
//		final StringBuilder sb = new StringBuilder("\nExecuting ");
//		sb.append(thisJoinPoint.getSignature().getName());
//		sb.append(" with: ");
//		
//		for(final Object o: thisJoinPoint.getArgs()) {
//			sb.append(o.getClass().getSimpleName()+ " = " + o.toString() + "\t");
//		}
//		
//		sb.append("\n");
//		
//		logger.info(sb.toString());
//	}
	
	Object around() : loggableMethods() {
		/* before */
		final StringBuilder sb = new StringBuilder("\nExecuting ");
		sb.append(thisJoinPoint.getSignature().getName());
		sb.append(" with: ");
		
		for(final Object o: thisJoinPoint.getArgs()) {
			sb.append(o.getClass().getSimpleName()+ " = " + o.toString() + "\t");
		}
		
		logger.info(sb.toString());
		
        long start = System.currentTimeMillis();
        try {
            return proceed();
        } finally {
            long end = System.currentTimeMillis();
            logger.info("Execution of " + thisJoinPoint.getSignature().getName() + " took " + (end - start) + " ms\n");
        }
	}
}
