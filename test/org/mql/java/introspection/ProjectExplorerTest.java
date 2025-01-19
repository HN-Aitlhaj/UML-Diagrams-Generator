package org.mql.java.introspection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mql.java.introspection.parser.ProjectExplorer;
import org.mql.java.models.Package;
import org.mql.java.models.Project;

class ProjectExplorerTest {

	@Test
	void test() {
		String path = "D:\\MQL\\JAVA\\eclipse-workspace_2024-2025\\p03-reflection-and-annotations\\bin";
		//path = System.getProperty("java.class.path");
		System.out.println(path);
		Project project = ProjectExplorer.scan(path);
		
		System.out.println("\nProject : " + project.getName());
		for(Package packge: project.getPackages()) {
			System.out.println(packge);
		}
		
		assertNotNull(project);
	}

}
