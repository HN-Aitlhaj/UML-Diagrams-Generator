package org.mql.java.uml.introspection.xml;

import java.util.List;
import java.util.Vector;

import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Method;

public class XMLContextLoader {
	
	public XMLContextLoader() {
	}
	
	public void load(XMLNode node) {
		
		try {
			//Class<?> claz = Class.forName(className);
			List<Class<?>> clss = new Vector<Class<?>>();
			clss.add(Classe.class);
			clss.add(org.mql.java.uml.models.Field.class);
			clss.add(Method.class);
			
			for (Class<?> class1 : clss) {
				for (XMLNode n : node.children()) {
					class1.getField(n.getName());
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
