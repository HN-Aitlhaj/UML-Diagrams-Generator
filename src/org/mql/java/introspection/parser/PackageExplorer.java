package org.mql.java.introspection.parser;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Classe;
import org.mql.java.models.Package;

public class PackageExplorer { //explorer le package  et l'anayser
	
	Package packageObject;
	public PackageExplorer() {
		packageObject = new Package();
		String classPath = System.getProperty("java.class.path");
		System.out.println(classPath + " : ");
		scan(classPath ,"org.mql.java.introspection.models");
		
		System.out.println("\npackageObject : ");
		for(Classe cls : packageObject.getClasses()) {
			System.out.println(cls);
		}
	}
	
	public void scan(String classPath, String packageName) {
		//String classPath = System.getProperty("java.class.path");
		//System.out.println(classPath);
		
		String packagePath = packageName.replace(".", "\\");
		System.out.println("Package : " + packagePath);
		packageObject.setName(packagePath);
		
		String path = classPath + "\\" + packagePath;
		//autre solution : classe loader au lieu de class path
		
		File dir = new File(path);
		File content[] = dir.listFiles();
		
		List<Classe> classes = new Vector<Classe>();
		for (int i = 0; i < content.length; i++) {
			String name = packageName + "." + content[i].getName().replace(".class","");
			System.out.println("-" + name);
			
			ClassParser parser = new ClassParser(name);
			classes.add(parser.getClasse());
		
			if(content[i].isDirectory()) {
				scan(classPath,packageName + "." +content[i].getName());
			}
		}
		
		packageObject.setClasses(classes);
	}
	
	public static void main(String[] args) {
		new PackageExplorer();
	}

}
