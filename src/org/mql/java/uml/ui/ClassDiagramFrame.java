package org.mql.java.uml.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.swing.JFrame;

import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Project;

public class ClassDiagramFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    HashMap<EntityPanel<?>, Dimension> position;

    public ClassDiagramFrame(String path) {
        setContentPane(new RelationPanel(300, 300, 300, 300));
        userInterface();
        generateDiagram(path);
    }

    private void userInterface() {
        setSize(1000, 700); 
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
       
        
        
    }

    private void generateDiagram(String path) {
    	Project proj = test(path);
    	if(proj != null)
        for (Package packge : proj.getPackages()) {
        	
            for (Classe classe : packge.getClasses()) {
            	EntityPanel<Classe> e = new EntityPanel<>(classe); 
            	e.getX(); e.getY();
            	add(new EntityPanel<>(classe));
            	add(new RelationPanel(300, 300, 300, 300));				///
            }
            for (Interface interf : packge.getInterfaces())
            	add(new EntityPanel<Interface>(interf));
			
            for (Enum enumeration : packge.getEnums()) 
            	add(new EntityPanel<>(enumeration));
            
            for (Annotation annotation : packge.getAnnotations()) 
            	add(new EntityPanel<>(annotation));

        }

        revalidate();
        repaint();
    }

    private Project test(String path) {
        path = !path.isEmpty() ? path : "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";

        return ProjectExplorer.scan(path); // Return scanned project
    }

    public static void main(String[] args) {
        new ClassDiagramFrame("");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}