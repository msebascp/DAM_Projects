package Practica2;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Practica2_1 {
	public static void main(String[] args) {
		String optionMenu;
		do {
			optionMenu = menu();
			switch (optionMenu) {
				case "1" -> writeFile();
				case "2" -> readFile();
				case "3" -> System.out.println("Saliendo...");
				default -> System.out.println("Opción incorrecta");
			}
		} while (!optionMenu.equals("3"));
	}

	/**
	 * Muestra un menú de opciones y devuelve la opción elegida
	 *
	 * @return Opción elegida
	 */
	public static String menu() {
		return JOptionPane.showInputDialog(
				"""
						        1. Escribir texto
						        2. Leer texto
						        3. Salir
						        Opción:\s
						"""
		);
	}

	/**
	 * Escribe en un de texto
	 */
	public static void writeFile() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero: ");
		Path path = Path.of(fileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe, creando...");
				Files.createFile(path);
			}
			do {
				String texto = JOptionPane.showInputDialog("Texto a escribir: ") + "\n";
				Files.writeString(path, texto, StandardOpenOption.APPEND);
			} while (JOptionPane.showConfirmDialog(null, "¿Desea escribir más texto?") == 0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Lee un fichero de texto y muestra su contenido
	 */
	public static void readFile() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero: ");
		Path path = Path.of(fileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe");
			} else {
				List<String> texto = Files.readAllLines(path);
				for (String linea : texto) {
					System.out.println(linea);
				}
			}
		} catch (IOException | ClassCastException e) {
			throw new RuntimeException(e);
		}
	}
}
