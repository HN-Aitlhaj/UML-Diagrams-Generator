package org.mql.java.introspection.parser;

import java.nio.file.Paths;
import java.util.Vector;

import org.mql.java.models.Project;

public class ProjectExplorer {
	
	public static Project scan(String path) {
		
		path = path.endsWith("src")? path : path.replace("src" ,"bin");
		path = path.endsWith("bin")? path : path + "\\bin";
		
		Project project = new Project();
		project.setName(Paths.get(path).getParent().getFileName().toString());
		project.setBinPath(path);
		project.setPackages(PackageExplorer.scan(path, "" ,new Vector<>()));
		//StringMapper()
		//project.setRelations(new Vector<>());
		return project;
	}

}
