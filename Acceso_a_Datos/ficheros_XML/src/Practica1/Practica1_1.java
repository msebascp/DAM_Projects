package Practica1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;

public class Practica1_1 {
	public static void main(String[] args) {
		readXML();
	}

	public static void readXML() {
		Path path = Path.of("ms.xml");
		File xml = path.toFile();

		DocumentBuilder builder = createBuilder();
		Document document = null;

		try {
			document = builder.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodeList = document != null ? document.getElementsByTagName("Tests").item(0).getChildNodes() : null;
		if (nodeList != null) {
			treatNodes(nodeList);
		}
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

	public static void treatNodes(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				switch (nodo.getNodeName()) {
					case "Test":
						String id = nodo.getAttributes().getNamedItem("TestId").getNodeValue();
						String type = nodo.getAttributes().getNamedItem("TestType").getNodeValue();
						System.out.println(nodo.getNodeName() + "\t\t" + id + "\t\t" + type);
						NodeList nodeListChildren = nodo.getChildNodes();
						treatNodes(nodeListChildren);
						break;
					case "Name":
					case "CommandLine":
						System.out.println("\t" + nodo.getNodeName() + " -> " + nodo.getTextContent());
						break;
					case "Input":
						System.out.println("\t" + nodo.getNodeName() + ": " + nodo.getTextContent());
						break;
					case "Output":
						System.out.println("\t" + nodo.getNodeName() + ": " + nodo.getTextContent() + "\n");
						break;
				}
			}
		}
	}
}
