package org.mql.java.ui.test;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.mql.java.introspection.parser.ProjectExplorer;
import org.mql.java.models.Classe;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.ui.PackagePanel;

public class ScreenFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public ScreenFrame() {
        userInterface();
        generateShapes();
    }

    private void userInterface() {
        // Set up the JFrame properties
        setSize(800, 500);
        setLayout(new FlowLayout());
// Use absolute positioning
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }

    private void generateShapes() {
        int width = 300;
        int height = 100;
        int x = 20;
        int y = 20;

        for (Package packge : test().getPackages()) {
            // Create a PackagePanel for each package
            PackagePanel pac = new PackagePanel(packge.getName());
            for (Classe classe : packge.getClasses()) {
				
			}
            

            // Add the PackagePanel to the JFrame
            add(pac);

            // Update the position for the next panel
            x += (int) (Math.random() * getWidth() + width); // Random offset for layout
            y += (int) (Math.random() * 105); 
        }

        // Refresh the frame to display changes
        revalidate();
        repaint();
    }

    private Project test() {
        String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";

        ProjectExplorer projExplorer = new ProjectExplorer();
        return projExplorer.scan(path); // Return scanned project
    }

    public static void main(String[] args) {
        new ScreenFrame();
    }

    @Override
    public Dimension getPreferredSize() {
        // Return user's preferred size if set
        return super.getPreferredSize();
    }
}
