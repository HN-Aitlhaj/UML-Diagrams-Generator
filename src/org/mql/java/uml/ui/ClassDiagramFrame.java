package org.mql.java.uml.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.mql.java.uml.enums.RelationType;
import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.introspection.xml.XMLGenerator;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Relation;

public class ClassDiagramFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Project project;
    private List<Entity> entities;
    private HashMap<String, EntityPanel<Entity>> entityPanelsMap = new HashMap<>();
    private JPanel contentPanel;
    private RelationLayer relationLayer;

    public ClassDiagramFrame(String path) {
        entities = new Vector<>();
        userInterface();
        EventQueue.invokeLater(() -> {
            project = scan(path);
            if (project != null) {
                generateDiagram();
                generateRelations();
            }
        });
    }    
    
    public ClassDiagramFrame(Project project) {
        entities = new Vector<>();
        userInterface();
        EventQueue.invokeLater(() -> {
            this.project = project;
            if (project != null) {
                this.entities = ProjectExplorer.getEntities(project);
                generateDiagram();
                generateRelations();
            }
        });
    }

    private void userInterface() {
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(1600, 1400));

        relationLayer = new RelationLayer();
        relationLayer.setBounds(0, 0, 1600, 1400);
        contentPanel.add(relationLayer);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        setVisible(true);
    }

    public void generateDiagram() {
        int x = 50;
        int y = 50;
        int padding = 100;

        for (Entity entity : entities) {
            EntityPanel<Entity> entityPanel = new EntityPanel<>(entity);
            entityPanel.setBounds(x, y, (int)(entityPanel.getPreferredSize().getWidth()), 
                                  (int)(entityPanel.getPreferredSize().getHeight()));
            contentPanel.add(entityPanel);
            entityPanelsMap.put(entity.getName(), entityPanel);

            x += entityPanel.getWidth() + padding;
            if (x > contentPanel.getWidth() - 200) {
                x = 50;
                y += entityPanel.getHeight() + padding;
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void generateRelations() {
        for (Entry<String, List<Relation>> entry : project.getRelations().entrySet()) {
            List<Relation> relations = entry.getValue();
            for (Relation relation : relations) {
                EntityPanel<Entity> srcPanel = entityPanelsMap.get(relation.getSouceClass().getName());
                EntityPanel<Entity> destPanel = entityPanelsMap.get(relation.getDestinationClass().getName());
                if (srcPanel != null && destPanel != null) {
                    relationLayer.addRelation(srcPanel, destPanel, relation.getType());
                }
            }
        }
        relationLayer.repaint();
    }

    private Project scan(String path) {
        path = !path.isEmpty() ? path : "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\AIT-LHAJ HANANE - UML-Diagrams-Generator\\bin";
        this.project = ProjectExplorer.scan(path);
        this.entities = ProjectExplorer.getEntities(project);
        if(project != null) {
            XMLGenerator generator = new XMLGenerator();
            generator.generateXML(project, "resources/generatedProject.xml");
        }
        return project;
    }

    public static void main(String[] args) {
        new ClassDiagramFrame("");
    }

    class RelationLayer extends JPanel {
        private static final long serialVersionUID = 1L;
        private List<RelationPanel> relations = new Vector<>();

        public RelationLayer() {
            setOpaque(false); // Transparent background
        }

        public void addRelation(EntityPanel<Entity> src, EntityPanel<Entity> dest, RelationType type) {
            relations.add(new RelationPanel(src, dest, type));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (RelationPanel relation : relations) {
                relation.paintComponent(g);
            }
        }
    }
}
