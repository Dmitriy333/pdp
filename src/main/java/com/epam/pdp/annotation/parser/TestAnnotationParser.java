package com.epam.pdp.annotation.parser;

import java.lang.reflect.Method;

import com.epam.pdp.annotation.test.Test;

public class TestAnnotationParser {
    private int pass;
    private int fail;

    public void parse(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                // this is how you access to the attributes
                Test test = method.getAnnotation(Test.class);
                Class<? extends Exception> expected = test.expected();
                try {
                    method.invoke(null);
                    pass++;
                } catch (Exception e) {
                    if (Exception.class != expected) {
                        fail++;
                    } else {
                        pass++;
                    }
                }
            }
        }
    }

    public int getPass() {
        return pass;
    }

    public int getFail() {
        return fail;
    }

}
