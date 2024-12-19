package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

public class Constructeur {
	
	private String name;
	private String modifier;
	private List<Class<?>> parameterTypes;

	public Constructeur() {
		parameterTypes = new Vector<>();
	}

	public Constructeur(String name, String modifier, List<Class<?>> parameterTypes) {
		super();
		this.name = name;
		this.modifier = modifier;
		this.parameterTypes = parameterTypes;
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

	public List<Class<?>> getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(List<Class<?>> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	@Override
	public String toString() {
		return "Constructeur [name=" + name + ", modifier=" + modifier + ", parameterTypes=" + parameterTypes + "]";
	}

}
