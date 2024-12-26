package org.mql.java.introspection.models;

import java.util.List;

public class Package {
	
	private String name;
	private List<Classe> classes;
	private List<Interface> interfaces;
	private List<Enum> enums;
	private List<Annotation> annotations;

	public Package() {
		
	}

}
