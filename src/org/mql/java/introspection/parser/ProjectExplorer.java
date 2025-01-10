package org.mql.java.introspection.parser;

import java.nio.file.Paths;
import java.util.Vector;

import org.mql.java.models.Project;
import org.mql.java.models.Package;

public class ProjectExplorer { //explorer le projet  et l'anayser
	

	public ProjectExplorer() {
		String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations";
		//path = System.getProperty("java.class.path");
		System.out.println(path);
		
		Project project = scan(path);
		
		System.out.println("\nProject : " + project.getName());
		for(Package packge: project.getPackages()) {
			System.out.println(packge);
		}
	}
	
	public Project scan(String path) {
		PackageExplorer packageExplorer = new PackageExplorer();
		Project project = new Project();
		 
		project.setName(Paths.get(path).toAbsolutePath().toString());

		path = path.endsWith("src")? path : path.replace("src" ,"bin");
		path = path.endsWith("bin")? path : path + "\\bin";
		project.setPackages(packageExplorer.scan(path, "" ,new Vector<>()));
		return project;
	}
	
	public static void main(String[] args) {
		new ProjectExplorer();
	}

}
