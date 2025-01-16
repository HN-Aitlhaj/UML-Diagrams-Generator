package org.mql.java.introspection.parser;

import java.nio.file.Paths;
import java.util.Vector;

import org.mql.java.models.Project;

public class ProjectExplorer {
	
	public ProjectExplorer() {
	}
	
	public Project scan(String path) {
		PackageExplorer packageExplorer = new PackageExplorer();
		
		path = path.endsWith("src")? path : path.replace("src" ,"bin");
		path = path.endsWith("bin")? path : path + "\\bin";
		
		Project project = new Project();
		project.setName(Paths.get(path).getParent().getFileName().toString());

		project.setPackages(packageExplorer.scan(path, "" ,new Vector<>()));
		return project;
	}

}
