package org.mql.java.uml.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.mql.java.uml.introspection.xml.ProjectXmlParser;
import org.mql.java.uml.models.Project;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;

    public MainFrame() {
    	userInterface();
        setTitle("UML Diagrams Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,500);
		setLocationRelativeTo(null);
		setVisible(true);

        
    }

    private void userInterface() {
    	
    	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    	inputPanel.setBorder(new EmptyBorder(150, 50, 150, 50));
       
        textField = new JTextField();
        
        JPanel buttonsPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20) );
        
        JButton generateClassButton = new JButton("Générer Diagramme de Classe");
        JButton XMLgenerateClassButton = new JButton("Générer Diagramme de Classe Depuis fichierXML");
        JButton generatePackageButton = new JButton("Générer Diagramme de Package");
        
        generateClassButton.setBorder(new LineBorder(Color.decode("#87ceeb"), 6, true));
        generateClassButton.setBackground(Color.decode("#87ceeb"));
        XMLgenerateClassButton.setBorder(new LineBorder(Color.decode("#87ceeb"), 6, true));
        XMLgenerateClassButton.setBackground(Color.decode("#87ceeb"));
        generatePackageButton.setBorder(new LineBorder(Color.decode("#87ceeb"), 6, true));
        generatePackageButton.setBackground(Color.decode("#87ceeb"));
        
        buttonsPane.add(generateClassButton);
        buttonsPane.add(XMLgenerateClassButton);
        buttonsPane.add(generatePackageButton);
        
        inputPanel.add(new JLabel("Veuillez insérer le chemin de projet (bin): "));
        inputPanel.add(textField);
        inputPanel.add(buttonsPane);

        add(inputPanel);
        
        generateClassButton.addActionListener(this::generateClassDiagram);
        generatePackageButton.addActionListener(this::generatePackageDiagram);
        XMLgenerateClassButton.addActionListener(this::XMLgenerateClassDiagram);
    }

    
    private void generateClassDiagram(ActionEvent e) {
		String binPath = textField.getText();
		
		if(!binPath.isEmpty()) {
			dispose();
			new ClassDiagramFrame(binPath);
		}else
			JOptionPane.showMessageDialog(this, "Veuillez insérer un chemin valide (bin)!");
	}
    
    private void XMLgenerateClassDiagram(ActionEvent e) {
		ProjectXmlParser parser = new ProjectXmlParser();
		Project project = parser.parse("resources/project.xml");
			dispose();
			new ClassDiagramFrame(project);
		}
    
    private void generatePackageDiagram(ActionEvent e) {
		String binPath = textField.getText();
		
		if(!binPath.isEmpty()) {
			dispose();
			new PackageDiagramFrame(binPath);
		}else
			JOptionPane.showMessageDialog(this, "Veuillez insérer un chemin valide (bin)!");
	}

    public static void main(String[] args) {
        new MainFrame();
        
    }
}
