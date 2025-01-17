package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.models.Classe;

public class ClassPanel extends JPanel {

	
	private int x, y;
	private int width, height;
	private Classe classe;
	private JTextPane titleTestPane;
	private JTextPane fieldsTextPane;
	private JTextPane methodsTextPane;
	
	public ClassPanel() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
        setPreferredSize(new Dimension(300,300));
        
        setBackground(Color.white);
        setBorder( new TitledBorder( new LineBorder(Color.cyan, 2, true),classe.getSimpleName()));
	}
	
	public ClassPanel(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		g.drawRect(x, y-height, width, height);
		
	}

}
