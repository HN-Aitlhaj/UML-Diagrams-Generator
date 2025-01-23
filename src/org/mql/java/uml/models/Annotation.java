package org.mql.java.uml.models;

import java.util.List;
import java.util.Vector;

public class Annotation extends Entity {

	private List<Method> values;
	
	public Annotation() {
		values = new Vector<Method>();
	}

	public Annotation(String name, List<Method> values) {
		super(name);
		this.values = values;
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
