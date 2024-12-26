package org.mql.java.introspection.parser;

import java.io.File;

public class PackageExplorer { //explorer le package  et l'anayser
	
	public PackageExplorer() {
		String classPath = System.getProperty("java.class.path");
		System.out.println(classPath + " : ");
		scan(classPath ,"org.mql.java.introspection"); 
	}
	
	public void scan(String classPath, String packageName) {
		//String classPath = System.getProperty("java.class.path");
		//System.out.println(classPath);
		
		String packagePath = packageName.replace(".", "\\");
		System.out.println("Package : " + packagePath);
		
		String path = classPath + "\\" + packagePath;
		//autre solution : classe loader au lieu de class path
		
		File dir = new File(path);
		File content[] = dir.listFiles();
		
		for (int i = 0; i < content.length; i++) {
			String name = packageName + "." + content[i].getName().replace(".class","");
			System.out.println("-" + name);
			if(content[i].isDirectory()) {
				scan(classPath,packageName + "." +content[i].getName());
			}
			
		}
	}
	
	public static void main(String[] args) {
		new PackageExplorer();
	}

}
