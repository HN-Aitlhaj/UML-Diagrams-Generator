package org.mql.java.xml;

import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {//façade : objectif : parser toute en cachant la complexité
	private Node node;
	
	public XMLNode(Node node) {
		super();
		this.node = node;
	}
	
	public XMLNode(String source) {
		//Factory abstract builder : design patterns en semestre 2
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();

		try {
			DocumentBuilder builder =  factory.newDocumentBuilder(); //factory : bin - newDocumentBuilder() : method
			Document document = builder.parse(source);
			node = document.getFirstChild(); //il y a diff entre root et rootElement //il detecte auusi coments
			//elment est un cas particulier ds neuds
			while(node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling(); //permet de recuperer le frère à droite		
			}
		
			System.out.println(node.getNodeName() + " : " + node.getNodeType());
			
			
			NodeList children = node.getChildNodes();
			int n = children.getLength();
			for (int i = 0; i < n; i++) {
				Node child = children.item(i);
				if(child.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(" - " + child.getNodeName());
					
				}
			}
			
			
			//System.out.println(Node.ELEMENT_NODE);
			//System.out.println(Node.COMMENT_NODE);
			//node name - node value(ne marche pas pour tous (marche sur textuel) ) - node type
			
			
		}catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	public XMLNode[] children() {
		List<XMLNode> list = new Vector<XMLNode>();
		NodeList n1 = node.getChildNodes();
		int n = n1.getLength();
		for (int i = 0; i < n; i++) {
			Node child = n1.item(i);
			if(child.getNodeType() == Node.ELEMENT_NODE) {
				list.add(new XMLNode(child));
			}
		}
		
		XMLNode t[] = new XMLNode[list.size()];
		list.toArray(t);
		return t;
	}
	
	public XMLNode child(String name) {

		NodeList n1 = node.getChildNodes();
		int n = n1.getLength();
		for (int i = 0; i < n; i++) {
			Node child = n1.item(i);
			if(child.getNodeName().equals(name)) {
				return new XMLNode(child);
			}
		}
		
		return null;
	}
	
	public String getName() {
		return node.getNodeName();
	}
	
	public String getValue() {
		//on peut récupérer la valeur de noeud seulment si na pas de fils et sa valeur est textuel
		NodeList list = node.getChildNodes();
		if(list.getLength() == 1 && list.item(0).getNodeType() == Node.TEXT_NODE) {
			return list.item(0).getNodeValue();
		}
		
		return null;
	}
	
	public String attribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		return atts.getNamedItem(name) != null ? atts.getNamedItem(name).getNodeValue() : null;
		//Optionnal dans java.util : peut etre au lieu de null
	}

	public int intAttribute(String name) {
		String att = attribute(name);
		try {
			return Integer.parseInt(att);
			
		}catch (Exception e) {
			return -1;
		}
	}
	
}
