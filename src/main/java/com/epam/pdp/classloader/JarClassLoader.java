package com.epam.pdp.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends ClassLoader {

	private HashMap<String, Class<?>> cache = new HashMap<String, Class<?>>();

	private String jarFileName;

	private String packageName;

	private static String WARNING = "Warning : No jar file found. Packet unmarshalling won't be possible. Please verify your classpath";

	public JarClassLoader(String jarFileName, String packageName) {
		this.jarFileName = jarFileName;
		this.packageName = packageName;
		cacheClasses();
	}

	@Override
	public synchronized Class<?> loadClass(String name)
			throws ClassNotFoundException {
		Class<?> result = cache.get(name);
		// Возможно класс вызывается не по полному имени - добавим имя пакета
		if (result == null)
			result = cache.get(packageName + "." + name);
		// Если класса нет в кэше то возможно он системный
		if (result == null)
			result = super.findSystemClass(name);
		System.out.println("== loadClass(" + name + ")");
		return result;
	}

	/**
	 * Retrieve classes from jar file and cache them
	 */
	private void cacheClasses() {
		try {
			JarFile jarFile = new JarFile(jarFileName);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				/*One of the purpose of the bes classloader is validating on rutime*/
				if (match(normalize(jarEntry.getName()), packageName)) {
					byte[] classData = loadClassData(jarFile, jarEntry);
					if (classData != null) {
						Class<?> clazz = defineClass(
								stripClassName(normalize(jarEntry.getName())),
								classData, 0, classData.length);
						cache.put(clazz.getName(), clazz);
						System.out.println("== class " + clazz.getName()
								+ " loaded in cache");
					}
				}
			}
		} catch (IOException IOE) {
			System.out.println(WARNING);
		}
	}

	/**
	 * 
	 * Retrieve canonical name of the class
	 * 
	 * @param className
	 * @return canonical name of the class
	 */

	private String stripClassName(String className) {
		return className.substring(0, className.length() - 6);
	}

	/**
	 * Transform name of file system into name of the class (exchange slashes to
	 * dots)
	 * 
	 * @param className
	 * 
	 * @return
	 */

	private String normalize(String className) {
		return className.replace('/', '.');
	}

	/**
	 * Class validation. Checks if class belongs to the package and has .class
	 * extension
	 * 
	 * @param className
	 * @param packageName
	 * @return
	 */

	private boolean match(String className, String packageName) {
		return className.startsWith(packageName)
				&& className.endsWith(".class");
	}

	/**
	 * 
	 * Retrieve file from JarEntry
	 * 
	 * @param jarFile
	 *            -jar file
	 * 
	 * @param jarEntry
	 *            - jar entry
	 * 
	 * @return null if cannot read
	 */

	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry)
			throws IOException {
		long size = jarEntry.getSize();
		if (size == -1 || size == 0)
			return null;
		byte[] data = new byte[(int) size];
		InputStream in = jarFile.getInputStream(jarEntry);
		in.read(data);
		return data;
	}

}