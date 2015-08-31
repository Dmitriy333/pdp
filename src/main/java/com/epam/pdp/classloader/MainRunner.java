package com.epam.pdp.classloader;

import com.epam.pdp.classloader.module.IJarSample;

public class MainRunner {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/*JarClassLoader jarClassLoader = new JarClassLoader("pdp.jar", "org.beq.classloader.classes.impl");*/
		String jarPath = args[0];
		String packagePath = args[1];
		String className = args[2];
		JarClassLoader jarClassLoader = new JarClassLoader("D:\\pdp.jar", "com.epam.pdp.classloader.module.impl");
		Class<?> clazz = jarClassLoader.loadClass("JarSample");
		IJarSample sample = (IJarSample) clazz.newInstance();
		sample.demo("Test");
	}
}
