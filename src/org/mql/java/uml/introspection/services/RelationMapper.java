package org.mql.java.uml.introspection.services;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.uml.enums.Multiplicity;
import org.mql.java.uml.models.Relation;


public class RelationMapper{ //Relation
	
	List<Relation> relations;
	
	public static String toString(Object obj) {
		StringBuilder result = new StringBuilder();
		Set<Object> scannedObjects = new HashSet<Object>();
			scan(obj,result, scannedObjects);
		
		return result.toString();
	}
	public List<Relation> scan(Class<?> srcCls ){
		relations = new Vector<Relation>();
		Relation relation = new Relation(null, srcCls, getClass(), null, null);
		relations.add(relation);
		return relations;
		
	}
	private static String scan(Object obj,StringBuilder result,Set<Object> scannedObjects) {
		if (!scannedObjects.contains(obj) && obj != null) {
			scannedObjects.add(obj);
			
			Class<?> cls =obj.getClass();
			result.append(" \n ["+cls.getSimpleName()).append(" {");
			Field fields[] = cls.getDeclaredFields();
			
			for (Field field : fields) {
				
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
			}result.append("]");
			
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
	
	
	// Function to check multiplicity between two classes
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
}
