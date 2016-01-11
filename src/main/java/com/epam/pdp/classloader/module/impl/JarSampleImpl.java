package com.epam.pdp.classloader.module.impl;

import com.epam.pdp.classloader.module.JarSample;

public class JarSampleImpl implements JarSample {
    public JarSampleImpl() {
        System.out.println("JarSample::JarSampleImpl()");
    }

    public void demo(String str) {
        System.out.println("JarSampleImpl::demo(String str)");
        System.out.println(str);
    }
}