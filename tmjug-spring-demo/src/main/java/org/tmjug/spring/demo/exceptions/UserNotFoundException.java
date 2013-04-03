package org.tmjug.spring.demo.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String s) {
        super(s);
    }
}
