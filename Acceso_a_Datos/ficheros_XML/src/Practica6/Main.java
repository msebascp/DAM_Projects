package Practica6;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("ms.xml");
		readXML(path);
	}

	public static void readXML(Path path) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			throw new RuntimeException(e);
		}
		XMLReader reader = null;
		try {
			reader = parser.getXMLReader();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
		reader.setContentHandler(new Controladora());
		try {
			reader.parse(String.valueOf(path));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
