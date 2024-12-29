package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Annotation {

	String name;
	List<Field> values;
	
	public Annotation() {
		values = new Vector<Field>();
	}

	public Annotation(String name, List<Field> values) {
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

	public List<Field> getValues() {
		return values;
	}

	public void setValues(List<Field> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Annotation [name=" + name + ", values=" + values + "]";
	}

}
