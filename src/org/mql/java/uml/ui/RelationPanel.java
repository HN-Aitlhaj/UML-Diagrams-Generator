package org.mql.java.uml.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

class RelationPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    int barb;
    double phi;
  
    // Instance variables for coordinates
    private double x1, y1, x2, y2;
  
    // Constructor that accepts x1, y1, x2, y2
    public RelationPanel(double x1, double y1, double x2, double y2)
    {
        this.barb = 20;                   // barb length
        this.phi = Math.PI / 6;           // 30 degrees barb angle
        this.x1 = x1;                     // Set x1 from constructor
        this.y1 = y1;                     // Set y1 from constructor
        this.x2 = x2;                     // Set x2 from constructor
        this.y2 = y2;                     // Set y2 from constructor
        setBackground(Color.white);       // Set background color
    }
  
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the line using the coordinates passed to the constructor
        g2.setPaint(Color.blue);
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        
        // Calculate the angle for the arrow and draw the arrow
        double theta = Math.atan2(y2 - y1, x2 - x1);
        drawArrow(g2, theta, x2, y2);
    }
  
    private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
    {
        // Draw arrowheads based on the angle
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));

        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
    }
}
