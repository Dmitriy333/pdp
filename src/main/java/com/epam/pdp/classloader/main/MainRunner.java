package com.epam.pdp.classloader.main;

import com.epam.pdp.classloader.JarClassLoader;
import com.epam.pdp.classloader.module.JarSample;

public class MainRunner {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        String jarPath = args[0];
        String packagePath = args[1];
        String className = args[2];

        JarClassLoader jarClassLoader = new JarClassLoader(jarPath, packagePath);
        Class<?> clazz = jarClassLoader.loadClass(className);
        JarSample sample = (JarSample) clazz.newInstance();
        sample.demo("Test");
    }
}
