package org.mql.java.introspection.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.introspection.models.Classe;
import org.mql.java.introspection.models.Constructeur;
import org.mql.java.introspection.models.Field;
import org.mql.java.introspection.models.Method;

public class ClassParser {
	
	private Class<?> cls;
	public Classe classe;

	public ClassParser(String className) { 
		//File classFile = new File(className);
		//Class<?> cls = classFile.getClass();
		try {
			this.cls = Class.forName(className);
			this.classe = getClasse();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public Classe getClasse() {
		Classe classe = new Classe();
		classe.setName(cls.getSimpleName());
		classe.setModifier(getModifier());
		classe.setSuperClass(getSuperclass());
		classe.setInterfaces(getInterfaces());
		classe.setConstructors(getConstructors());
		classe.setMethods(getMethods());
		classe.setInternClasses(getInternClasses());
		
		return classe;
	}
	
	public String getModifier() {
		return Modifier.toString(cls.getModifiers());
	}
	
	private String getModifier(int modifier) {
		return Modifier.toString(modifier);
	}
	
	public Classe getSuperclass(){
		
		Classe classe = new Classe();
		classe.setName(cls.getSuperclass().getSimpleName());
		classe.setModifier(getModifier());
		classe.setInterfaces(getInterfaces());
		classe.setConstructors(getConstructors());
		classe.setMethods(getMethods());
		classe.setInternClasses(getInternClasses());
		return classe;
	}
	

	public List<String> getInterfaces(){
		List<String> interfaces = new Vector<String>();
		
		for(Class<?> interfaceClass : cls.getInterfaces()) {
			interfaces.add(interfaceClass.getName());
		}
		
		return interfaces;
	}
	
	public List<Field> getFields() {
		
		List<Field> fields = new Vector<Field>();
		for( java.lang.reflect.Field field : cls.getDeclaredFields() ) {
			fields.add(new Field(field.getName(), field.getType().getName(), getModifier(field.getModifiers())));
		}
		return fields;
	}
	
	public List<Constructeur> getConstructors(){
		Constructor<?>[] consts = cls.getDeclaredConstructors();
		
		List<Constructeur> constructeurs = new Vector<Constructeur>();
		for(Constructor<?> construct : consts ) {
			constructeurs.add(new Constructeur(construct.getName(),Modifier.toString(construct.getModifiers()), Arrays.asList( construct.getParameterTypes() )));
		}
		return constructeurs;
	}
	
	public List<Method> getMethods() {
		
		List<Method> methods = new Vector<Method>();
		for(java.lang.reflect.Method method : cls.getDeclaredMethods() ) {
			methods.add(new Method(method.getName(),getModifier(method.getModifiers()),method.getReturnType().getName(), Arrays.asList( method.getParameterTypes() )));
		}
		return methods;
	}
	
	public List<Classe> getInternClasses(){
		List<Classe> internClasses = new Vector<Classe>();
		Classe internClasse = new Classe();
		for(Class<?> internCls : cls.getDeclaredClasses()) {
			
			internClasse.setName(internCls.getSuperclass().getSimpleName());
			internClasse.setModifier(getModifier());
			internClasse.setInterfaces(getInterfaces());
			internClasse.setConstructors(getConstructors());
			internClasse.setMethods(getMethods());
			
			internClasses.add(internClasse);
		}
		return internClasses;
	}
}
