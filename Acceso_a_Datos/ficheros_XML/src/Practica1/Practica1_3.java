package Practica1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;

public class Practica1_3 {
	public static void main(String[] args) {
		readXML();
	}

	public static void readXML() {
		Path path = Path.of("personas.xml");
		File xml = path.toFile();
		DocumentBuilder builder = createBuilder();
		Document document = null;
		try {
			document = builder.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodeList = document != null ? document.getElementsByTagName("Personas").item(0).getChildNodes() : null;
		trateNodes(nodeList);
	}

	public static DocumentBuilder createBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder;
	}

	public static void trateNodes(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				switch (nodo.getNodeName()) {
					case "Persona":
						String dni = nodo.getAttributes().getNamedItem("dni").getNodeValue();
						String nombre = nodo.getAttributes().getNamedItem("nombre").getNodeValue();
						String apellidos = nodo.getAttributes().getNamedItem("apellidos").getNodeValue();
						System.out.println(nodo.getNodeName() + "\t\t" + dni + "\t\t" + nombre + "\t\t" + apellidos);
						NodeList nodeListChildren = nodo.getChildNodes();
						trateNodes(nodeListChildren);
						break;
					case "Direccion":
					case "Telefono":
						System.out.println("\t" + nodo.getNodeName() + " -> " + nodo.getTextContent());
						break;
				}
			}
		}
	}
}
