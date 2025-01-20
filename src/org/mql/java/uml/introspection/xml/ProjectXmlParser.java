package org.mql.java.uml.introspection.xml;

import java.util.List;
import java.util.Vector;

import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Constructeur;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Method;
import org.mql.java.uml.models.Modifier;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Project;

/*
 * DOM PARSER
 */
public class ProjectXmlParser { 
	
	public ProjectXmlParser() {
	}
	
	//Mapping XML/Object
	public Project parse(String source){
		Project proj = new Project();
		List<Package> packages = new Vector<Package>();
		
		XMLNode root = new XMLNode(source);
		XMLNode packageNodes[] = root.children();
		System.out.println("nbr packages : " + packageNodes.length);
		
		//ClassParser parser = new ClassParser(); //needed in FieldType Class<?>
		//try{ 
		//Class.forName("java.lang." + "String");	ifit's wrapperclasse //java.util => hashTable and list	
			
		//}catch(Exception e) { e.printStackTrace(); }
		
		proj.setName(root.attribute("name"));

		
		for(XMLNode packageNode : packageNodes) {
			Package packge = new Package();
			packge.setName(packageNode.attribute("name"));
			
			for (XMLNode classeNode : packageNode.child("classes").children()) {
				Classe classe = new Classe();
				classe.setName(classeNode.attribute("name"));
				classe.setModifier(new Modifier(classeNode.attribute("modifier") ));
				classe.setSuperClass(new Classe());
				classe.getSuperClass().setName(classeNode.child("superClass").getValue());
				
				List<Field> fields = new Vector<Field>();
				for (XMLNode fieldNode : classeNode.child("fields").children()) {
					Field field = new Field();
					field.setName(fieldNode.attribute("name"));
					fields.add(field);
				}
				classe.setFields(fields);
				
				List<Constructeur> constructors = new Vector<Constructeur>();
				for (XMLNode constructorNode : classeNode.child("constructeurs").children()) {
					Constructeur constructor = new Constructeur();
					constructor.setName(constructorNode.attribute("name"));
					constructors.add(constructor);
				}
				classe.setFields(fields);
				
				List<Method> methods = new Vector<Method>();
				for (XMLNode methodNode : classeNode.child("methods").children()) {
					Method method = new Method();
					method.setName(methodNode.attribute("name"));
					methods.add(method);
				}
				classe.setMethods(methods);
				
				List<Classe> internClasses = new Vector<Classe>();
				for (XMLNode internClasseNode : classeNode.child("internClasses").children()) {
					Classe internClasse = new Classe();
					internClasse.setName(internClasseNode.attribute("name"));
					internClasses.add(internClasse);
				}
				classe.setInternClasses(internClasses);
				
				packge.addClasse(classe);
			}
			
			for (XMLNode interfaceNode : packageNode.child("interfaces").children()) {
				Interface interf = new Interface();
				interf.setName(interfaceNode.attribute("name"));
				for (XMLNode extInterf : interfaceNode.child("extendedInterfaces").children()) {
					interf.getExtendedInterfaces().add(new Interface(extInterf.getValue(), null ,null, null));
				}
				
				List<Field> fields = new Vector<Field>();
				for (XMLNode fieldNode : interfaceNode.child("fields").children()) {
					Field field = new Field();
					field.setName(fieldNode.attribute("name"));
					fields.add(field);
				}
				interf.setFields(fields);
				
				List<Method> methods = new Vector<Method>();
				for (XMLNode methodNode : interfaceNode.child("methods").children()) {
					Method method = new Method();
					method.setName(methodNode.attribute("name"));
					
					for (XMLNode typeNode : methodNode.children()) {
						String type= typeNode.getValue();
						if("int".equals(type))
							method.getParameterTypes().add(Class.forPrimitiveName(type));
						else
							try {
								method.getParameterTypes().add(Class.forName(type));
							} catch (ClassNotFoundException e) {
								//e.printStackTrace();
							}
					}
					methods.add(method);
				}
				interf.setMethods(methods);
				packge.getInterfaces().add(interf);
			}			
			
			for (XMLNode annotationNode : packageNode.child("annotations").children()) {
				Annotation annotation = new Annotation();
				annotation.setName(annotationNode.attribute("name"));
				for (XMLNode value : annotationNode.children()) {
					annotation.getValues().add(new Method(value.attribute("name"), null ,value.attribute("returnType"), null));
				}
				packge.getAnnotations().add(annotation);
			}
			
			for (XMLNode enumNode : packageNode.child("enums").children()) {
				Enum enumeration = new Enum();
				enumeration.setName(enumNode.attribute("name"));
				for (XMLNode value : enumNode.children()) {
					enumeration.getValues().add(value.getValue());
				}
				packge.getEnums().add(enumeration);
			}

			packages.add(packge);
		}
		proj.setPackages(packages);
		return proj;
		
	}
}
