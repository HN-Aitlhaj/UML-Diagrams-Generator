package org.mql.java.uml.introspection.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.uml.models.Project;
import org.mql.java.uml.models.Annotation;
import org.mql.java.uml.models.Classe;
import org.mql.java.uml.models.Constructeur;
import org.mql.java.uml.models.Enum;
import org.mql.java.uml.models.Field;
import org.mql.java.uml.models.Interface;
import org.mql.java.uml.models.Method;
import org.mql.java.uml.models.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMLGenerator {

	public void generateXML(Project project, String filePath) {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			
			Element projectElement = document.createElement("project");
			projectElement.setAttribute("name", project.getName());
			projectElement.setAttribute("binPath", project.getBinPath());
			document.appendChild(projectElement);


			for (Package pkg : project.getPackages()) {
				Element packageElement = document.createElement("package");
				packageElement.setAttribute("name", pkg.getName());
				projectElement.appendChild(packageElement);

				// Add <classes>
				Element classesElement = document.createElement("classes");
				packageElement.appendChild(classesElement);

				for (Classe classe : pkg.getClasses()) {
					Element classElement = document.createElement("classe");
					classElement.setAttribute("name", classe.getName());
					classElement.setAttribute("modifier", classe.getModifier().toString());
					classesElement.appendChild(classElement);

					// Add <superClass>
					Element superClassElement = document.createElement("superClass");
					superClassElement.setTextContent(classe.getSuperClass().getName());
					classElement.appendChild(superClassElement);

					Element interfacesElement = document.createElement("interfaces");
					classElement.appendChild(interfacesElement);
					for (Interface interf : classe.getInterfaces()) {
						Element interfaceElement = document.createElement("interface");
						interfaceElement.setTextContent(interf.getName());
						interfacesElement.appendChild(interfaceElement);
					}

					
					Element fieldsElement = document.createElement("fields");
					classElement.appendChild(fieldsElement);
					for (Field field : classe.getFields()) {
						Element fieldElement = document.createElement("field");
						fieldElement.setAttribute("name", field.getName());
						fieldElement.setAttribute("type", field.getType().getName());
						fieldElement.setAttribute("modifier", field.getModifier().toString());
						fieldsElement.appendChild(fieldElement);
					}

					
					Element constructorsElement = document.createElement("constructeurs");
					classElement.appendChild(constructorsElement);
					for (Constructeur constructor : classe.getConstructors()) {
						Element constructorElement = document.createElement("constructeur");
						constructorElement.setAttribute("name", constructor.getName());
						constructorElement.setAttribute("modifier", constructor.getModifier().toString());
						constructorsElement.appendChild(constructorElement);

						for (Class<?> parameter : constructor.getParameterTypes()) {
							Element parameterElement = document.createElement("parameterType");
							parameterElement.setTextContent(parameter.getName());
							constructorElement.appendChild(parameterElement);
						}
					}

					
					Element methodsElement = document.createElement("methods");
					classElement.appendChild(methodsElement);
					for (Method method : classe.getMethods()) {
						Element methodElement = document.createElement("method");
						methodElement.setAttribute("name", method.getName());
						methodElement.setAttribute("modifier", method.getModifier().toString());
						methodElement.setAttribute("returnType", method.getReturnType());
						methodsElement.appendChild(methodElement);

						for (Class<?> parameter : method.getParameterTypes()) {
							Element parameterElement = document.createElement("parameterType");
							parameterElement.setTextContent(parameter.getName());
							methodElement.appendChild(parameterElement);
						}
					}

					// Add <internClasses>
					Element internClassesElement = document.createElement("internClasses");
					classElement.appendChild(internClassesElement);
					for (Classe internClass : classe.getInternClasses()) {
						Element internClassElement = document.createElement("classe");
						internClassElement.setTextContent(internClass.getName());
						internClassesElement.appendChild(internClassElement);
					}
				}

				// Add <interfaces> for the package
				Element packageInterfacesElement = document.createElement("interfaces");
				packageElement.appendChild(packageInterfacesElement);
				for (Interface iface : pkg.getInterfaces()) {
					Element interfaceElement = document.createElement("interface");
					interfaceElement.setAttribute("name", iface.getName());
					packageInterfacesElement.appendChild(interfaceElement);

					// Add <extendedInterfaces>
					Element extendedInterfacesElement = document.createElement("extendedInterfaces");
					interfaceElement.appendChild(extendedInterfacesElement);
					for (Interface extendedInterface : iface.getExtendedInterfaces()) {
						Element extInterfaceElement = document.createElement("interface");
						extInterfaceElement.setTextContent(extendedInterface.getName());
						extendedInterfacesElement.appendChild(extInterfaceElement);
					}

					// Add <fields> to the interface
					Element ifaceFieldsElement = document.createElement("fields");
					interfaceElement.appendChild(ifaceFieldsElement);
					for (Field field : iface.getFields()) {
						Element fieldElement = document.createElement("field");
						fieldElement.setAttribute("name", field.getName());
						fieldElement.setAttribute("type", field.getType().getName());
						fieldElement.setAttribute("modifier", field.getModifier().toString());
						ifaceFieldsElement.appendChild(fieldElement);
					}

					// Add <methods> to the interface
					Element ifaceMethodsElement = document.createElement("methods");
					interfaceElement.appendChild(ifaceMethodsElement);
					for (Method method : iface.getMethods()) {
						Element methodElement = document.createElement("method");
						methodElement.setAttribute("name", method.getName());
						methodElement.setAttribute("modifier", method.getModifier().toString());
						methodElement.setAttribute("returnType", method.getReturnType());
						ifaceMethodsElement.appendChild(methodElement);

						for (Class<?> parameter : method.getParameterTypes()) {
							Element parameterElement = document.createElement("parameterType");
							parameterElement.setTextContent(parameter.getName());
							methodElement.appendChild(parameterElement);
						}
					}
				}

				
				Element enumsElement = document.createElement("enums");
				packageElement.appendChild(enumsElement);
				for (Enum enumModel : pkg.getEnums()) {
					Element enumElement = document.createElement("enum");
					enumElement.setAttribute("name", enumModel.getName());
					enumsElement.appendChild(enumElement);

					for (String value : enumModel.getValues()) {
						Element valueElement = document.createElement("value");
						valueElement.setTextContent(value);
						enumElement.appendChild(valueElement);
					}
				}

				// Add <annotations>
				Element annotationsElement = document.createElement("annotations");
				packageElement.appendChild(annotationsElement);
				for (Annotation annotation : pkg.getAnnotations()) {
					Element annotationElement = document.createElement("annotation");
					annotationElement.setAttribute("name", annotation.getName());
					annotationsElement.appendChild(annotationElement);

					for (Method value : annotation.getValues()) {
						Element valueElement = document.createElement("value");
						valueElement.setAttribute("name", value.getName());
						valueElement.setAttribute("returnType", value.getReturnType());
						annotationElement.appendChild(valueElement);
					}
				}
			}

			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));

			transformer.transform(source, result);
			System.out.println("XML file generated successfully: " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
