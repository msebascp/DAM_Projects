package Practica7;

import java.io.*;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		File tarea7 = new File("tarea7.txt");
		try {
			if (tarea7.createNewFile()) {
				System.out.println("Fichero creado");
			} else {
				System.out.println("Fichero ya existe");
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
					figura_1(tarea7);
					break;
				case "2":
					figura_2(tarea7);
					break;
				case "3":
					figura_3(tarea7);
					break;
				case "4":
					figura_4(tarea7);
					break;
				case "5":
					readFile(tarea7);
					break;
				case "6":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción no válida");
					break;
			}
		} while (!option.equals("6"));
	}

	public static void menu() {
		System.out.println("1. Dibujar figura_1");
		System.out.println("2. Dibujar figura_2");
		System.out.println("3. Dibujar figura_3");
		System.out.println("4. Dibujar figura_4");
		System.out.println("5. Ver fichero");
		System.out.println("6. Salir");
		System.out.print("Opción: ");
	}

	public static void writeFile(File file, String text) {
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(text);
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void figura_1(File file) {
		// Dibujar triángulo rectángulo
		System.out.println("Inserta el número de filas: ");
		int filas = scanner.nextInt();
		scanner.nextLine();
		String text = "";
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j <= i; j++) {
				text += "*";
			}
			text += "\n";
		}
		writeFile(file, text);
	}

	public static void figura_2(File file) {
		System.out.println("Inserta el número de filas: ");
		int filas = scanner.nextInt();
		scanner.nextLine();
		String text = "";
		// Dibujar triángulo isósceles eje y
		if (filas % 2 == 0) {
			for (int i = 0; i < filas / 2; i++) {
				for (int j = 0; j <= i; j++) {
					text += "*";
				}
				text += "\n";
			}
			for (int i = 0; i < filas / 2; i++) {
				for (int j = filas / 2 - i; j > 0; j--) {
					text += "*";
				}
				text += "\n";
			}
		} else {
			for (int i = 0; i <= filas / 2; i++) {
				for (int j = 0; j <= i; j++) {
					text += "*";
				}
				text += "\n";
			}
			for (int i = 0; i < filas / 2; i++) {
				for (int j = filas / 2 - i; j > 0; j--) {
					text += "*";
				}
				System.out.println("a");
				text += "\n";
			}
		}
		writeFile(file, text);
	}

	public static void figura_3(File file) {
		System.out.println("Inserta el número de filas: ");
		int filas = scanner.nextInt();
		scanner.nextLine();
		String text = "";
		// Dibujar triángulo rectángulo invertido eje x
		for (int i = 0; i <= filas; i++) {
			//Espacios en blanco
			for (int j = filas - i - 1; j > -1; j--) {
				text += " ";
			}
			//Asteriscos
			for (int j = 0; j < i; j++) {
				text += "*";
			}
			text += "\n";
		}
		writeFile(file, text);
	}

	public static void figura_4(File file) {
		System.out.println("Inserta el número de filas: ");
		int filas = scanner.nextInt();
		scanner.nextLine();
		String text = "";
		// Dibujar triángulo rectángulo isósceles
		for (int i = 0; i < filas; i++) {
			for (int j = filas - i - 1; j > -1; j--) {
				text += " ";
			}
			for (int j = 0; j < (i + 1) * 2; j++) {
				text += "*";
			}
			text += "\n";
		}
		writeFile(file, text);
	}

	public static void readFile(File file) {
		try {
			FileReader fileReader = new FileReader(file);
			while (fileReader.ready()) {
				System.out.print((char) fileReader.read());
			}
			fileReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
