package Avanzado.P3_LectoresEscritores;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		File file = new File("src/lectoresEscritores.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("Archivo creado");
			} else {
				System.out.println("Archivo ya existente");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		EditorTexto editorTexto = new EditorTexto(file);
		for (int i = 0; i < 5; i++) {
			new HiloEscritor(editorTexto).start();
			new HiloLector(editorTexto).start();
		}
	}
}
