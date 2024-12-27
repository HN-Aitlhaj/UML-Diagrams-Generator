package org.mql.java.xml;

/*
 * Parseurs XML:
 * - DOM
 * - SAX (Simple API for XML) avec ce parser je peux ne mettre tous au memoire
 * - StAX = basé sur streaming pour les docs de volumineux tailles et sur le swaping mémoire disk dur
 * */
public class Examples {

	public Examples(){
		exp01();
	}
	
	void exp01(){
//		ProjectXmlParser parser = new ProjectXmlParser();
//		Project project = parser.parse("resources/project.xml"); //il detecte aussi les commentaires
//		
//			System.out.println(project);
//		
	}
	
	public static void main(String[] args) {
		new Examples();

	}

}
