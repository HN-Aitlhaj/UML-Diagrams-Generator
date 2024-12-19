package org.mql.java.introspection.models;

public class Field {

	private String name;
	private String type;
	private String modifier;
	
	public Field() {
		super();
	}

	public Field(String name, String type, String modifier) {
		super();
		this.name = name;
		this.type = type;
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifer) {
		this.modifier = modifer;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", modifer=" + modifier + "]";
	}
	
}
