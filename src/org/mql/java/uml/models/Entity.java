package org.mql.java.uml.models;

public class Entity {
	
	protected String name;
	
	
	public Entity() {
	}

	public Entity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getSimpleName() {
		//simpleName.substring(simpleName.lastIndexOf('.') + 1);
		String[] n = name.split("\\.");
		return n[n.length - 1];
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + "]";
	}
	
}
