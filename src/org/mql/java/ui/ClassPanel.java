package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.models.Classe;
import org.mql.java.ui.test.ComponentMover;

public class ClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Classe classe;
	
	public ClassPanel(Classe classe) {
		this.classe = classe;
		
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
        setPreferredSize(new Dimension(200,200));
        JTextPane titlePane = new JTextPane();
        titlePane.setText(classe.getName());
        titlePane.setBorder(new LineBorder(Color.blue, 2, true));
        titlePane.setBackground(Color.LIGHT_GRAY);
        
//        StyledDocument doc = outputPane.getStyledDocument();
//        appendText(doc, "Details de la classe :\n", 16, Color.BLACK, true);
        JTextPane fieldPane = new JTextPane();
        JTextPane methodPane = new JTextPane();

        fieldPane.setBorder(new LineBorder(Color.blue, 1));
        methodPane.setBorder(new LineBorder(Color.blue, 1));
        
        add(titlePane);
        add(fieldPane);
        add(methodPane);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        setBorder( new TitledBorder( new LineBorder(Color.cyan, 2, true),classe.getSimpleName()));
        
        //new ComponentMover(frame, this);
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

}
