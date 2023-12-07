package Practica3;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) {
		readXML();
	}

	public static void readXML() {
		Path path = Path.of("book.xml");
		File xml = path.toFile();

		DocumentBuilder builder = createBuilder();
		Document document = null;

		try {
			document = builder.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodeList = document != null ? document.getElementsByTagName("Catalog").item(0).getChildNodes() : null;
		if (nodeList != null) {
			trateNodes(nodeList);
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

	public static void trateNodes(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				switch (nodo.getNodeName()) {
					case "Book":
						String id = nodo.getAttributes().getNamedItem("id").getNodeValue();
						System.out.println("Book_id: " + id);
						NodeList nodeListChildren = nodo.getChildNodes();
						trateNodes(nodeListChildren);
						break;
					case "Author":
					case "Title":
					case "Genre":
					case "Price":
						System.out.println("\t" + nodo.getNodeName() + " -> " + nodo.getTextContent());
						break;
					case "Description":
						System.out.println("\t" + nodo.getNodeName() + ": " + nodo.getTextContent());
						break;
				}
			}
		}
	}
}
