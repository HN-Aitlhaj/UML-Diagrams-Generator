package org.mql.java.uml.models;

public class Entity {
	
	protected String name;
	
	
	public Entity() {
	}

	public Entity(String name) {
		super();
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
			if(getName().equals(((Entity)obj).getName()))
				return true;
		
		return super.equals(obj);
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

	public String getPackageName() {
		if(name.isEmpty() || name.lastIndexOf('.') < 0)
			return "";
		else return name.substring(0, name.lastIndexOf('.') );
	}
	
	@Override
	public String toString() {
		return "Entity [name=" + name + "]";
	}
	
	public static void main(String[] args) {
		new Entity();
	}
	
}
