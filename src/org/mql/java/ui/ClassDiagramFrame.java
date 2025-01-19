package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.Field;

import javax.swing.JFrame;

import org.mql.java.annotations.Entity;
import org.mql.java.introspection.parser.ProjectExplorer;
import org.mql.java.models.Classe;
import org.mql.java.models.Package;
import org.mql.java.models.Project;

public class ClassDiagramFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public ClassDiagramFrame() {
        userInterface();
        generatePackages();
    }

    private void userInterface() {
    	
        setSize(1800, 1500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    private void generatePackages() {
    	Project proj = test();
    	if(proj != null)
        for (Package packge : proj.getPackages()) {
        	//PackagePanel pac = new PackagePanel(packge.getName());
            for (Classe classe : packge.getClasses()) {
            	ClassPanel classPane = new ClassPanel(classe);//annotation enum interface ??
            	add(classPane);
			}
            
//            for (Interface classe : packge.getInterfaces()) {
//            	ClassPanel classPane = new ClassPanel(classe);//annotation enum interface ??
//            	add(classPane);
//			}
//            
//            for (Annotation classe : packge.getAnnotations()) {
//            	ClassPanel classPane = new ClassPanel(classe);//annotation enum interface ??
//            	add(classPane);
//			}
            
            Field[] f = packge.getClass().getDeclaredFields();
            for (Field field : f) {
				Entity e = field.getDeclaredAnnotation(Entity.class);
				String t = e.type();
				
			}
            

        }

        revalidate();
        repaint();
    }

    private Project test() {
        String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";

        return ProjectExplorer.scan(path); // Return scanned project
    }

    public static void main(String[] args) {
        new ClassDiagramFrame();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
