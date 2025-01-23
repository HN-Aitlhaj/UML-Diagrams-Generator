package org.mql.java.uml.introspection.services;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Relation;

public class ProjectExplorer {
	
	public static String binPath; 
	
	public static Project scan(String path) {
		
		path = path.endsWith("src")? path : path.replace("src" ,"bin");
		path = path.endsWith("bin")? path : path + "\\bin";
		
		Project project = new Project();
		project.setName(Paths.get(path).getParent().getFileName().toString());
		project.setBinPath(path);
		project.setPackages(PackageExplorer.scan(path, "" ,new Vector<>()));
		
		
		HashMap<String,List<Relation>> relations = new HashMap<String, List<Relation>>();
		for (Package pack : project.getPackages()) {
			for (Entity classe : pack.getClasses()) {
				relations.putAll(RelationMapper.scan(classe,project));
			}
			for (Entity classe : pack.getInterfaces()) {
				relations.putAll(RelationMapper.scan(classe,project));
			}
		}
		
		project.setRelations(relations);
		
		return project;
	}
	
	public List<Entity> getEntities(Project proj){
		List<Entity> listEntity = new Vector<>();
		for (Package pack : proj.getPackages()) {
			listEntity.addAll(pack.getClasses());
			listEntity.addAll(pack.getInterfaces());
			listEntity.addAll(pack.getAnnotations());
			listEntity.addAll(pack.getEnums());
		}
		return listEntity;
		
	}

}
