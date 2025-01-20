package org.mql.java.uml.introspection.services;

import java.nio.file.Paths;
import java.util.Vector;

import org.mql.java.uml.models.Project;

public class ProjectExplorer {
	
	public static Project scan(String path) {
		
		path = path.endsWith("src")? path : path.replace("src" ,"bin");
		path = path.endsWith("bin")? path : path + "\\bin";
		
		Project project = new Project();
		project.setName(Paths.get(path).getParent().getFileName().toString());
		project.setBinPath(path);
		project.setPackages(PackageExplorer.scan(path, "" ,new Vector<>()));
		//StringMapper()
//		List<Relation> relations = new RelationMapper().get;
//		project.setRelations(relations);
//		relations.add(new Relation());
		//project.getRelations().get(0).setSourceMultiplicity(RelationMapper.checkMultiplicity());;
		
		return project;
	}
	
//	public List<Class<?>> getClasses(Project proj){
//		List<Class<?>> cls = new Vector<Class<?>>();
//		for (Package pack : proj.getPackages()) {
//			cls.addAll(pack.getClasses());
//		}
//		return null;
//		
//	}

}
