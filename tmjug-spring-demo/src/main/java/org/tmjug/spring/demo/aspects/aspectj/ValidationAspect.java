package org.tmjug.spring.demo.aspects.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tmjug.spring.demo.exceptions.ValidationException;
import org.tmjug.spring.demo.transport.UserTO;

/**
 * Aspect used to perform a basic validation
 */
@Aspect
@Component
public class ValidationAspect {

    private static final Logger log = LoggerFactory.getLogger(ValidationAspect.class);

    // 'within' pointcut
    @Pointcut("within(org.tmjug.spring.demo.service..*)")
    public void withinPackage() {}

    // 'execution ' pointcut
    @Pointcut("execution(public * save*(..))")
    public void onSaveExecution() {}

    @Pointcut("withinPackage() && onSaveExecution()")
    public void onSave(){}

    @Before("onSave() && args(userTO)")
    public void validateUser(UserTO userTO) throws ValidationException {
        if (userTO.getUserName().equals("admin")) {
            throw new ValidationException("Cannot save admin user");
        }
    }
}
