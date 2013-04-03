package org.tmjug.spring.demo.aspects.spring;

public class SimpleService {

    private String innerText;

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }

    public void displayText() {
        System.out.println("The configure text is '" + innerText + "'");
    }
}
