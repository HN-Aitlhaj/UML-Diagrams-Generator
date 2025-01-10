package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ClassPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int x, y;
	private int width, height;
	
	
	public ClassPanel() {
		
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
	}

}
