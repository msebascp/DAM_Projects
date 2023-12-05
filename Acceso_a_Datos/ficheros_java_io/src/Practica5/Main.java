package Practica5;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Creo un fichero de texto
		File file = new File("fichero_practica_5.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("Fichero creado");
			} else {
				System.out.println("Fichero ya existe");
			}
		} catch (IOException e) {
			System.out.println("Error al crear el fichero");
		}
		String option;
		Scanner scanner = new Scanner(System.in);
		// Muestro un menú, 1 para escribir en el fichero, 2 para leerlo y 3 para salir
		do {
			menu();
			option = scanner.nextLine();
			switch (option) {
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
			}
		} while (!option.equals("3"));
	}

	public static void menu() {
		System.out.println("1. Insertar nuevos datos");
		System.out.println("2. Mostrar datos");
		System.out.println("3. Salir");
		System.out.print("Opción: ");
	}

	/**
	 * Método que sobreescribe en un fichero de texto
	 */
	public static void writeFile(File fIle) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Introduce el texto: ");
			String texto = scanner.nextLine();
			FileWriter fileWriter = new FileWriter(fIle, true);
			fileWriter.write(texto + "\n");
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readFile(File file) {
		try {
			FileReader fileReader = new FileReader(file);
			int i;
			while ((i = fileReader.read()) != -1) {
				System.out.print((char) i);
			}
			System.out.println();
			fileReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
