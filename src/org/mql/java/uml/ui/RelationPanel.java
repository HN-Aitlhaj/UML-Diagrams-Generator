package org.mql.java.uml.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        
        Point srcEdge = getIntersection(srcPanel.getBounds(), destPanel.getBounds());
        Point destEdge = getIntersection(destPanel.getBounds(), srcPanel.getBounds());

       
        if (relationType == RelationType.IMPLEMENTATION) 
            drawDashedLine(g2, srcEdge.x, srcEdge.y, destEdge.x, destEdge.y);
        else 
            g2.drawLine(srcEdge.x, srcEdge.y, destEdge.x, destEdge.y);

        
        double angle = Math.atan2(destEdge.y - srcEdge.y, destEdge.x - srcEdge.x);
        int arrowSize = 15;

        int xArrow1 = destEdge.x - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = destEdge.y - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = destEdge.x - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = destEdge.y - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

        
        int xOpposite = destEdge.x - (int) (2 * arrowSize * Math.cos(angle));
        int yOpposite = destEdge.y - (int) (2 * arrowSize * Math.sin(angle));
        
        // Draw the arrowhead
        if (relationType == RelationType.ASSOCIATION) {
            g2.setColor(Color.BLUE);
            g2.setBackground(Color.white);
            g2.drawLine(destEdge.x, destEdge.y, xArrow1, yArrow1); // First line of the arrowhead
            g2.drawLine(destEdge.x, destEdge.y, xArrow2, yArrow2); // Second line of the arrowhead
        } else if (relationType == RelationType.INHERITANCE || relationType == RelationType.IMPLEMENTATION) {
            int[] xPoints = {destEdge.x, xArrow1, xArrow2}; // X points for the triangle
            int[] yPoints = {destEdge.y, yArrow1, yArrow2}; // Y points for the triangle

            g2.setColor(Color.WHITE);
            g2.fillPolygon(xPoints, yPoints, 3);

            g2.setColor(Color.BLACK);
            g2.drawPolygon(xPoints, yPoints, 3);
        } else {
            // Define the coordinates for the lozenge (diamond)
            int[] xPoints = {destEdge.x, xArrow1, xOpposite, xArrow2};
            int[] yPoints = {destEdge.y, yArrow1, yOpposite, yArrow2}; 
            
            if (relationType == RelationType.AGGREGATION) {
                // Draw the diamond
                g2.setColor(Color.WHITE);
                g2.fillPolygon(xPoints, yPoints, 4);

                g2.setColor(Color.BLACK);          
                g2.drawPolygon(xPoints, yPoints, 4);  
            } else if (relationType == RelationType.COMPOSITIN) {
                g2.setColor(Color.BLACK); 
                g2.fillPolygon(xPoints, yPoints, 4); 

                g2.setColor(Color.BLACK);
                g2.drawPolygon(xPoints, yPoints, 4); 
            }
        }

        g2.setStroke(new BasicStroke(1));
    }

    private Point getIntersection(Rectangle src, Rectangle dest) {
        Point centerSrc = new Point(src.x + src.width / 2, src.y + src.height / 2);
        Point centerDest = new Point(dest.x + dest.width / 2, dest.y + dest.height / 2);

        double dx = centerDest.x - centerSrc.x;
        double dy = centerDest.y - centerSrc.y;

        double scale = Math.min(
            Math.abs(src.width / 2.0 / dx), 
            Math.abs(src.height / 2.0 / dy)
        );

        return new Point(
            (int) (centerSrc.x + dx * scale),
            (int) (centerSrc.y + dy * scale)
        );
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
    }
}
