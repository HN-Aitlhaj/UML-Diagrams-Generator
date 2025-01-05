package org.mql.java.introspection.parser;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Classe;
import org.mql.java.models.Constructeur;
import org.mql.java.models.Field;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;

public class ClassParser {
	
	private Class<?> cls;

	@SuppressWarnings("resource")
	public ClassParser(String classPath) { 
	
		try {
//			this.cls = Class.forName(classPath);
			
//			 File file = new File(classPath); 
//			 URL[] urls = {
//	        		 file.toURI().toURL()
//	        		 };
//	         this.cls = new URLClassLoader(urls).loadClass(classPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Class<?> getCls(){
		return cls;
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
		return getModifier(cls.getModifiers());
	}
	
	private String getModifier(int modifier) {
		return Modifier.toString(modifier);
	}
	
	public Classe getSuperclass(){
		
		Classe classe = new Classe();
		String superClass = cls.getSuperclass() !=null ? cls.getSuperclass().getSimpleName() : null;
		classe.setName(superClass);
		classe.setModifier(getModifier());
		classe.setInterfaces(getInterfaces());
		classe.setConstructors(getConstructors());
		classe.setMethods(getMethods());
		classe.setInternClasses(getInternClasses());
		return classe;
	}
	
	public List<Interface> getInterfaces(){
		return getInterfaces(cls);
	}
	
	public List<Interface> getInterfaces(Class<?> classe){
		List<Interface> interfaces = new Vector<Interface>();
		if(classe.getInterfaces().length == 0) return interfaces;
		for(Class<?> interfaceClass : classe.getInterfaces()) {
			
			interfaces.add(new Interface(interfaceClass.getSimpleName(),getInterfaces(interfaceClass),
					getFields(interfaceClass),getMethods(interfaceClass)));
		}
		return interfaces;
	}
	
	
	public List<Constructeur> getConstructors(){
		Constructor<?>[] consts = cls.getDeclaredConstructors();
		
		List<Constructeur> constructeurs = new Vector<Constructeur>();
		for(Constructor<?> construct : consts ) {
			constructeurs.add(new Constructeur(construct.getName(),Modifier.toString(construct.getModifiers()), 
					Arrays.asList( construct.getParameterTypes() )));
		}
		return constructeurs;
	}
	
	public List<Field> getFields() {
		
		return getFields(cls);
	}
	
	public List<Field> getFields(Class<?> cls) {
		
		List<Field> fields = new Vector<Field>();
		for( java.lang.reflect.Field field : cls.getDeclaredFields() ) {
			fields.add(new Field(field.getName(), field.getType(), getModifier(field.getModifiers())));
		}
		return fields;
	}
	
	public List<Method> getMethods() {
		
		return getMethods(cls);
	}
	
	private List<Method> getMethods(Class<?> cls) {
		
		List<Method> methods = new Vector<Method>();
		for(java.lang.reflect.Method method : cls.getDeclaredMethods() ) {
			methods.add(new Method(method.getName(),getModifier(method.getModifiers()),
					method.getReturnType().getName(), Arrays.asList( method.getParameterTypes() )));
		}
		return methods;
	}
	
	public List<Classe> getInternClasses(){
		List<Classe> internClasses = new Vector<Classe>();
		Classe internClasse = new Classe();
		for(Class<?> internCls : cls.getDeclaredClasses()) {
			//internClasse.getClass().isInterface()
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
