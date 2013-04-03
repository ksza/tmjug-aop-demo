package org.tmjug.spring.demo.aspects.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect used to trace the execution time and the parameters of a method
 */
@Aspect
@Component
public class TracingAspect {

    private static final Logger log = LoggerFactory.getLogger(TracingAspect.class);

    // 'execution' pointcut
    @Pointcut("execution(public * get*(..))")   // pointcut expression
    public void loadingMethods() {}             // pointcut signature

    // 'execution' pointcut
    @Pointcut("execution(public * save(..))")
    public void savingMethods() {}

    // 'within' pointcut
    @Pointcut("within(org.tmjug.spring.demo.service..*)")
    public void withinPackage() {}

    // pointcut which joins two defined pointcuts
    @Pointcut("withinPackage() && (loadingMethods() || savingMethods())")
    public void anyLoadAndSaveWithinPackage() {}

    // 'around' advice which uses a pointcut
    @Around(value = "anyLoadAndSaveWithinPackage()")
    public Object traceMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
        long now = System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        if (args != null && args[0] != null) {
            log.info("Executing '{}', with the parameters '{}'", className + "#" + methodName, args);
        } else {
            log.info("Executing '{}', with no parameters", className + "#" + methodName);
        }

        // start the method execution
        Object returnValue = pjp.proceed();

        // after the method has executed
        if (returnValue != null) {
            log.info("The execution took {} ms, returning '{}'", (System.currentTimeMillis() - now), returnValue);
        } else {
            log.info("The execution took {} ms", (System.currentTimeMillis() - now));
        }

        return returnValue;
    }
}
