package org.mql.java;

import java.io.File;

public class PackageExplorer { //explorer le package  et l'anayser
	
	public PackageExplorer() {
		//scan("org.mql.java.reflection"); 
	}
	
	public void scan(String packageName) {
		String classPath = System.getProperty("java.class.path");
		System.out.println(classPath);
		
		String packagePath = packageName.replace(".", "\\");
		System.out.println(packagePath);
		
		String path = classPath + "\\" + packagePath;
		//autre solution : classe loader au lieu de class path
		
		
		File dir = new File(path);
		File content[] = dir.listFiles();
		
		for (int i = 0; i < content.length; i++) {
			String name = packageName + "." + content[i].getName().replace(".class","");
			System.out.println("-" + name);
			
		}
	}
	
	public static void main(String[] args) {
		new PackageExplorer();
	}

}
