package org.tmjug.spring.demo.components;

import org.springframework.stereotype.Component;
import org.tmjug.spring.demo.entities.User;

@Component
public class UserProcessingComponentImpl implements UserProcessingComponent {

    public void processUser(User user) {
        // no op
    }
}
