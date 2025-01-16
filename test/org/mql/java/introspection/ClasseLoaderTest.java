package org.mql.java.introspection;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.mql.java.introspection.parser.ClassParser;
import org.mql.java.introspection.parser.ClasseLoader;
import org.mql.java.models.Classe;

public class ClasseLoaderTest {
	
	String p = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
	String className = "org.mql.java.reflection.ui.Form";

    public ClasseLoaderTest() {
		exp02();
	}
	
	void exp01() {
//String p = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\src\\org\\mql\\java\\reflection\\ui\\Form.java";
        
		File classFile = new File(p);

        Class<?> cls = ClasseLoader.loadClass(classFile, className);
        ClassParser parser = new ClassParser(cls);
        Classe classe = parser.getClasse();
        for (org.mql.java.models.Method m : classe.getMethods() ) {
        	System.out.println(m);
        }
        
        if (cls != null) {
            for (Method m : cls.getDeclaredMethods()) {
                System.out.println("Method: " + m);
            }

            for (Constructor<?> c : cls.getDeclaredConstructors()) {
                System.out.println("Constructor: " + c);
            }

            System.out.println("Loaded class: " + cls.getName());
        } else {
            System.out.println("Failed to load the class.");
        }
	}
	
	void exp02() {
		try {
            // Base directory containing the package directories
            File classDir = new File(p);
            if (!classDir.exists() || !classDir.isDirectory()) {
                System.out.println("The specified class directory does not exist or is not a directory.");
                return;
            }

            URL[] urls = new URL[]{classDir.toURI().toURL()};
            
            // Create a new class loader with the directory
            @SuppressWarnings("resource")
			URLClassLoader classLoader = new URLClassLoader(urls);
            
            // Fully qualified class name
            String className = "org.mql.java.reflection.ui.Form";
            Class<?> clazz = classLoader.loadClass(className);
            
            System.out.println("Class loaded: " + clazz.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    public static void main(String[] args) {
        new ClasseLoaderTest();
    }

}
