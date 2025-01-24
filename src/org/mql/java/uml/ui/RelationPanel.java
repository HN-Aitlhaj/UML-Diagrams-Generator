package org.mql.java.uml.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JPanel;

import org.mql.java.uml.enums.RelationType;

public class RelationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private EntityPanel<?> srcPanel;
    private EntityPanel<?> destPanel;
    private RelationType relationType;

    public RelationPanel(EntityPanel<?> srcPanel, EntityPanel<?> destPanel, RelationType relationType) {
        this.srcPanel = srcPanel;
        this.destPanel = destPanel;
        this.relationType = relationType;
        setOpaque(false); // Make panel transparent for drawing only
        
    }
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	
	    g2.setStroke(new BasicStroke(2));
	    g2.setColor(Color.BLACK);
	
	    // Calculate dynamic positions for source and destination panels
	    Point srcCenter = new Point(
	        srcPanel.getX() + srcPanel.getWidth(),
	        srcPanel.getY() + srcPanel.getHeight()
	    );
	    Point destCenter = new Point(
	        destPanel.getX() ,
	        destPanel.getY() 
	    );
	
	    // Draw the main arrow line
	    
	    if (relationType == RelationType.IMPLEMENTATION) 
	    	drawDashedLine(g2,srcCenter.x, srcCenter.y, destCenter.x, destCenter.y);
	    else 
	    	g2.drawLine(srcCenter.x, srcCenter.y, destCenter.x, destCenter.y);

	    // Calculate arrowhead points
	    double angle = Math.atan2(destCenter.y - srcCenter.y, destCenter.x - srcCenter.x);
	    int arrowSize = 20;

	    int xArrow1 = destCenter.x - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
	    int yArrow1 = destCenter.y - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
	    int xArrow2 = destCenter.x - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
	    int yArrow2 = destCenter.y - (int) (arrowSize * Math.sin(angle + Math.PI / 6));
	    
	 // Calculate the opposite point of the diamond
	    int xOpposite = destCenter.x - (int) (2 * arrowSize * Math.cos(angle));
	    int yOpposite = destCenter.y - (int) (2 * arrowSize * Math.sin(angle));
		
		        // Draw the arrowhead
	    if (relationType == RelationType.ASSOCIATION) {
	    	g2.setColor(Color.BLUE);
	    	g2.setBackground(Color.white);
		        
		    g2.drawLine(destCenter.x, destCenter.y, xArrow1, yArrow1); // First line of the arrowhead
		    g2.drawLine(destCenter.x, destCenter.y, xArrow2, yArrow2); // Second line of the arrowhead
	    } else if (relationType == RelationType.INHERITANCE || relationType == RelationType.IMPLEMENTATION) {

	    	int[] xPoints = {destCenter.x, xArrow1, xArrow2}; // X points for the triangle
	    	int[] yPoints = {destCenter.y, yArrow1, yArrow2}; // Y points for the triangle


	    	g2.setColor(Color.WHITE);
	    	g2.fillPolygon(xPoints, yPoints, 3);
		
	    	g2.setColor(Color.BLACK);
		    g2.drawPolygon(xPoints, yPoints, 3);
		    
    	} else {
    		// Define the coordinates for the lozenge (diamond)
    		int[] xPoints = {destCenter.x, xArrow1, xOpposite, xArrow2};
	    	int[] yPoints = {destCenter.y, yArrow1, yOpposite, yArrow2}; 
	    	
	    	if(relationType == RelationType.AGGREGATION) {
	    		// Draw the diamand
	    		g2.setColor(Color.WHITE);
	    		g2.fillPolygon(xPoints, yPoints, 4);
	
	    		g2.setColor(Color.BLACK);          
	    		g2.drawPolygon(xPoints, yPoints, 4);  
	    	}else if( relationType == RelationType.COMPOSITIN) {
	    		g2.setColor(Color.BLACK); 
	    		g2.fillPolygon(xPoints, yPoints, 4); 
	
	    		g2.setColor(Color.BLACK);
	    		g2.drawPolygon(xPoints, yPoints, 4); 
	    	}
    	}

    	g2.setStroke(new BasicStroke(1));
	}


	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

	  Graphics2D g2d = (Graphics2D) g.create();
 
	  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
	                                  0, new float[]{9}, 0);
	  g2d.setStroke(dashed);
	  g2d.drawLine(x1, y1, x2, y2);

	  g2d.dispose();
	}

    
}
