package Practica1;

import java.nio.file.Path;
import java.util.Scanner;

public class Practica1_1 {
	public static void main(String[] args) {
		//Pedir el nombre/ruta del fichero y mostrar la ruta absoluta
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ruta del fichero dentro del proyecto: ");
		String file_path = scanner.nextLine();
		Path path = Path.of(file_path);
		System.out.println("Ruta absoluta: " + path.toAbsolutePath());
	}
}
