package org.mql.java.uml.models;

import java.util.Arrays;

import org.mql.java.uml.enums.Visibility;

public class Modifier {
	
	private Visibility access;
	private String[] nonAccess;
	
	public Modifier(String modifier) {
		if(modifier.contains("public"))
			access = Visibility.PUBLIC;
		else if(modifier.contains("private"))
			access = Visibility.PRIVATE;
		else if(modifier.contains("protected"))
			access = Visibility.PROTECTED;
		else
			access = Visibility.DEFAULT;
		
		nonAccess = modifier.replace(access.toString().toLowerCase(), "").trim().split(" ");
		nonAccess = Arrays.stream(nonAccess)
		        .filter(s -> !s.isEmpty())
		        .toArray(String[]::new);
		
	}
	
	public Modifier(Visibility access, String[] nonAccess) {
		super();
		this.access = access;
		this.nonAccess = nonAccess;
	}
	
	public Visibility getAccess() {
		return access;
	}
	public void setAccess(Visibility access) {
		this.access = access;
	}
	public String[] getNonAccess() {
		return nonAccess;
	}
	public void setNonAccess(String[] nonAccess) {
		this.nonAccess = nonAccess;
	}

	@Override
	public String toString() {
		return access + " " + Arrays.toString(nonAccess);
	}

}
