package org.mql.java.uml.introspection.xml;

import org.mql.java.uml.models.Project;

public class Examples {

	public Examples(){
		XMLParser();
	}
	
	void XMLParser(){
		ProjectXmlParser parser = new ProjectXmlParser();
		Project project = parser.parse("resources/project.xml");
		
		System.out.println(project);	
	}
	
	void XMLGenerator(Project project) {
		XMLGenerator generator = new XMLGenerator();
		generator.generateXML(project, "resources/generatedProject.xml");
	}
	
	public static void main(String[] args) {
		new Examples();

	}

}
