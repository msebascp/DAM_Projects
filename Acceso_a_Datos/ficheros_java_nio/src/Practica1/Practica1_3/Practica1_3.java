package Practica1.Practica1_3;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Practica1_3 {
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
						        1. Escribir cursos en fichero binario
						        2. Leer cursos en fichero binario
						        3. Salir
						        Opción:\s
						"""
		);
	}

	/**
	 * Escribe en un fichero binario los cursos ingresados por el usuario
	 */
	public static void writeFile() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero: ");
		Path path = Path.of(fileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe, creando...");
				Files.createFile(path);
			}
			ArrayList<Curso> cursos = addCursos();
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			for (Curso curso : cursos) {
				objectOutputStream.writeObject(curso);
			}
			objectOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Lee un fichero binario y muestra por pantalla los cursos que contiene
	 */
	public static void readFile() {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero: ");
		Path path = Path.of(fileName);
		try {
			if (!Files.exists(path)) {
				System.out.println("El fichero no existe");
			} else {
				FileInputStream fileInputStream = new FileInputStream(fileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				while (fileInputStream.available() > 0) {
					Curso curso = (Curso) objectInputStream.readObject();
					System.out.println(curso);
				}
				objectInputStream.close();
			}
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Pide al usuario los datos de los cursos y los devuelve en un ArrayList
	 *
	 * @return ArrayList con los cursos ingresados
	 */
	public static ArrayList<Curso> addCursos() {
		ArrayList<Curso> cursos = new ArrayList<>();
		do {

			String id = JOptionPane.showInputDialog("ID del curso: ");
			String nombre = JOptionPane.showInputDialog("Nombre del curso: ");
			String numAlumnos = JOptionPane.showInputDialog("Número de alumnos: ");
			Curso curso = new Curso(Integer.parseInt(id), nombre, Integer.parseInt(numAlumnos));
			cursos.add(curso);
		} while (JOptionPane.showConfirmDialog(null, "¿Desea ingresar otro curso?") == 0);
		return cursos;
	}
}
