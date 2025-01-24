package org.mql.java.introspection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Relation;

class RelationMapperTest {

	
	@Test
	void test() {
		String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
		//path = System.getProperty("java.class.path");
		System.out.println(path);
		Project project = ProjectExplorer.scan("D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\AIT-LHAJ HANANE - UML-Diagrams-Generator\\bin");
		
		System.out.println("\nProject : " + project.getName());

		
		for (Map.Entry<String, List<Relation>> entry : project.getRelations().entrySet()) {
			String key = entry.getKey();
			List<Relation> val = entry.getValue();
			System.out.println("key : " + key);
			for (Relation relation : val) {
				System.out.println("relation : " + relation.getType() + " src : " + relation.getSouceClass().getName() + " --> " + relation.getDestinationClass().getName());
					
			}
		}
		
		assertNotNull(project);
	}
}
