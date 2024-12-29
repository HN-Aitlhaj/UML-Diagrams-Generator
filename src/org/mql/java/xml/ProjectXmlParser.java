package org.mql.java.xml;

import java.util.List;
import java.util.Vector;

import org.mql.java.models.Package;

/*
 * DOM PARSER
 */
public class ProjectXmlParser { 
	
	public ProjectXmlParser() {
	}
	
	//Mapping XML/Object
	public List<Package> parse(String source){
		
		List<Package> packages = new Vector<Package>();
		XMLNode root = new XMLNode(source);
		XMLNode t[] = root.children();
		System.out.println(t.length);
		
		for(XMLNode node : t) {
//			XMLNode props[] = node.children();
//			System.out.println(props[0].getValue());
			
//			int id = node.intAttribute("id");
//			String name = node.child("name").getValue();
			
			Package a = new Package();
			packages.add(a);
			a.setName(node.child("name").getValue());
/*			//a.setClasses(node.child("classe").getValue());
			//a.setInterfaces(node.child("name").getValue());
			
//			XMLNode date= node.child("");
//			a.setBirthday(new Date(
//					date.intAttribute("day"),date.intAttribute("month"),date.intAttribute("year")
//					));
*/	
		}
		return packages;
		
	}
}
