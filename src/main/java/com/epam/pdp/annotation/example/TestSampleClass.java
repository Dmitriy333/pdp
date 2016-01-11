package com.epam.pdp.annotation.example;

import com.epam.pdp.annotation.test.Test;

public class TestSampleClass {
    @Test(expected = RuntimeException.class)
    public void failedMethod() {
        // some implementation to test
    }

    @Test(expected = RuntimeException.class)
    public void passedMethod() {
        // some implementation to test
        throw new RuntimeException();
    }
}