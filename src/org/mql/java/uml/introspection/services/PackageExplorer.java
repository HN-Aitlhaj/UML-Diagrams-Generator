package org.mql.java.uml.introspection.services;

import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Package;

public class PackageExplorer {
	
	public static List<Package> scan(String binPath, String packageName, List<Package> packages) {
		
		
		Package packageObject = new Package();
		String packagePath = packageName.replace(".", File.separator);
		packageObject.setName(packageName);
		
		String path = binPath + File.separator + packagePath;
		
		File dir = new File(path);
		File content[] = dir.listFiles();
		
		List<Classe> classes = new Vector<Classe>();
		List<Annotation> annotations = new Vector<>();
		List<Enum> enums = new Vector<Enum>();
		List<Interface> interfaces = new Vector<Interface>();
		
		
		for (int i = 0; i < content.length; i++) {
			if(!content[i].getName().contains("$")) {
				if(content[i].isDirectory() ) {
					if(packageName == "")
						scan(binPath,content[i].getName(),packages);
					else
						scan(binPath,packageName + "." +content[i].getName(),packages);
						
				}else {
					String className = packageName + "." + content[i].getName().replace(".class","");
					
					ClassParser parser = new ClassParser(binPath,className);
					Class<?> cls = parser.getCls();
					
					if(cls.isAnnotation()) {
						
						annotations.add(new Annotation(cls.getName(),parser.getMethods()));
						
					} else if(cls.isEnum()) {
						List<String> values = parser.getFields().stream()
								.map(Field::getName)
								.collect(Collectors.toList());
						enums.add(new Enum(cls.getName(),values));
						
					}else if(cls.isInterface()) {
						interfaces.add(new Interface(className, parser.getInterfaces(), parser.getFields(), parser.getMethods()));
					}else {
						classes.add((Classe) parser.getEntity());
					}
				}
			}
		}
		
		if (!classes.isEmpty() || !annotations.isEmpty() || !enums.isEmpty() || !interfaces.isEmpty()) {
			packageObject.setClasses(classes);
			packageObject.setAnnotations(annotations);
			packageObject.setEnums(enums);
			packageObject.setInterfaces(interfaces);
			packages.add(packageObject);
		}
		
		return packages;
	}
}
