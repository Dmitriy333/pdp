package com.epam.pdp.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Test {
    Class<? extends Exception> expected();
}
