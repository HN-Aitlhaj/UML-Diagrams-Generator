package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.ui.test.ComponentMover;

public class PackagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
//	private int x, y;
//	private int width, height;
	private String title;

	public PackagePanel(String title) {
		this.title = title;
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
        setPreferredSize(new Dimension(300,300));
        
        setBackground(Color.white);
        setBorder( new TitledBorder( new LineBorder(Color.cyan, 2, true),title));
        

        new ComponentMover(frame, this);
	}
	
	
	
//	public PackagePanel(int x, int y, int width, int height, String title) {
//		
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
//		this.title = title;
	
//		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
//        setPreferredSize(new Dimension(300,300));
//       
//        setBackground(Color.white);
//        
//        ComponentMover cm = new ComponentMover(frame, this);
//	}

//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public void setWidth(int width) {
//		this.width = width;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public void setHeight(int height) {
//		this.height = height;
//	}
//
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
