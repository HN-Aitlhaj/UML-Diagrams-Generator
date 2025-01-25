package org.mql.java.introspection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.introspection.xml.XMLGenerator;
import org.mql.java.uml.models.Entity;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Project;

class ProjectExplorerTest {

	static Project project;
	@BeforeAll
	static void scanProject() {
		String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
		System.out.println(path);
		//path = System.getProperty("java.class.path");
		project = ProjectExplorer.scan(path);
	}
	
	@Test
	void test() {
		
		System.out.println("\nProject : " + project.getName());
		for(Package packge: project.getPackages()) {
			System.out.println(packge);
		}
		
		assertNotNull(project);
	}
	
	@Test
	void entityTest() {
		
		List<Entity> entityList = ProjectExplorer.getEntities(project);
		for (Entity entity : entityList) {
		    System.out.println("Entity: " + entity.getName());
		}
		
		assertNotNull(entityList);

	}
	
	@Test
	void XMLGenerator() {
		//Project project = new Project("MyProject", "binPa", new Vector<Package>(), null);
		XMLGenerator generator = new XMLGenerator();
		generator.generateXML(project, "resources/generatedProject.xml");
		
		assertNotNull(generator);
	}

}
