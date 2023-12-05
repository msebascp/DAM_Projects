package Practica3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	//Creamos un array dinámico de objetos de tipo Persona
	public static ArrayList<Persona> personas = new ArrayList<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Creamos el directorio ficheros y dentro el fichero con el que trabajaremos
		File directory = new File("ficheros");
		if (directory.mkdir()) {
			System.out.println("Directorio creado");
		} else {
			System.out.println("El directorio ya existe");
		}
		File file = new File("ficheros", " fichero_binario_practica3");
		if (file.createNewFile()) {
			System.out.println("Fichero creado");
		} else {
			System.out.println("El fichero ya existe");
		}
		addPersonas();
		if (!personas.isEmpty()) {
			saveObject(file);
			readObject(file);
		}
		scanner.close();
	}

	/**
	 * Método para añadir personas al array dinámico de personas
	 */
	public static void addPersonas() {
		System.out.print("¿Cuántas personas quieres añadir?: ");
		int numPersonas = readInteger();
		scanner.nextLine();
		for (int i = 0; i < numPersonas; i++) {
			System.out.println("Persona " + (i + 1) + ":");
			System.out.print("Nombre:");
			String nombre = scanner.nextLine();
			System.out.print("Edad:");
			int edad = readInteger();
			scanner.nextLine();
			System.out.print("Dirección:");
			String direccion = scanner.nextLine();
			personas.add(new Persona(nombre, edad, direccion));
		}
	}

	public static int readInteger() {
		boolean correcto = false;
		int num = 0;
		do {
			try {
				num = scanner.nextInt();
				correcto = true;
			} catch (Exception e) {
				System.out.print("Error al introducir datos numéricos, vuelve a intentarlo: ");
				scanner.nextLine();
			}
		} while (!correcto);
		return num;
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

	public static void readObject(File file) throws ClassNotFoundException {
		try {

			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Persona persona;
			System.out.println("Recuperando personas del fichero:");
			while (fileInputStream.available() > 0) {
				persona = (Persona) objectInputStream.readObject();
				System.out.println(persona.toString());
			}
			objectInputStream.close();
		} catch (IOException e) {
			System.out.println("Error en recuperarObjeto");
		}
	}
}
