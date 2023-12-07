package Practica6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Controladora extends DefaultHandler {
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Comienzo del documento XML");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("Fin del documento XML");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (qName) {
			case "Tests":
				System.out.println(qName);
				break;
			case "Test":
				String id = attributes.getValue("TestId");
				String tipo = attributes.getValue("TestType");
				System.out.println("\t" + qName + ": " + id + " <--> " + tipo);
				break;
			case "Name":
				System.out.print("\t\tNombre del Test:\t");
				break;
			case "CommandLine":
				System.out.print("\t\t" + qName + ":\t");
				break;
			case "Input":
				System.out.print("\t\tDatos de entrada:\t");
				break;
			case "Output":
				System.out.print("\t\tDatos de salida:\t");
				break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String texto = new String(ch, start, length).trim();
		if (!texto.isEmpty()) {
			System.out.println(texto);
		}
	}
}
