package Practica1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String option;
		do {
			showMenu();
			option = sc.nextLine();
			switch (option) {
				case "1":
					createDirectory();
					break;
				case "2":
					createFile();
					break;
				case "3":
					listDirectory();
					break;
				case "4":
					existsFile();
					break;
				case "5":
					deleteFile();
					break;
				case "6":
					renameFile();
					break;
				case "7":
					copyFile();
					break;
				case "8":
					moveFile();
					break;
				case "9":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción no válida");
					break;
			}
		} while (!option.equals("9"));
		sc.close();
	}

	public static void showMenu() {
		System.out.println("1. Crear directorio");
		System.out.println("2. Crear fichero");
		System.out.println("3. Listar directorio");
		System.out.println("4. ¿Existe el fichero?");
		System.out.println("5. Borrar fichero");
		System.out.println("6. Renombrar fichero");
		System.out.println("7. Copiar fichero");
		System.out.println("8. Mover fichero");
		System.out.println("9. Salir");
		System.out.print("Opción: ");
	}

	public static void createDirectory() {
		System.out.print("Introduce el nombre del directorio: ");
		String path = sc.nextLine();
		File file = new File(path);
		if (file.mkdir()) {
			System.out.println("Directorio creado");
		} else {
			System.out.println("No se ha podido crear el directorio");
		}
	}

	public static void createFile() {
		System.out.print("¿Dónde quieres guardar el fichero?: ");
		String directory = sc.nextLine();
		File fileDirectory = new File(directory);
		if (!fileDirectory.exists()) {
			System.out.println("El directorio no existe");
			return;
		}
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		File file = new File(directory + "/" + path);
		try {
			if (file.createNewFile()) {
				System.out.println("Fichero creado");
			}
		} catch (Exception e) {
			System.out.println("No se ha podido crear el fichero");
		}
	}

	public static void listDirectory() {
		System.out.print("Introduce el nombre del directorio: ");
		String path = sc.nextLine();
		File file = new File(path);
		String[] files = file.list();
		if (files != null) {
			for (String f : files) {
				System.out.println(f);
			}
		}
	}

	public static void existsFile() {
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		File file = new File(path);
		if (file.exists()) {
			System.out.println("El fichero existe");
		} else {
			System.out.println("El fichero no existe");
		}
	}

	public static void deleteFile() {
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		File file = new File(path);
		if (file.delete()) {
			System.out.println("Fichero borrado");
		} else {
			System.out.println("No se ha podido borrar el fichero");
		}
	}

	public static void renameFile() {
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		System.out.print("Introduce el nuevo nombre del fichero: ");
		String newName = sc.nextLine();
		File file = new File(path);
		if (file.renameTo(new File(newName))) {
			System.out.println("Fichero renombrado");
		} else {
			System.out.println("No se ha podido renombrar el fichero");
		}
	}

	public static void copyFile() {
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		System.out.print("Introduce la ruta y el nuevo nombre del fichero: ");
		String newPath = sc.nextLine();
		File file = new File(path);
		File newFile = new File(newPath);
		try {
			FileUtils.copyFile(file, newFile);
			System.out.println("Fichero copiado");
		} catch (Exception e) {
			System.out.println("No se ha podido copiar el fichero");
		}
	}

	public static void moveFile() {
		System.out.print("Introduce el nombre del fichero: ");
		String path = sc.nextLine();
		System.out.print("Introduce la ruta y el nuevo nombre del fichero: ");
		String newPath = sc.nextLine();
		File file = new File(path);
		File newFile = new File(newPath);
		try {
			FileUtils.moveFile(file, newFile);
			System.out.println("Fichero movido");
		} catch (Exception e) {
			System.out.println("No se ha podido mover el fichero");
		}
	}
}
