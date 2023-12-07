package Practica2;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Practica2_2 {
	public static void main(String[] args) {
		String optionMenu;
		do {
			optionMenu = menu();
			switch (optionMenu) {
				case "1" -> copiarFichero();
				case "2" -> moveFile();
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
						        1. Copiar fichero
						        2. Mover fichero
						        3. Salir
						        Opción:\s
						"""
		);
	}

	/**
	 * Copia un fichero
	 */
	public static void copiarFichero() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero a copiar: ");
		String newFileName = JOptionPane.showInputDialog("Nombre del fichero copiado: ");
		Path path = Path.of(fileName);
		Path newPath = Path.of(newFileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe");
			} else {
				Files.copy(path, newPath);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Mueve un fichero
	 */
	public static void moveFile() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero a mover: ");
		String newFileName = JOptionPane.showInputDialog("Nombre del fichero movido: ");
		Path path = Path.of(fileName);
		Path newPath = Path.of(newFileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe");
			} else {
				Files.move(path, newPath);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
