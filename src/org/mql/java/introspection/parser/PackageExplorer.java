package org.mql.java.introspection.parser;

import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.mql.java.models.Annotation;
import org.mql.java.models.Enum;
import org.mql.java.models.Field;
import org.mql.java.models.Interface;
import org.mql.java.models.Classe;
import org.mql.java.models.Package;

public class PackageExplorer { //explorer le package  et l'anayser
	
	public PackageExplorer() {
//		packageObject = new Package();
		
//		String classPath = System.getProperty("java.class.path");
//		System.out.println(classPath + " : ");
//		List<Package> packages = scan(classPath ,"org.mql.java",new Vector<Package>());
//		
//		System.out.println("\npackageObject : ");
//		for(Package cls: packages) {
//			System.out.println(cls);
//		}
	}
	
	public List<Package> scan(String classPath, String packageName, List<Package> packages) {
		//String classPath = System.getProperty("java.class.path");
		//System.out.println(classPath);
		Package packageObject = new Package();
		String packagePath = packageName.replace(".", "\\");
		
		
		packageObject.setName(packagePath);
		
		String path = classPath + "\\" + packagePath;
		//autre solution : classe loader au lieu de class path
		
		File dir = new File(path);
		File content[] = dir.listFiles();
		
		List<Classe> classes = new Vector<Classe>();
		List<Annotation> annotations = new Vector<>();
		List<Enum> enums = new Vector<Enum>();
		List<Interface> interfaces = new Vector<Interface>();
		
		
		for (int i = 0; i < content.length; i++) {
						
			if(content[i].isDirectory() ) {
					scan(classPath,packageName + "." +content[i].getName(),packages);
			
			}else {
				String className = packageName + "." + content[i].getName().replace(".class","");
				ClassParser parser = new ClassParser(className);
				Class<?> cls = parser.getCls();
				
				if(cls.isAnnotation()) {
					
					annotations.add(new Annotation(cls.getSimpleName(),parser.getMethods()));
					
				} else if(cls.isEnum()) {
//					List<String> values1 = parser.getMethods().stream()
//							.map(Method::getName)
//							.collect(Collectors.toList());
					List<String> values = parser.getFields().stream()
							.map(Field::getName)
							.collect(Collectors.toList());
					enums.add(new Enum(cls.getName(),values));
					
				}else if(cls.isInterface()) {
					interfaces.add(new Interface());
				}else {
					classes.add(parser.getClasse());
				}
			}
				
				
		}
		if (!classes.isEmpty() || !annotations.isEmpty() || !enums.isEmpty() || !interfaces.isEmpty()) {
			packageObject.setClasses(classes);
			packageObject.setAnnotations(annotations);
			packageObject.setEnums(enums);
			packageObject.setInterfaces(interfaces);
			packages.add(packageObject);}
		return packages;
	}
}
