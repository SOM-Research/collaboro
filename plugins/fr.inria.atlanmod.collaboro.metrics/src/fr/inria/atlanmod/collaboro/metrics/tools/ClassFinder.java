package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassFinder {
	
	public ClassFinder() {
		
	}
	
	public <T> List<Class<?>> getClassesFromPackage(Class<T> classType, String packageName) throws IOException, ClassNotFoundException {
		System.out.println("In getClassesFromPackage ( " + classType + " , " + packageName + " )");
		List<Class<?>> classList = new ArrayList<Class<?>>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace('.', '/');
		System.out.println(" PackagePath : " + packagePath);
		Enumeration<URL> resources = classLoader.getResources(packagePath);
		System.out.println("Iterating resources : ");
		while(resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			System.out.println("\t Resource : " + resource);
			File directory = new File(resource.getFile());
			List<Class<?>> directoryClasses = findClasses(classType,directory, packageName);
			classList.addAll(directoryClasses);
		}
		
		return classList;
	}
	
	private <T> List<Class<?>> findClasses(Class<T> classType, File directory, String packageName) throws ClassNotFoundException {
		System.out.println("\t\tIn findClasses");
		List<Class<?>> directoryClasses = new ArrayList<Class<?>>();
		if(directory.exists()) {
			File[] directoryFiles = directory.listFiles();
			for(File file : directoryFiles) {
				if(file.isDirectory()) {
					System.out.println("\t\t resource is a directory");
					List<Class<?>> subDirectoryClasses = findClasses(classType,file,packageName);
					directoryClasses.addAll(subDirectoryClasses);
				} else {
					if(file.getName().endsWith(".class")) {
						String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
						Class<?> classz = Class.forName(className);
						try {
							Class<? extends T> clazz = classz.asSubclass(classType);
							System.out.println(classz.getName() + " extends " + classType.getName());
							directoryClasses.add(clazz);
						} catch (ClassCastException e) {
							
						}
					}
				}
			}
		}
		return directoryClasses;
	}
}
