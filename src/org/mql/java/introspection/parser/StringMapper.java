package org.mql.java.introspection.parser;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mql.java.enums.Multiplicity;


class StringMapper{ //Relation
	
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
	
	
	// Function to check multiplicity between two classes
    public static Multiplicity checkMultiplicity(Class<?> srcClass, Class<?> destClass) {
        // Iterate through all fields in the source class
        for (Field field : srcClass.getDeclaredFields()) {
            // Check if the field is of the destination class type (single object)
            if (field.getType().equals(destClass)) {
                return Multiplicity.ONE_ONE;  // 1..1: exactly one instance
            }

            // Check if the field is a collection (List, Set, etc.)
            if (List.class.isAssignableFrom(field.getType()) || Set.class.isAssignableFrom(field.getType())) {
                // Check if the collection's generic type is the destination class
                if (field.getGenericType().toString().contains(destClass.getSimpleName())) {
                    // Now determine if the collection is mandatory or optional
                    // If the collection field can be null, treat it as ZERO_MANY (optional)
                    if (field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) {
                        // If the collection is of List or Set, check if it's nullable (optional relationship)
                        return Multiplicity.ZERO_MANY;  // 0..*: zero or many related instances (optional)
                    }
                    // Otherwise, if the field is mandatory (must contain at least one related object)
                    return Multiplicity.ONE_MANY;  // 1..*: mandatory, one instance, many related instances
                }
            }
        }

        // If no relationship found, it might be ZERO_ONE (field is optional but single object)
        return Multiplicity.ZERO_ONE;  // 0..1: zero or one instance (optional)
    }
}
