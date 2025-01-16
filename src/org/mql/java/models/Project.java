package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Project {

	private String name;
	private List<Package> packages;
	
	public Project() {
		packages = new Vector<Package>();
	}
	
	public Project(String name, List<Package> packages) {
		super();
		this.name = name;
		this.packages = packages;
	}

	public void addPackage(Package pack) {
		packages.add(pack);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", packages=" + packages + "]";
	}

	
}
