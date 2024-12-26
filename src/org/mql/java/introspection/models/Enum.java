package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

public class Enum {

	String name;
	List<String> values;
	
	public Enum() {
		values = new Vector<String>();
	}

	public Enum(String name, List<String> values) {
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

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Enum [name=" + name + ", values=" + values + "]";
	}

}
