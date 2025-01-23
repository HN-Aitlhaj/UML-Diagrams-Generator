package org.mql.java.uml.introspection.services;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.uml.enums.Multiplicity;
import org.mql.java.uml.enums.RelationType;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Relation;


public class RelationMapper{ //Relation

	
	

	
	public static HashMap<String, List<Relation>> scan(Entity entity,Project project){ 
		
		HashMap<String,List<Relation>> projectRelations = new HashMap<String, List<Relation>>();
		String entityName = null;
		List<Relation> relations = new Vector<Relation>();

		
		ClassParser parser = new ClassParser(project.getBinPath(), entity.getName());
		Class<?> cls1 = parser.getCls();
	
		
		if(entity.getClass().equals(Interface.class)) {
        	Interface interf = ((Interface)entity);
        	entityName = interf.getName();
        	List<Interface> extendedInterfaces = interf.getExtendedInterfaces();
        	if(extendedInterfaces != null) {
        		for (Interface parent : extendedInterfaces) {
					Class<?> cls2 = new ClassParser(project.getBinPath(), parent.getName()).getCls();
					Relation relation = new Relation(RelationType.INHERITANCE, entity, parent, 
        				checkMultiplicity(cls1,cls2), checkMultiplicity(cls2,cls1));
					relations.add(relation);
				}
        	}
        	        	
        }else if(entity.getClass().equals(Classe.class)) {
        	
        	Classe classe = ((Classe)entity);
        	entityName = classe.getName();
        	Classe parent = classe.getSuperClass();
        	List<Interface> implementedInterfaces = classe.getInterfaces();
        	
        	if(implementedInterfaces != null) {
        		for (Interface interf : implementedInterfaces) {
					Class<?> cls2 = new ClassParser(project.getBinPath(), interf.getName()).getCls();
					Relation relation = new Relation(RelationType.IMPLEMENTATION, entity, interf, 
        				checkMultiplicity(cls1,cls2), checkMultiplicity(cls2,cls1));
					relations.add(relation);
				}
        	}
        	
        	if(parent != null) {
        		Class<?> cls2 = new ClassParser(project.getBinPath(), parent.getName()).getCls();
        		if(cls2 != Object.class) {
	        		Relation relation = new Relation(RelationType.INHERITANCE, entity, parent, 
	        				checkMultiplicity(cls1,cls2), checkMultiplicity(cls2,cls1));
	        		relations.add(relation);
	        		System.out.println(checkMultiplicity(cls1,cls2));
        		}
        	}
        }
		if(entityName != null && relations != null)
			projectRelations.put(entityName, relations);
		
		return projectRelations;
		
	}	
	
    public static Multiplicity checkMultiplicity(Class<?> srcClass, Class<?> destClass) {
    	
        for (Field field : srcClass.getDeclaredFields()) {

            if (field.getType().equals(destClass)) {
                return Multiplicity.ONE_ONE;
            }
           
            if (List.class.isAssignableFrom(field.getType()) || Set.class.isAssignableFrom(field.getType())) {
                
                if (field.getGenericType().toString().contains(destClass.getSimpleName())) {
                    if (field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) {
                        return Multiplicity.ZERO_MANY;
                    }
                    return Multiplicity.ONE_MANY;
                }
            }
        }
        return Multiplicity.ZERO_ONE; 
    }
    
    
	public static String toString(Object obj) {
		StringBuilder result = new StringBuilder();
		Set<Object> scannedObjects = new HashSet<Object>();
			scan(obj,result, scannedObjects);
		
		return result.toString();
	}
    
    
    
    private static String scan(Object obj,StringBuilder result,Set<Object> scannedObjects) {
		if (!scannedObjects.contains(obj) && obj != null) {
			scannedObjects.add(obj);
			
			Class<?> cls =obj.getClass();
			result.append(" \n ["+cls.getSimpleName()).append(" {");
			Field fields[] = cls.getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true);
				String typeName =field.getType().getSimpleName();
				try {
					if(field.getType().isPrimitive() || isWrapper(field.getType()) ) {
						result.append("  " + typeName + " " + field.getName() 
						+ " = " + field.get(obj) + ";");
					}else {
						scan(field.get(obj), result.append("  " + typeName +" " + field.getName() + ";"), scannedObjects);
						
					}
				}catch(Exception e) {
					System.out.println("Erreur : " + e.getMessage());
				}
				field.setAccessible(false);

			}
			
			result.append("]");
			
		}
		result.append(" ");
		return result.toString();
		
		
	}
	public static boolean isWrapper(Class<?> type) {
	    return
	        type == Double.class || type == Float.class || type == Long.class ||
	        type == Integer.class || type == Short.class || type == Character.class ||
	        type == Byte.class || type == Boolean.class || type == String.class;
	}
}
