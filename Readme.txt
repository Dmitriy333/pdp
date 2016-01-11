To run JarClassLoader:
1) build project with maven (mvn clean install)
2) copy .jar from "target" 
3) run jar as following:
	java -cp <pdp>.jar com.epam.pdp.classloader.main.MainRunner <jar_to_load_files> <package_to_load_from> <class_name>
For example:
	java -cp pdp.jar com.epam.pdp.classloader.main.MainRunner pdp2.jar com.epam.pdp.classloader.module.impl JarSampleImpl
