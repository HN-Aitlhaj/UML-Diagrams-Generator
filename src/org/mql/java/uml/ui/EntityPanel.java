package org.mql.java.uml.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.mql.java.uml.enums.Visibility;
import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Constructeur;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Method;
import org.mql.java.uml.models.Modifier;
import org.mql.java.uml.ui.tools.ComponentMover;

public class EntityPanel<T extends Entity> extends JPanel {

	private static final long serialVersionUID = 1L;
	private T entity;
	private Dimension dimension;
	private Point position;

	
	public EntityPanel() {
		super();
		this.dimension = new Dimension(getWidth(),getHeight());
		//getLocation();
		this.position = new Point(getX(),getY());
	}

	public EntityPanel(T entity) {
		this.entity = entity;
		
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
        //setPreferredSize(new Dimension(200,200));

        JTextPane methodPane = new JTextPane();
        JTextPane fieldPane = new JTextPane();
        
		
		String title = entity.getSimpleName();
        if(entity.getClass().equals(Interface.class)) {
        	Interface interf = ((Interface)entity);
        	
        	fieldPane = getFieldsPane(interf.getFields());
        	methodPane = getMethodsPane(interf.getMethods(),null);
        	        	
        }else if(entity.getClass().equals(Enum.class)) {
        	Enum enumeration = ((Enum)entity);
        	fieldPane = getValuesPane(enumeration.getValues());
        	
        }else if(entity.getClass().equals(Annotation.class)) {
        	Annotation annotation = ((Annotation)entity);
        	
        	methodPane = getMethodsPane(annotation.getValues(),null);
        
        }else if(entity.getClass().equals(Classe.class)) {
        	Classe classe = ((Classe)entity);
        	fieldPane = getFieldsPane(classe.getFields());
        	methodPane = getMethodsPane(classe.getMethods(),classe.getConstructors());
        	//modifier + internClasses
        	//in relation : interfaces + superClass
        }
        
        JTextPane titlePane = new JTextPane();
        titlePane.setSize(new Dimension(title.length(),50));
        titlePane.setText(title);
        titlePane.setBorder(new LineBorder(Color.decode("#87ceeb"), 2, true));
        titlePane.setBackground(Color.decode("#87ceeb"));
        
        
        fieldPane.setBorder(new LineBorder(Color.decode("#87ceeb"), 1));
        methodPane.setBorder(new LineBorder(Color.decode("#87ceeb"), 1));
        
        titlePane.setEnabled(false);
        fieldPane.setEnabled(false);
        methodPane.setEnabled(false);
        
        titlePane.setDisabledTextColor(Color.black);
        fieldPane.setDisabledTextColor(Color.black);
        methodPane.setDisabledTextColor(Color.black);
        
        add(titlePane);
        add(fieldPane);
        add(methodPane);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        setBorder( new TitledBorder( new LineBorder(Color.cyan, 2, true),entity.getClass().getSimpleName()));
        
        new ComponentMover(frame, this);
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
	private JTextPane getFieldsPane(List<Field> fields) {
		JTextPane fieldPane = new JTextPane();
		StyledDocument doc = fieldPane.getStyledDocument();
		for (Field field : fields) {
			appendText(fieldPane, doc, "  ", 10, Color.BLACK, false);
			appendText(fieldPane, doc, getVisibilitySign(field.getModifier().getAccess())
					+Arrays.toString(field.getModifier().getNonAccess()) + " ", 10, Color.GREEN, false);
			appendText(fieldPane, doc, field.getType().getName() + " " + field.getName() + "\n", 10, Color.BLACK, false);
            
		}
        
		return fieldPane;		
	}
	public JTextPane getValuesPane(List<String> values) {
		JTextPane fieldPane = new JTextPane();
		StyledDocument doc = fieldPane.getStyledDocument();
		for (String value : values) {
			appendText(fieldPane, doc, "  ", 10, Color.BLACK, false);
			appendText(fieldPane, doc, value  + "\n", 10, Color.BLACK, false);
            
		}
        
		return fieldPane;		
	}
	
	public JTextPane getMethodsPane(List<Method> methods,List<Constructeur> constructeurs) {
		JTextPane methodPane = new JTextPane();
		StyledDocument doc = methodPane.getStyledDocument();
        if(constructeurs != null) {
        	for (Constructeur constructeur : constructeurs) {
        		String[] modifier = {};
        		Visibility access = Visibility.DEFAULT;
        		Modifier mod = constructeur.getModifier();
        		if(constructeur.getModifier() != null) {
        			modifier = mod.getNonAccess();
        			access = mod.getAccess();
        		}
        		
        		List<Class<?>> types = constructeur.getParameterTypes();
                appendText(methodPane, doc, "  ", 10, Color.BLACK, false);
                appendText(methodPane, doc, getVisibilitySign(access) 
                		+ " " + (modifier.length != 0 ? Arrays.toString(modifier) : "") + " ", 10, Color.BLACK,
                		false);
                appendText(methodPane, doc, "Constructor(" + (types.size() != 0 ? 
                		types.stream().map(Class::getSimpleName).collect(Collectors.joining(",")) : "") + ")\n",
                		10, Color.BLACK, false);
            }
        }
		
		for (Method method : methods) {
			String[] modifier = {};
    		Visibility access = Visibility.DEFAULT;
    		Modifier mod = method.getModifier();
    		if(method.getModifier() != null) {
    			modifier = mod.getNonAccess();
    			access = mod.getAccess();
    		}
			
    		List<Class<?>> types = method.getParameterTypes();
    		if (types == null) {
    		    types = new Vector<>();
    		}

    		appendText(methodPane, doc, "  ", 10, Color.BLACK, false);
    		appendText(methodPane, doc, getVisibilitySign(access) 
    		        + " " + (modifier.length == 0 ? "" : Arrays.toString(modifier)) + " ", 10, Color.BLACK, false);

    		
    		String returnType = method.getReturnType();
    		String formattedReturnType = (returnType != null) 
    		        ? returnType.substring(returnType.lastIndexOf('.') + 1) 
    		        : "void"; 

    		appendText(methodPane, doc, method.getName() + "(" + 
    		        (types.isEmpty() ? "" : types.stream().map(Class::getSimpleName).collect(Collectors.joining(","))) + 
    		        ") : " + formattedReturnType + "\n", 10, Color.BLACK, false);

        }
		return methodPane;		
	}
	
	
	
	private void appendText(JTextPane outputPane, StyledDocument doc, String text, int fontSize, Color color, 
			boolean bold) {
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

	private String getVisibilitySign(Visibility v) {
		switch (v) {
		case Visibility.PUBLIC:
			return "+";
			
		case Visibility.PRIVATE:
			return "-";
		case Visibility.PROTECTED :
			return "#";
		default:
			return "";
		}
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	
	
}