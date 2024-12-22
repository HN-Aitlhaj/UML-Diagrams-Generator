package org.mql.java.introspection.parser;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


class StringMapper{
	
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
	
}
