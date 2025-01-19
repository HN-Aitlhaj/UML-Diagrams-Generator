package org.mql.java.models;

public class Field {

	private String name;
	private Class<?> type;
	private Modifier modifier;
	
	public Field() {
		super();
	}

	public Field(String name, Class<?> type, Modifier modifier) {
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

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifer) {
		this.modifier = modifer;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", modifer=" + modifier + "]";
	}
	
}
