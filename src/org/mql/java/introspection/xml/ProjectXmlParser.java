package org.mql.java.introspection.xml;

import java.util.List;
import java.util.Vector;

import org.mql.java.models.Package;
import org.mql.java.models.Project;

/*
 * DOM PARSER
 */
public class ProjectXmlParser { 
	
	public ProjectXmlParser() {
	}
	
	//Mapping XML/Object
	public Project parse(String source){
		Project proj = new Project();
		List<Package> packages = new Vector<Package>();
		XMLNode root = new XMLNode(source); //return project
		XMLNode t[] = root.children(); //name - packages
		System.out.println(t.length);
		proj.setName(t[0].getValue());

		
		for(XMLNode node : t[1].children()) { //foreach package => (t[1].children() = <packages>)
			Package packge = new Package();
//			XMLNode props[] = node.children();
//			System.out.println(props[0].getValue());
			
			String name = node.child("name").getValue();
			packge.setName(name);
			
//			proj.add(a);
//			a.setName(node.child("name").getValue());
/*			//a.setClasses(node.child("classe").getValue());
			//a.setInterfaces(node.child("name").getValue());
			
//			XMLNode date= node.child("");
//			a.setBirthday(new Date(
//					date.intAttribute("day"),date.intAttribute("month"),date.intAttribute("year")
//					));
*/	
			packages.add(packge);
		}
		proj.setPackages(packages);
		return proj;
		
	}
}
