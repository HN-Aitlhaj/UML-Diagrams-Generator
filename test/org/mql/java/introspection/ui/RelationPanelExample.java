package org.mql.java.introspection.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.mql.java.uml.enums.RelationType;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.ui.EntityPanel;
import org.mql.java.uml.ui.RelationPanel;

public class RelationPanelExample extends JFrame {

	private static final long serialVersionUID = 1L;


	public RelationPanelExample() {
		exp01();
	}
	

	private void exp01() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
	    
		JLabel label = new JLabel("click to drag");
		label.setBounds(30,10, 150,150);
		add(label);
		EntityPanel<?> panel1 = new EntityPanel<>(new Entity("Class A"));
		panel1.setBounds(100, 100, 150, 80); 
		panel1.setBackground(Color.CYAN);
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(panel1);
		
		EntityPanel<?> panel2 = new EntityPanel<>(new Entity("Class B"));
		panel2.setBounds(400, 300, 150, 80);
		panel2.setBackground(Color.YELLOW);
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(panel2);
		
		RelationPanel relationPanel = new RelationPanel(panel1, panel2, RelationType.COMPOSITIN);
		relationPanel.setBounds(0, 0, 800, 600); 
		add(relationPanel);

		setVisible(true);
		
	}


	public static void main(String[] args) {

		new RelationPanelExample();
	}
}	
