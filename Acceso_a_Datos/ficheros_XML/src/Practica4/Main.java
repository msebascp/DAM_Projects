package Practica4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("objetoVariados.xml");
		File file = path.toFile();
		saveObjects(file);
		readObjects(file);
	}

	public static void saveObjects(File file) {
		Automovil auto = new Automovil("Ford", "Mustang", 2015);
		Motocicleta moto = new Motocicleta("Yamaha", "R1", 2016);
		Ordenador ordenador = new Ordenador("HP", "Pavilion", 2017);
		SmartPhone smartphone = new SmartPhone("Samsung", "Galaxy S8", 2018);
		Contenedor contenedor = new Contenedor(auto, moto, ordenador, smartphone);
		try {
			JAXBContext context = JAXBContext.newInstance(Contenedor.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(contenedor, file);
			System.out.println("Objetos guardados en " + file.getAbsolutePath());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readObjects(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(Contenedor.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Contenedor contenedor = (Contenedor) unmarshaller.unmarshal(file);
			Automovil auto = contenedor.getAutomovil();
			Motocicleta moto = contenedor.getMotocicleta();
			Ordenador ordenador = contenedor.getOrdenador();
			SmartPhone smartphone = contenedor.getSmartphone();
			System.out.println("Objetos leídos de " + file.getAbsolutePath());
			System.out.println(auto.toString());
			System.out.println(moto.toString());
			System.out.println(ordenador.toString());
			System.out.println(smartphone.toString());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
