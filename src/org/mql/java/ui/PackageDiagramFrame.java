package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import org.mql.java.introspection.parser.ProjectExplorer;
import org.mql.java.models.Package;
import org.mql.java.models.Project;

public class PackageDiagramFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public PackageDiagramFrame() {
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
            PackagePanel pac = new PackagePanel(packge.getName());
            add(pac);

        }

        revalidate();
        repaint();
    }

    private Project test() {
        String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";

        return ProjectExplorer.scan(path); // Return scanned project
    }

    public static void main(String[] args) {
        new PackageDiagramFrame();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
