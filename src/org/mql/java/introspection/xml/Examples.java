package org.mql.java.introspection.xml;

import org.mql.java.models.Project;

public class Examples {

	public Examples(){
		exp01();
	}
	
	void exp01(){
		ProjectXmlParser parser = new ProjectXmlParser();
		Project project = parser.parse("resources/project.xml");
		
		System.out.println(project);	
	}
	
	public static void main(String[] args) {
		new Examples();

	}

}
