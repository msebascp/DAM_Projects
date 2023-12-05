package Practica8;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
	public static void main(String[] args) {
		String option;
		File file = new File("tarea8.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("Archivo creado");
			} else {
				System.out.println("El archivo ya existe.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		do {
			option = JOptionPane.showInputDialog(menu());
			switch (option) {
				case "1":
					writeFile(file);
					break;
				case "2":
					palindromo(file);
					break;
				case "3":
					convertUppercase(file);
					break;
				case "4":
					convertLowercase(file);
					break;
				case "5":
					countVowels(file);
					break;
				case "6":
					sortLetters(file);
					break;
				case "7":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!option.equals("7"));
	}

	public static String menu() {
		String menu;
		menu = """
				1. Crear un nuevo archivo
				2. Palíndromo
				3. Convertir a mayúsculas
				4. Convertir a minúsculas
				5. Contar vocales
				6. Ordenar letras del fichero
				7. Salir
				Opción:\s""";
		return menu;
	}

	public static void writeFile(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			String text = JOptionPane.showInputDialog("Introduce un texto:");
			randomAccessFile.writeBytes(text);
			randomAccessFile.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void palindromo(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			String text = randomAccessFile.readLine();
			text = text.toLowerCase();
			String textReverse = new StringBuilder(text).reverse().toString();
			if (text.equals(textReverse)) {
				System.out.println("El texto es un palíndromo");
			} else {
				System.out.println("El texto no es un palíndromo");
			}
			randomAccessFile.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void convertUppercase(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			String text = randomAccessFile.readLine();
			text = text.toUpperCase();
			randomAccessFile.close();
			createFileWithText(text, "archivoMayusculas.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void convertLowercase(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			String text = randomAccessFile.readLine();
			text = text.toLowerCase();
			randomAccessFile.close();
			createFileWithText(text, "archivoMinusculas.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void countVowels(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			String text = randomAccessFile.readLine();
			randomAccessFile.close();
			text = text.toLowerCase();
			int contadorLetraA = 0;
			int contadorLetraE = 0;
			int contadorLetraI = 0;
			int contadorLetraO = 0;
			int contadorLetraU = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == 'a') {
					contadorLetraA++;
				}
				if (text.charAt(i) == 'e') {
					contadorLetraE++;
				}
				if (text.charAt(i) == 'i') {
					contadorLetraI++;
				}
				if (text.charAt(i) == 'o') {
					contadorLetraO++;
				}
				if (text.charAt(i) == 'u') {
					contadorLetraU++;
				}
			}
			String textResult = """
					El texto tiene %d letras a
					El texto tiene %d letras e
					El texto tiene %d letras i
					El texto tiene %d letras o
					El texto tiene %d letras u"""
					.formatted(contadorLetraA, contadorLetraE, contadorLetraI, contadorLetraO, contadorLetraU);
			JOptionPane.showMessageDialog(null, textResult);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sortLetters(File file) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			String text = randomAccessFile.readLine();
			randomAccessFile.close();
			char[] textArray = text.toCharArray();
			char aux;
			for (int i = 0; i < textArray.length; i++) {
				for (int j = 0; j < textArray.length - 1; j++) {
					if (textArray[j] > textArray[j + 1]) {
						aux = textArray[j];
						textArray[j] = textArray[j + 1];
						textArray[j + 1] = aux;
					}
				}
			}
			String textResult = new String(textArray);
			createFileWithText(textResult, "archivoOrdenado.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void createFileWithText(String text, String nameFile) {
		File file = new File(nameFile);
		try {
			if (file.createNewFile()) {
				System.out.println("Archivo creado");
			} else {
				System.out.println("El archivo ya existe.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.writeBytes(text);
			randomAccessFile.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
