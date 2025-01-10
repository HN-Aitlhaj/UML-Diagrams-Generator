package org.mql.java.introspection.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClasseLoader {

    public static Class<?> loadClass(File classFile, String className) {
        try {
            byte[] classData = Files.readAllBytes(Paths.get(classFile.toURI()));
            MyClassLoader classLoader = new MyClassLoader(ClassLoader.getSystemClassLoader());
            return classLoader.loadClass(className, classData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> loadClass(String classFile, String className) {
        try {
            byte[] classData = Files.readAllBytes(Paths.get(classFile));
            MyClassLoader classLoader = new MyClassLoader(ClassLoader.getSystemClassLoader());
            return classLoader.loadClass(className, classData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static class MyClassLoader extends ClassLoader {
        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        public Class<?> loadClass(String name, byte[] data) throws ClassNotFoundException {
            // Check if the class is already loaded
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass == null) {
                try {
                    // Delegate to the parent class loader (system class loader for Swing)
                    loadedClass = getParent().loadClass(name);
                } catch (ClassNotFoundException e) {
                    // If not found, define it using the provided byte array
                    loadedClass = defineClass(name, data, 0, data.length);
                }
            }
            return loadedClass;
        }
    }

}
