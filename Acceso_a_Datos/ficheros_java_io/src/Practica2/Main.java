package Practica2;

import java.io.*;
import java.util.ArrayList;

public class Main {
	//Creamos un array dinámico de objetos de tipo Persona
	public static ArrayList<Persona> personas = new ArrayList<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Creo 5 objetos de tipo Persona y las añado al array dinámico
		personas.add(new Persona("Juan", 20, "Calle 1"));
		personas.add(new Persona("Pedro", 21, "Calle 2"));
		personas.add(new Persona("Luis", 22, "Calle 3"));
		personas.add(new Persona("Ana", 23, "Calle 4"));
		personas.add(new Persona("Maria", 24, "Calle 5"));

		// Creamos el directorio ficheros y dentro el fichero con el que trabajaremos
		File directory = new File("ficheros");
		if (directory.mkdir()) {
			System.out.println("Directorio creado");
		} else {
			System.out.println("El directorio ya existe");
		}
		File file = new File("ficheros", " fichero_binario_practica2");
		if (file.createNewFile()) {
			System.out.println("Fichero creado");
		} else {
			System.out.println("El fichero ya existe");
		}
		saveObject(file);
		readObject(file);
	}

	public static void saveObject(File file) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			for (Persona persona : personas) {
				objectOutputStream.writeObject(persona);
			}
			objectOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error en guardarObjeto:");
			System.out.println(e.getMessage());
		}
	}

	public static void readObject(File file) throws IOException, ClassNotFoundException {
		try {

			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Persona persona;
			System.out.println("Recuperando personas del fichero:");
			while (fileInputStream.available() > 0) {
				persona = (Persona) objectInputStream.readObject();
				System.out.println(persona.name + " " + persona.age + " " + persona.address);
			}
			objectInputStream.close();
		} catch (IOException e) {
			System.out.println("Error en recuperarObjeto:");
			System.out.println(e.getMessage());
		}
	}
}
