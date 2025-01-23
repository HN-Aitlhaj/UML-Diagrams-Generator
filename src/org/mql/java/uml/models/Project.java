package org.mql.java.uml.models;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Project {

	private String name;
	private String binPath;
	private List<Package> packages;
	private HashMap<String,List<Relation>> relations;
	
	public Project() {
		packages = new Vector<Package>();
		relations = new HashMap<String, List<Relation>>();
	}
	
	public Project(String name, String binPath, List<Package> packages,HashMap<String, List<Relation>> relations) {
		super();
		this.name = name;
		this.binPath = binPath;
		this.packages = packages;
		this.relations = relations;
	}

	public void addPackage(Package pack) {
		packages.add(pack);
	}

	public String getName() {
		return name;
	}
	
	public String getBinPath() {
		return binPath;
	}

	public void setBinPath(String binPath) {
		this.binPath = binPath;
		this.name = Paths.get(binPath).getParent().getFileName().toString();
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

	public HashMap<String, List<Relation>> getRelations() {
		return relations;
	}

	public void setRelations(HashMap<String, List<Relation>> relations) {
		this.relations = relations;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", binPath=" + binPath + ", packages=" + packages + ", relations=" + relations
				+ "]";
	}
	
}
