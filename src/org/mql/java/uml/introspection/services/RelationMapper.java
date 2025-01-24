package org.mql.java.uml.introspection.services;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.uml.enums.Multiplicity;
import org.mql.java.uml.enums.RelationType;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Constructeur;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Method;
import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Relation;

public class RelationMapper { // Relation

	public static HashMap<String, List<Relation>> scan(Entity entity, Project project) {
		HashMap<String, List<Relation>> projectRelations = new HashMap<String, List<Relation>>();
		String entityName = null;
		List<Relation> relations = new Vector<Relation>();
		List<Entity> entityList = ProjectExplorer.getEntities(project);

		ClassParser parser = new ClassParser(project.getBinPath(), entity.getName());
		Class<?> cls1 = parser.getCls();

		if (entity.getClass().equals(Interface.class)) {

			Interface interf = ((Interface) entity);
			entityName = interf.getName();
			List<Interface> extendedInterfaces = interf.getExtendedInterfaces();
			if (extendedInterfaces != null) {
				for (Interface parent : extendedInterfaces) {
					if (entityList.contains(parent)) {
						Class<?> cls2 = new ClassParser(project.getBinPath(), parent.getName()).getCls();
						Relation relation = new Relation(RelationType.INHERITANCE, entity, parent,
								checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
						
						relations.add(relation);
					}

				}
			}

			for (Field field : interf.getFields()) {
				ClassParser parser2 = new ClassParser(field.getType());
				if (entityList.contains(parser2.getEntity())) {
					Class<?> cls2 = parser2.getCls();
					Relation relation = new Relation(RelationType.ASSOCIATION, entity, parser2.getEntity(),
							checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
					relations.add(relation);
				}
			}

			for (Method method : interf.getMethods()) {
				for (Class<?> type : method.getParameterTypes()) {
					ClassParser parser2 = new ClassParser(type);

					if (entityList.contains(parser2.getEntity())) {
						Class<?> cls2 = parser2.getCls();
						Relation relation = new Relation(RelationType.ASSOCIATION, entity, parser2.getEntity(),
								checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
						relations.add(relation);
					}
				}
			}

		} else if (entity.getClass().equals(Classe.class)) {

			Classe classe = ((Classe) entity);
			entityName = classe.getName();
			Classe parent = classe.getSuperClass();
			List<Interface> implementedInterfaces = classe.getInterfaces();

			if (implementedInterfaces != null) {
				for (Interface interf : implementedInterfaces) {
					if (entityList.contains(interf)) {
						Class<?> cls2 = new ClassParser(project.getBinPath(), interf.getName()).getCls();
						Relation relation = new Relation(RelationType.IMPLEMENTATION, entity, interf,
								checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
						relations.add(relation);
					}
				}
			}

			if (parent != null) {
				ClassParser parser2 = new ClassParser(project.getBinPath(), parent.getName());
				//logger.info("Scanning entity: " + entity.getName());

				if (entityList.contains(parser2.getEntity())) {
					Class<?> cls2 = parser2.getCls();
					Relation relation = new Relation(RelationType.INHERITANCE, entity, parent,
							checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
					relations.add(relation);
					//logger.info("Found relation: " + relation.toString());

				}
			}

			for (Field field : classe.getFields()) {
				ClassParser parser2 = new ClassParser(field.getType());
				if (entityList.contains(parser2.getEntity())) {

					Class<?> cls2 = parser2.getCls();

					for (Constructeur constructeur : classe.getConstructors()) {
						for (Class<?> type : constructeur.getParameterTypes()) {
							RelationType relationType;
							if (cls2.equals(type))
								relationType = RelationType.COMPOSITIN;
							else
								relationType = RelationType.AGGREGATION;

							Entity destEntity = parser2.getEntity();
							Relation relation = new Relation(relationType, entity, destEntity,
									checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
							relations.add(relation);
						}

					}
				}
			}
			for (Constructeur constructeur : classe.getConstructors()) {
				for (Class<?> type : constructeur.getParameterTypes()) {
					ClassParser parser2 = new ClassParser(type);
					Entity destEntity = parser2.getEntity();
					
					Class<?> cls2 = parser.getCls();
					if(existsInFields(entityList, cls1, cls2)) {
						//Class<?> cls2 = parser2.getCls();
						if(entityList.contains(destEntity)  && !type.isPrimitive()) {
						Relation relation = new Relation(RelationType.COMPOSITIN, entity, destEntity,
								checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
						relations.add(relation);}
					}else {
						if(entityList.contains(destEntity)  && !type.isPrimitive()) {
							Relation relation = new Relation(RelationType.ASSOCIATION, entity, destEntity,
									checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
							relations.add(relation);
							}
					}
				}
			}
			for (Method method : classe.getMethods()) {
        		for ( Class<?> type : method.getParameterTypes()) {
        			ClassParser parser2 = new ClassParser(type);
					Entity destEntity = parser2.getEntity();
					Class<?> cls2 = parser.getCls();
					if(!existsInFields(entityList, cls1, cls2)) {
//						Class<?> cls2 = parser2.getCls();
						if(entityList.contains(destEntity) && !type.isPrimitive()) {
						Relation relation = new Relation(RelationType.ASSOCIATION, entity, destEntity,
								checkMultiplicity(cls1, cls2), checkMultiplicity(cls2, cls1));
						relations.add(relation);}
					}
       		 	}
       		 	 
       	 	}
        }
		

		if (entityName != null && relations != null)
			projectRelations.put(entityName, relations);

		return projectRelations;

	}

	private static boolean existsInFields(List<Entity> entityList, Class<?> srcClass, Class<?> destClass) {
	   
	        
	        for (java.lang.reflect.Field field : srcClass.getDeclaredFields()) {
	       
	            if (Collection.class.isAssignableFrom(field.getType())) {
	                if (field.getGenericType() instanceof ParameterizedType) {
	                    ParameterizedType paramType = (ParameterizedType) field.getGenericType();
	                    Type[] typeArgs = paramType.getActualTypeArguments();
	                    if (typeArgs.length > 0 && typeArgs[0] instanceof Class) {
	                        Class<?> genericClass = (Class<?>) typeArgs[0];
	                        if (genericClass.equals(destClass)) {
	                            return true;
	                        }
	                    }
	                }
	            } else {
	                // If it's not a collection, directly compare types
	                ClassParser fieldType = new ClassParser(field.getType());
	                if (fieldType.getCls().equals(destClass))
	                    return true;
	            }
	        
	    }
	    return false;
	}


	public static Multiplicity checkMultiplicity(Class<?> srcClass, Class<?> destClass) {

		for (java.lang.reflect.Field field : srcClass.getDeclaredFields()) {

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
