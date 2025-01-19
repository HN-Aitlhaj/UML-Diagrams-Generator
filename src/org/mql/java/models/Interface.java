package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.annotations.Entity;

@Entity(type="")
public class Interface {
	
	private String name;
	private List<Interface> extendedInterfaces;
	private List<Field> fields;
	private List<Method> methods;
	
	public Interface() {
		
		extendedInterfaces = new Vector<Interface>();
		fields = new Vector<Field>();
		methods = new Vector<Method>();
	}


	public Interface(String name, List<Interface> extendedInterfaces, List<Field> fields, List<Method> methods) {
		super();
		this.name = name;
		this.extendedInterfaces = extendedInterfaces;
		this.fields = fields;
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public String getSimpleName() {
//		return name.substring(name.lastIndexOf('.') + 1);
		String[] n = name.split("\\.");
		return n[n.length - 1];
	}

	public void setName(String name) {
		this.name = name;
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
