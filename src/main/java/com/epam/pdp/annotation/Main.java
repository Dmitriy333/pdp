package com.epam.pdp.annotation;

import com.epam.pdp.annotation.test.Test;

public class Main {
    public static void main(String[] args) {

    }
    
    class Annotated {
        @Test(expected = NullPointerException.class)
        public void foo(String myParam) {
           System.out.println("This is " + myParam);
        }
     }
}
