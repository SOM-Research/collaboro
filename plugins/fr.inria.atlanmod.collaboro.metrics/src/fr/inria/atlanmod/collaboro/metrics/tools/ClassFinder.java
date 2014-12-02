package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ClassFinder {
	
	public ClassFinder() {
		
	}
	
	public <T> List<Class<?>> getClassByType(Class<T> classType, String packageName) throws IOException, ClassNotFoundException {
		System.out.println("In getClassesFromPackage ( " + classType + " , " + packageName + " )");
		List<Class<?>> classList = new ArrayList<Class<?>>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace('.', '/');
		System.out.println(" PackagePath : " + packagePath);
		Enumeration<URL> resources = classLoader.getResources(packagePath);
		System.out.println("Iterating resources : ");
		while(resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			System.out.println("\t Resource : " + URLDecoder.decode(resource.getFile(),"UTF-8"));
			String directoryPath = resource.getFile();
			List<Class<?>> directoryClasses = findClassByType(classType, directoryPath, packageName);
			classList.addAll(directoryClasses);
		}
		
		return classList;
	}
	
	public Class<?> getClassByName(String className, String packageName) throws ClassNotFoundException {
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//String packagePath = packageName.replace('.', '/');

		String classNameFull = packageName + "." + className;
		return Class.forName(classNameFull);
	}
	
	private Class<?> findClassByName(String className, String directoryPath, String packageName) {
		Class<?> directoryClass = null;
		
		if(directoryPath.startsWith("file:") && directoryPath.contains("!")) {
			String[] splitDirectoryPath = directoryPath.split("!");
			URL jarURL = null;
			try {
				jarURL = new URL(splitDirectoryPath[0]);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ZipInputStream zip = new ZipInputStream(jarURL.openStream());
				ZipEntry entry;
				while((entry = zip.getNextEntry()) != null) {
					if(entry.getName().endsWith(className + ".class")) {
						String entryClassName = entry.getName().replaceAll("[$].*", "").replaceAll("[.]class", "").replace('/','.');
						if(entryClassName.startsWith(packageName)) {
							
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return directoryClass;
	}
	
	private <T> List<Class<?>> findClassByType(Class<T> classType, String directoryPath, String packageName) throws ClassNotFoundException {
		System.out.println("\t\tIn findClasses " + directoryPath);
		List<Class<?>> directoryClasses = new ArrayList<Class<?>>();
		List<String> classesName = new ArrayList<String>();
		
		if(directoryPath.startsWith("file:") && directoryPath.contains("!")) {
			String[] splitDirectoryPath = directoryPath.split("!");
			URL jarURL = null;
			try {
				jarURL = new URL(splitDirectoryPath[0]);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ZipInputStream zip = new ZipInputStream(jarURL.openStream());
				ZipEntry entry;
				while((entry = zip.getNextEntry()) != null) {
					if(entry.getName().endsWith(".class")) {
						String className = entry.getName().replaceAll("[$].*", "").replaceAll("[.]class", "").replace('/','.');
						if(className.startsWith(packageName)) {
							classesName.add(className);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		File directory = new File(directoryPath);
		if(directory.exists()) {
			File[] directoryFiles = directory.listFiles();
			for(File file : directoryFiles) {
				if(file.isDirectory()) {
					System.out.println("\t\t resource is a directory");
					List<Class<?>> subDirectoryClasses = findClassByType(classType,file.getName(),packageName);
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
		} else {
			System.out.println("directory doen't exist");
			if(!classesName.isEmpty()) {
				System.out.println("in jar file");
				for(String className : classesName) {
					Class<?> classz = null;
					try {
						System.out.println("class name : " + className);
						classz = Class.forName(className);
					} catch (ClassNotFoundException e){
						System.out.println("class not found : " + className);
					}
					try {
						Class<? extends T> clazz = classz.asSubclass(classType);
						System.out.println(classz.getName() + " extends " + classType.getName());
						directoryClasses.add(clazz);
					} catch (ClassCastException e) {
						System.out.println("class cast execption");
						e.printStackTrace();
					}
					
				}
			} else {
				System.out.println("classesName is empty");
			}
			
		}
		return directoryClasses;
	}
}
