package Practica5;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Path path = Path.of("arraysDeObjetosVariados.xml");
		File file = path.toFile();
		String opcion;
		do {
			menu();
			opcion = sc.nextLine();
			switch (opcion) {
				case "1":
					writeFile(file);
					break;
				case "2":
					readFile(file);
					break;
				case "3":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!opcion.equals("3"));
	}

	public static void menu() {
		System.out.println("1. Escribir fichero\n2. Leer fichero\n3. Salir\nOpción: ");
	}

	public static void writeFile(File file) {
		ArrayList<Gimnasio> gimnasios = new ArrayList<>();
		gimnasios.add(new Gimnasio("Gimnasio 1", "Calle General 1", 100));
		gimnasios.add(new Gimnasio("Gimnasio 2", "Calle Plaza Mayor", 200));
		gimnasios.add(new Gimnasio("Gimnasio 3", "Calle General 2", 300));
		ArrayList<Videojuego> videojuegos = new ArrayList<>();
		videojuegos.add(new Videojuego("Videojuego 1", "Acción", 100));
		videojuegos.add(new Videojuego("Videojuego 2", "Aventura", 200));
		videojuegos.add(new Videojuego("Videojuego 3", "Deportes", 300));
		Contenedor contenedor = new Contenedor(gimnasios, videojuegos);
		try {
			JAXBContext context = JAXBContext.newInstance(Contenedor.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(contenedor, file);
			System.out.println("Objetos guardados en " + file.getAbsolutePath());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(Contenedor.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Contenedor contenedor = (Contenedor) unmarshaller.unmarshal(file);
			ArrayList<Gimnasio> gimnasios = contenedor.getGimnasios();
			ArrayList<Videojuego> videojuegos = contenedor.getVideojuegos();
			System.out.println("Objetos leídos de " + file.getAbsolutePath());
			for (Gimnasio gimnasio : gimnasios) {
				System.out.println(gimnasio.toString());
			}
			for (Videojuego videojuego : videojuegos) {
				System.out.println(videojuego.toString());
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
