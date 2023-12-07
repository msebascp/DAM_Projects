package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practica1_2 {
	public static void main(String[] args) {
		//Pedir por pantalla el nombre de un directorio y mostrar
		//todos los ficheros y subdirectorios que contiene
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ruta del directorio dentro del proyecto: ");
		String dirPath = scanner.nextLine();
		listFiles(dirPath);
	}

	public static void listFiles(String dirPath) {
		Path path = Path.of(dirPath);
		if (Files.isDirectory(path)) {
			try {
				Stream<Path> filesStream = Files.list(path);
				ArrayList<Path> filesList = (ArrayList<Path>) filesStream.collect(Collectors.toList());
				for (Path file : filesList) {
					if (Files.isDirectory(file)) {
						listFiles(file.toString());
					} else {
						System.out.println(file);
					}
				}
				filesStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println(path);
		}
	}
}
