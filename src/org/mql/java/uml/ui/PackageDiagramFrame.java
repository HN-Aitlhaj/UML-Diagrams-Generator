package org.mql.java.uml.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Project;

public class PackageDiagramFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public PackageDiagramFrame(String binPath) {
        userInterface();
        generatePackages(binPath);
    }

    private void userInterface() {
    	
        setSize(1800, 1500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    private void generatePackages(String binPath) {
    	Project proj = ProjectExplorer.scan(binPath);
    	if(proj != null)
        for (Package packge : proj.getPackages()) {
            PackagePanel pac = new PackagePanel(packge.getName());
            add(pac);

        }

        revalidate();
        repaint();
    }

//    private Project test() {
//        String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
//
//        return ProjectExplorer.scan(path); // Return scanned project
//    }
//
//    public static void main(String[] args) {
//        new PackageDiagramFrame();
//    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
