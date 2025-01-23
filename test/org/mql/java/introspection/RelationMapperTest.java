package org.mql.java.introspection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mql.java.uml.introspection.services.ProjectExplorer;
import org.mql.java.uml.introspection.services.RelationMapper;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Relation;

class RelationMapperTest {

	@Test
	void testToString() {
		
		Classe cls = new Classe();

        System.out.println(RelationMapper.toString(cls));
        //assertTrue(false);
	}
	
	@Test
	void test() {
		String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
		//path = System.getProperty("java.class.path");
		System.out.println(path);
		Project project = ProjectExplorer.scan(path);
		
		System.out.println("\nProject : " + project.getName());
//		for(List<Relation> relation: project.getRelations().values()) {
//			for (Relation relation2 : relation) {
//			System.out.println(relation2);
//				
//			}
//			
//		}
		
		for (Map.Entry<String, List<Relation>> entry : project.getRelations().entrySet()) {
			String key = entry.getKey();
			List<Relation> val = entry.getValue();
			System.out.println("key : " + key);
			for (Relation relation2 : val) {
				System.out.println(relation2);
					
			}
		}
		
		assertNotNull(project);
	}
}
