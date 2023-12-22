package Avanzado.P6_ForkJoin;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		String path = "src/ForkJoin/texto.txt";
		ArrayList<String> texto = leerFichero(path);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		forkJoin tarea = new forkJoin(texto, 0, 56);
		tarea.invoke();
		ArrayList<String> resultado = tarea.join();
		escribirFichero(resultado);
	}

	/**
	 * Método que lee un fichero y lo almacena en un ArrayList
	 *
	 * @param ruta String con la ruta del fichero
	 * @return ArrayList<String> con el texto del fichero
	 */
	private static ArrayList<String> leerFichero(String ruta) {
		File fichero = new File(ruta);
		ArrayList<String> texto = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(fichero);
			while (reader.hasNextLine()) {
				String linea = reader.nextLine();
				//linea = linea.trim();
				if (linea.length() != 0) {
					texto.add(linea);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return texto;
	}

	/**
	 * Método que escribe un ArrayList en un fichero
	 *
	 * @param resultado ArrayList<String> con el texto a escribir
	 */
	private static void escribirFichero(ArrayList<String> resultado) {
		try {
			File ficheroSalida = new File("src/ForkJoin/texto2.txt");
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ficheroSalida)));
			for (String line : resultado) {
				pw.println(line);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
