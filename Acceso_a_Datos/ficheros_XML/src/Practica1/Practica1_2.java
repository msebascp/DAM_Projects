package Practica1;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Practica1_2 {
	public static void main(String[] args) {
		writeXML();
	}

	public static void writeXML() {
		DocumentBuilder builder = createBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, null, null);
		document.setXmlVersion("1.0");
		Element alumnos = document.createElement("Alumnos");
		document.appendChild(alumnos);
		Element alumno = document.createElement("Alumno");
		alumno.setAttribute("nombre", "Juan");
		alumno.setAttribute("edad", "30");
		Element direccion = document.createElement("Direccion");
		direccion.setTextContent("Calle Mayor, 1");
		Element telefono = document.createElement("Telefono");
		telefono.setTextContent("123456789");
		alumno.appendChild(direccion);
		alumno.appendChild(telefono);
		alumnos.appendChild(alumno);

		Source origen = new DOMSource(document);
		Result destino = new StreamResult(new File("alumnos.xml"));
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(origen, destino);
		} catch (Exception e) {
			e.printStackTrace();
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
}
