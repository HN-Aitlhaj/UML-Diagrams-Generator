package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

public class Interface {
	
	private String name;
	private Class<?> superClass;
	private List<Field> fields;
	private List<Method> methods;
	
	
	public Interface() {
		
		fields = new Vector<Field>();
		methods = new Vector<Method>();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Class<?> getSuperClass() {
		return superClass;
	}


	public void setSuperClass(Class<?> superClass) {
		this.superClass = superClass;
	}


	public List<Field> getFields() {
		return fields;
	}


	public void setFields(List<Field> fields) {
		this.fields = fields;
	}


	public List<Method> getMethods() {
		return methods;
	}


	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}


	@Override
	public String toString() {
		return "Interface [name=" + name + ", superClass=" + superClass + ", fields=" + fields + ", methods=" + methods
				+ "]";
	}


	

}
