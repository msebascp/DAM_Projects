package Practica2;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;

public class Practica2_3 {
	public static void main(String[] args) {
		String fileName = JOptionPane.showInputDialog("Nombre del fichero del cual mostrar la información: ");
		Path path = Path.of(fileName);
		try {
			System.out.println("Fecha de creación: " + (FileTime) Files.getAttribute(path, "creationTime"));
			System.out.println("Fecha de acceso: " + (FileTime) Files.getAttribute(path, "lastAccessTime"));
			DosFileAttributes dosFileAttributes = Files.readAttributes(path, DosFileAttributes.class);
			System.out.println("Es oculto: " + dosFileAttributes.isHidden());
			System.out.println("Es de solo lectura: " + dosFileAttributes.isReadOnly());
			System.out.println("Es de sistema: " + dosFileAttributes.isSystem());
			System.out.println("Tamaño en bytes: " + dosFileAttributes.size());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
