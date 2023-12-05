package Practica6;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File file = new File("tarea6.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("Fichero creado");
			} else {
				System.out.println("EL fichero ya existe");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String option;
		do {
			menu();
			option = scanner.nextLine();
			switch (option) {
				case "1":
					addData(file);
					break;
				case "2":
					showData(file);
					break;
				case "3":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
			}
		} while (!option.equals("3"));
	}

	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Inserta una nueva línea en el fichero con salto de línea
	 *
	 * @param file Fichero de carácteres en el que se insertará la línea
	 */
	public static void addData(File file) {
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			System.out.print("Introduce texto: ");
			String texto = scanner.nextLine();
			fileWriter.write(texto + "\n");
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Muestra los datos del fichero en orden inverso con el uso de StringBuilder
	 *
	 * @param file Fichero de carácteres del que se mostrarán los datos
	 */
	public static void showData(File file) {
		try {
			StringBuilder texto = new StringBuilder();
			FileReader fileReader = new FileReader(file);
			int letra;
			while ((letra = fileReader.read()) != -1) {
				texto.append((char) letra);
			}
			texto.reverse();
			System.out.println(texto);
			fileReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void menu() {
		System.out.println("1. Insertar datos");
		System.out.println("2. Ver fichero");
		System.out.println("3. Salir");
		System.out.print("Opción: ");
	}
}
