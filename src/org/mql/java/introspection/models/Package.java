package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

public class Package {
	
	private String name;
	private List<Classe> classes;
	private List<Interface> interfaces;
	private List<Enum> enums;
	private List<Annotation> annotations;

	public Package() {
		classes = new Vector<Classe>();
		interfaces = new Vector<Interface>();
		enums = new Vector<Enum>();
		annotations = new Vector<Annotation>();
	}

	public Package(String name, List<Classe> classes, List<Interface> interfaces, List<Enum> enums,
			List<Annotation> annotations) {
		super();
		this.name = name;
		this.classes = classes;
		this.interfaces = interfaces;
		this.enums = enums;
		this.annotations = annotations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public String toString() {
		return "Package [name=" + name + ", classes=" + classes + ", interfaces=" + interfaces + ", enums=" + enums
				+ ", annotations=" + annotations + "]";
	}

}
