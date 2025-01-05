package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Annotation {

	String name;
	List<Method> values;
	
	public Annotation() {
		values = new Vector<Method>();
	}

	public Annotation(String name, List<Method> values) {
		super();
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Method> getValues() {
		return values;
	}

	public void setValues(List<Method> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Annotation [name=" + name + ", values=" + values + "]";
	}

}
