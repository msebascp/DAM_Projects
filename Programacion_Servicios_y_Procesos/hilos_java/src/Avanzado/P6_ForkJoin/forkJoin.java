package Avanzado.P6_ForkJoin;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class forkJoin extends RecursiveTask<ArrayList<String>> {
	ArrayList<String> texto;
	final int MAXLINES = 10;
	int primeraLinea;
	int ultimaLinea;
	String palabraBuscar = "Java";
	String palabraSustituir = "JAVA";

	public forkJoin(ArrayList<String> texto, int primeraLinea, int ultimaLinea) {
		this.texto = texto;
		this.primeraLinea = primeraLinea;
		this.ultimaLinea = ultimaLinea;

	}

	private static void escribirFichero(ArrayList<String> resultado, String ruta) {
		try {
			File ficheroSalida = new File(ruta);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ficheroSalida)));
			for (String line : resultado) {
				pw.println(line);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	 * Método que realiza la sustitución de palabras en un texto
	 *
	 * @return ArrayList<String> con el texto modificado
	 */
	@Override
	public ArrayList<String> compute() {
		// Si hay menos de MAXLINES líneas, realizar la sustitución de palabras
		if (ultimaLinea - primeraLinea <= MAXLINES) {
			ArrayList<String> textoResultado = new ArrayList<String>();
			// Realizar la sustitución de palabras para las líneas asignadas
			for (int i = primeraLinea; i <= ultimaLinea; i++) {
				String linea = texto.get(i);
				linea = linea.replaceAll(palabraBuscar, palabraSustituir);
				textoResultado.add(linea);
			}
			return textoResultado;
		} else {
			// Dividir en dos subtareas si hay más de MAXLINES líneas,
			// con la mitad de líneas para cada subtarea
			int mitad = (primeraLinea + ultimaLinea) / 2;
			forkJoin subTarea1 = new forkJoin(texto, primeraLinea, mitad);
			forkJoin subTarea2 = new forkJoin(texto, mitad + 1, ultimaLinea);

			// Se invocan las subtareas
			invokeAll(subTarea1, subTarea2);

			// Obtener los resultados de las subtareas
			ArrayList<String> resultado1 = subTarea1.join();
			ArrayList<String> resultado2 = subTarea2.join();

			// Combinar los resultados
			resultado1.addAll(resultado2);
			return resultado1;
		}
	}
}
