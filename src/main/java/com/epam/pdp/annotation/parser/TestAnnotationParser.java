package com.epam.pdp.annotation.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.epam.pdp.annotation.test.Test;

public class TestAnnotationParser {
    private final List<String> passedMethods = new ArrayList<String>();
    private final List<String> failedMethods = new ArrayList<String>();

    public void parse(Class<?> clazz) throws Exception {
        Object instance = clazz.newInstance();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                Class<? extends Exception> expected = test.expected();
                try {
                    method.invoke(instance);
                    passedMethods.add(method.getName());
                } catch (Exception e) {
                    if (!e.getClass().equals(expected)) {
                        failedMethods.add(method.getName());
                    } else {
                        passedMethods.add(method.getName());
                    }
                }
            }
        }
    }

    public String getDetailedInfo() {
        StringBuilder details = new StringBuilder();

        details.append("Passed methods: \n");
        for (String passedMethodName : passedMethods) {
            details.append(passedMethodName);
            details.append("\n");
        }

        details.append("\nFailed methods: \n");
        for (String failedMethodName : failedMethods) {
            details.append(failedMethodName);
            details.append("\n");
        }
        return details.toString();
    }
}
