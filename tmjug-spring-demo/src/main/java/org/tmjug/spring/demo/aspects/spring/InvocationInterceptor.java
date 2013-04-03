package org.tmjug.spring.demo.aspects.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor used to log the class access
 */
public class InvocationInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InvocationInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("Before invocation of the method '{}'", methodInvocation.getMethod().getName());

        long now = System.currentTimeMillis();
        Object returnValue = methodInvocation.proceed();
        log.info("Invocation finished in {} ms", (System.currentTimeMillis() - now));

        return returnValue;
    }
}
