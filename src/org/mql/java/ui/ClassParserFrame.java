package org.mql.java.ui;

import org.mql.java.introspection.parser.ClassParser;
import org.mql.java.models.Classe;
import org.mql.java.models.Constructeur;
import org.mql.java.models.Field;
import org.mql.java.models.Method;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class ClassParserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
    private JTextPane outputPane;

    public ClassParserFrame() {
        setTitle("Class Parser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(true);

        userInterface();
    }

    private void userInterface() {

        JPanel inputPanel = new JPanel(new BorderLayout());
        textField = new JTextField();
        JButton parseButton = new JButton("Explorer la classe");
        inputPanel.add(new JLabel("Insérez le nom de la classe : "), BorderLayout.WEST);
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(parseButton, BorderLayout.EAST);

        
        outputPane = new JTextPane();
        outputPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputPane);

        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        
        parseButton.addActionListener(e -> parseClass());
    }

    private void parseClass() {
        String className = textField.getText().trim();
        if (className.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insérez le nom de la classe ", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ClassParser parser = new ClassParser("",className);
            Classe classe = parser.getClasse();

            outputPane.setText("");

            
            StyledDocument doc = outputPane.getStyledDocument();

            
            appendText(doc, "Details de la classe :\n", 16, Color.BLACK, true);
            appendText(doc, "  Class Name: " + classe.getName() + "\n", 14, Color.BLACK, false);
            appendText(doc, "  Modifier: ", 14, Color.BLUE, false);
            appendText(doc, classe.getModifier() + "\n", 14, Color.BLACK, false);
            appendText(doc, "  Super Class: " + classe.getSuperClass().getName() + "\n", 14, Color.BLACK, false);

           
         // Chaîne d'héritage
            appendText(doc, "\nChaine d'héritage :\n", 16, Color.BLACK, true);
            Classe currentClass = classe;
            while (currentClass != null) {
                appendText(doc, "  " + currentClass.getName() + "\n      |    ", 14, Color.BLUE, false);
                currentClass = currentClass.getSuperClass();
            }

            // Fields
            appendText(doc, "\nFields (" + parser.getFields().size() + "):\n", 16, Color.BLACK, true);
            for (Field field : parser.getFields()) {
                appendText(doc, "  ", 14, Color.BLACK, false);
                appendText(doc, field.getModifier() + " ", 14, Color.GREEN, false);
                appendText(doc, field.getType().getName() + " " + field.getName() + "\n", 14, Color.BLACK, false);
            }

            // Constructeurrs
            appendText(doc, "\nConstructeurs (" + parser.getConstructors().size() + "):\n", 16, Color.BLACK, true);
            for (Constructeur constructor : parser.getConstructors()) {
                appendText(doc, "  ", 14, Color.BLACK, false);
                appendText(doc, constructor.getModifier() + " ", 14, Color.CYAN, false);
                appendText(doc, constructor.getName() + "(" + constructor.getParameterTypes() + ")\n", 14, Color.BLACK, false);
            }

            // Methods
            appendText(doc, "\nMethods (" + parser.getMethods().size() + "):\n", 16, Color.BLACK, true);
            for (Method method : parser.getMethods()) {
                appendText(doc, "  ", 14, Color.BLACK, false);
                appendText(doc, method.getModifier() + " ", 14, Color.CYAN, false);
                appendText(doc, method.getReturnType() + " " + method.getName() + "(" + method.getParameterTypes() + ")\n", 14, Color.BLACK, false);
            }

            // Inner Classes
            appendText(doc, "\nClasses Internes (" + parser.getInternClasses().size() + "):\n", 16, Color.BLACK, true);
            for (Classe innerClass : parser.getInternClasses()) {
                appendText(doc, "  ", 14, Color.BLACK, false);
                appendText(doc, innerClass.getModifier() + " ", 14, Color.CYAN, false);
                appendText(doc, innerClass.getName() + "\n", 14, Color.BLACK, false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void appendText(StyledDocument doc, String text, int fontSize, Color color, boolean bold) {
        try {
            Style style = outputPane.addStyle("CustomStyle", null);
            StyleConstants.setFontSize(style, fontSize);
            StyleConstants.setForeground(style, color);
            StyleConstants.setBold(style, bold);
            doc.insertString(doc.getLength(), text, style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClassParserFrame frame = new ClassParserFrame();
        frame.setVisible(true);
    }
}
