package org.mql.java.uml.introspection.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Method;
import org.mql.java.uml.models.Package;
import org.mql.java.uml.models.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMIGenerator {
	
	
	public void generateXMI(Project project, String filePath) {
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			Element xmiElement = document.createElement("XMI");
			xmiElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
			xmiElement.setAttribute("xmlns:uml", "http://www.omg.org/spec/UML/20090901");
			xmiElement.setAttribute("version", "2.1");
			document.appendChild(xmiElement);

			Element modelElement = document.createElement("uml:Model");
			modelElement.setAttribute("name", project.getName());
			modelElement.setAttribute("xmi:type", "uml:Model");
			xmiElement.appendChild(modelElement);

			for (Package pack : project.getPackages()) {
				Element packageElement = document.createElement("packagedElement");
				packageElement.setAttribute("name", pack.getName());
				packageElement.setAttribute("xmi:type", "uml:Package");
				modelElement.appendChild(packageElement);

				for (Classe classe : pack.getClasses()) {
					Element classElement = document.createElement("packagedElement");
					classElement.setAttribute("name", classe.getName());
					classElement.setAttribute("xmi:type", "uml:Class");
					classElement.setAttribute("visibility", classe.getModifier().toString());
					packageElement.appendChild(classElement);

					if (classe.getSuperClass() != null) {
						Element generalizationElement = document.createElement("generalization");
						generalizationElement.setAttribute("general", classe.getSuperClass().getName());
						classElement.appendChild(generalizationElement);
					}

					for (Field field : classe.getFields()) {
						Element fieldElement = document.createElement("ownedAttribute");
						fieldElement.setAttribute("name", field.getName());
						fieldElement.setAttribute("type", field.getType().getName());
						fieldElement.setAttribute("visibility", field.getModifier().toString());
						classElement.appendChild(fieldElement);
					}

					for (Method method : classe.getMethods()) {
						Element methodElement = document.createElement("ownedOperation");
						methodElement.setAttribute("name", method.getName());
						methodElement.setAttribute("visibility", method.getModifier().toString());
						methodElement.setAttribute("returnType", method.getReturnType());
						classElement.appendChild(methodElement);

						for (Class<?> parameter : method.getParameterTypes()) {
							Element parameterElement = document.createElement("ownedParameter");
							parameterElement.setAttribute("type", parameter.getName());
							methodElement.appendChild(parameterElement);
						}
					}

				}

				for (Annotation annotation : pack.getAnnotations()) {
					Element annotationElement = document.createElement("ownedComment");
					annotationElement.setAttribute("name", annotation.getName());
					packageElement.appendChild(annotationElement);

					for (Method value : annotation.getValues()) {
						Element valueElement = document.createElement("value");
						valueElement.setAttribute("name", value.getName());
						valueElement.setAttribute("returnType", value.getReturnType());
						annotationElement.appendChild(valueElement);
					}
				}

				for (Enum enumModel : pack.getEnums()) {
					Element enumElement = document.createElement("packagedElement");
					enumElement.setAttribute("name", enumModel.getName());
					enumElement.setAttribute("xmi:type", "uml:Enumeration");
					packageElement.appendChild(enumElement);

					for (String value : enumModel.getValues()) {
						Element literalElement = document.createElement("ownedLiteral");
						literalElement.setAttribute("name", value);
						enumElement.appendChild(literalElement);
					}
				}

				for (Interface interf : pack.getInterfaces()) {
					Element interfaceElement = document.createElement("packagedElement");
					interfaceElement.setAttribute("name", interf.getName());
					interfaceElement.setAttribute("xmi:type", "uml:Interface");
					packageElement.appendChild(interfaceElement);
				}
			}

			// to XMI file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));

			transformer.transform(source, result);
			System.out.println("XMI file generated successfully: " + filePath);
		} catch (ParserConfigurationException | javax.xml.transform.TransformerException e) {
			e.printStackTrace();
		}
	}
}
