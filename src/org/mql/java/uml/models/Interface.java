package org.mql.java.uml.models;

import java.util.List;
import java.util.Vector;

public class Interface extends Entity {
	
	private List<Interface> extendedInterfaces;
	private List<Field> fields;
	private List<Method> methods;
	
	public Interface() {
		super();
		extendedInterfaces = new Vector<Interface>();
		fields = new Vector<Field>();
		methods = new Vector<Method>();
	}


	public Interface(String name, List<Interface> extendedInterfaces, List<Field> fields, List<Method> methods) {
		super(name);
		this.extendedInterfaces = extendedInterfaces;
		this.fields = fields;
		this.methods = methods;
	}

	public List<Interface> getExtendedInterfaces() {
		return extendedInterfaces;
	}


	public void setExtendedInterfaces(List<Interface> extendedInterfaces) {
		this.extendedInterfaces = extendedInterfaces;
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
		return "Interface [name=" + name + ", extendedInterfaces=" + extendedInterfaces + ", fields=" + fields + ", methods=" + methods
				+ "]";
	}
	
}
