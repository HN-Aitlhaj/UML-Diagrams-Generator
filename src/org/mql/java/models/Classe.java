package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Classe {
	
	private String name;
	private String modifier;
	private Classe superClass;
	private List<Interface> interfaces;
	private List<Field> fields;
	private List<Constructeur> constructors;
	private List<Method> methods; 
	private List<Classe> internClasses;
	
	
	public Classe() {
		interfaces = new Vector<Interface>();
		fields = new Vector<Field>();
		constructors = new Vector<Constructeur>();
		methods = new Vector<Method>(); 
		internClasses = new Vector<Classe>();
	}

	public Classe(String name, String modifier, Classe superClass, List<Interface> interfaces, List<Field> fields,
			List<Constructeur> constructors, List<Method> methods, List<Classe> internClasses) {
		super();
		this.name = name;
		this.modifier = modifier;
		this.superClass = superClass;
		this.interfaces = interfaces;
		this.fields = fields;
		this.constructors = constructors;
		this.methods = methods;
		this.internClasses = internClasses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Classe getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Classe superClass) {
		this.superClass = superClass;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Constructeur> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<Constructeur> constructors) {
		this.constructors = constructors;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public List<Classe> getInternClasses() {
		return internClasses;
	}

	public void setInternClasses(List<Classe> internClasses) {
		this.internClasses = internClasses;
	}

	@Override
	public String toString() {
		return "Classe [name=" + name + ", modifier=" + modifier + ", superClass=" + superClass + ", interfaces="
				+ interfaces + ", fields=" + fields + ", constructors=" + constructors + ", methods=" + methods
				+ ", internClasses=" + internClasses + "]";
	}

	

}
