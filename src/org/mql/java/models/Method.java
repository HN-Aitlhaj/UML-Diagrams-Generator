package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Method {
	
	private String name;
	private String modifier;
	private String returnType;
	private List<Class<?>> parameterTypes;

	public Method() {
		super();
		parameterTypes = new Vector<Class<?>>();
	}

	public Method(String name, String modifier, String returnType, List<Class<?>> parameterTypes) {
		super();
		this.name = name;
		this.modifier = modifier;
		this.returnType = returnType;
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

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<Class<?>> getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(List<Class<?>> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	@Override
	public String toString() {
		return "Method [name=" + name + ", modifier=" + modifier + ", returnType=" + returnType + ", parameterTypes="
				+ parameterTypes + "]";
	}
	

}
