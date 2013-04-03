package org.tmjug.spring.demo.aspects.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class InterceptorTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aopTest.xml");

        SimpleService proxied = (SimpleService) applicationContext.getBean("proxy");
        proxied.displayText();
    }
}
