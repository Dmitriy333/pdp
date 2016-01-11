package com.epam.pdp.annotation;

import com.epam.pdp.annotation.example.TestSampleClass;
import com.epam.pdp.annotation.parser.TestAnnotationParser;

public class Main {
    public static void main(String[] args) {
        TestAnnotationParser annotationParser = new TestAnnotationParser();
        try {
            annotationParser.parse(TestSampleClass.class);
            System.out.println(annotationParser.getDetailedInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
