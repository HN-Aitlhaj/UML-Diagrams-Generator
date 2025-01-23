package org.mql.java.uml.models;

import java.util.List;
import java.util.Vector;

public class Enum  extends Entity {

	private List<String> values;
	
	public Enum() {
		values = new Vector<String>();
	}

	public Enum(String name, List<String> values) {
		super(name);
		this.values = values;
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
