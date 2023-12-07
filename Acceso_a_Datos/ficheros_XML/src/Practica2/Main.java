package Practica2;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		String option;
		do {
			menu();
			option = scanner.nextLine();
			switch (option) {
				case "1":
					createAlumnos();
					break;
				case "2":
					showAlumnos();
					break;
				case "3":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!option.equals("3"));
	}

	public static int askStudentsNumber() {
		System.out.print("Introduce el número de alumnos: ");
		int numAlumnos = scanner.nextInt();
		scanner.nextLine();
		return numAlumnos;
	}

	public static String generate5characterString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			cadena.append((char) (Math.random() * 26 + 'a'));
		}
		return cadena.toString();
	}

	public static String generatePassword() {
		// Generar una cadena de 20 caracteres aleatorios, incluyendo números y letras
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			int num = (int) (Math.random() * 62);
			if (num < 10) {
				cadena.append(num);
			} else if (num < 36) {
				cadena.append((char) (num + 55));
			} else {
				cadena.append((char) (num + 61));
			}
		}
		return cadena.toString();
	}

	public static void createAlumnos() {
		int numAlumnos = askStudentsNumber();
		writeXML(numAlumnos);
	}

	public static void writeXML(int numAlumnos) {
		DocumentBuilder builder = createBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, null, null);
		document.setXmlVersion("1.0");
		Element Alumnos = document.createElement("Alumnos");
		document.appendChild(Alumnos);
		for (int i = 0; i < numAlumnos; i++) {
			Element Alumno = document.createElement("Alumno");
			Element Usuario = document.createElement("Usuario");
			Usuario.setTextContent(generate5characterString());
			Element Password = document.createElement("Password");
			Password.setTextContent(generatePassword());
			Alumno.appendChild(Usuario);
			Alumno.appendChild(Password);
			Alumnos.appendChild(Alumno);
		}
		Source origen = new DOMSource(document);
		Result destino = new StreamResult(new File("nuevos_alumnos.xml"));
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(origen, destino);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAlumnos() {
		readXMl();
	}

	public static void readXMl() {
		Path path = Path.of("nuevos_alumnos.xml");
		File xml = path.toFile();
		DocumentBuilder builder = createBuilder();
		Document document = null;
		try {
			document = builder.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodeList = document != null ? document.getElementsByTagName("Alumnos").item(0).getChildNodes() : null;
		if (nodeList != null) {
			trateNodes(nodeList);
		}
	}

	public static void trateNodes(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				switch (nodo.getNodeName()) {
					case "Alumno":
						System.out.println("Alumno " + i + ":");
						NodeList nodeListChildren = nodo.getChildNodes();
						trateNodes(nodeListChildren);
						break;
					case "Usuario":
						System.out.print("\t" + nodo.getNodeName() + " -> " + nodo.getTextContent());
						break;
					case "Password":
						System.out.print("\t" + nodo.getNodeName() + " -> " + nodo.getTextContent() + "\n");
						break;
				}
			}
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

	public static void menu() {
		System.out.println("1. Crear alumnos");
		System.out.println("2. Ver alumnos");
		System.out.println("3. Salir");
		System.out.print("Opción: ");
	}
}
