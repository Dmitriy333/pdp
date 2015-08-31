package com.epam.pdp.classloader.module.impl;

import com.epam.pdp.classloader.module.IJarSample;

	public class JarSample implements IJarSample {
	    public JarSample() {
	        System.out.println("JarSample::JarSample()");
	    }

	    public void demo(String str) {
	        System.out.println("JarSample::demo(String str)");     
	        System.out.println(str);
	    }
}