package Practica4;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("fichero_binario_practica5");
		if (file.createNewFile()) {
			System.out.println("Fichero creado correctamente");
		} else {
			System.out.println("El fichero ya existe");
		}
		// Creo las colecciones
		// Lista de dos colas de vehículos
		Queue<Vehiculo> vehiculosCola1 = new LinkedList<>();
		vehiculosCola1.add(new Coche("1234ABC", 5));
		vehiculosCola1.add(new Moto("5678DEF", 125));
		Queue<Vehiculo> vehiculosCola2 = new LinkedList<>();
		vehiculosCola2.add(new Coche("9012GHI", 5));
		vehiculosCola2.add(new Moto("3456JKL", 125));
		List<Queue<Vehiculo>> listaDeColas = new ArrayList<>();
		listaDeColas.add(vehiculosCola1);
		listaDeColas.add(vehiculosCola2);
		// Pila de dos listas de libros
		List<Libro> librosList1 = new ArrayList<>();
		librosList1.add(new Libro("123456789", "El Quijote", 20));
		librosList1.add(new Libro("987654321", "El Quijote 2", 200));
		List<Libro> librosList2 = new ArrayList<>();
		librosList2.add(new Libro("123456789", "El Quijote 3", 15));
		librosList2.add(new Libro("987654321", "El Quijote 4", 10));
		Deque<List<Libro>> pilaDeListas = new ArrayDeque<>();
		pilaDeListas.add(librosList1);
		pilaDeListas.add(librosList2);
		// Cola de tres colas de vehículos
		Queue<Vehiculo> vehiculosCola3 = new LinkedList<>();
		vehiculosCola3.add(new Coche("1234ABC", 5));
		vehiculosCola3.add(new Moto("5678DEF", 125));
		Queue<Vehiculo> vehiculosCola4 = new LinkedList<>();
		vehiculosCola4.add(new Coche("9012GHI", 5));
		vehiculosCola4.add(new Moto("3456JKL", 125));
		Queue<Vehiculo> vehiculosCola5 = new LinkedList<>();
		vehiculosCola5.add(new Coche("1234GHC", 5));
		vehiculosCola5.add(new Moto("5888DEF", 125));
		Queue<Queue<Vehiculo>> colaDeColas = new LinkedList<>();
		colaDeColas.add(vehiculosCola3);
		colaDeColas.add(vehiculosCola4);
		colaDeColas.add(vehiculosCola5);
		// Inserto las colecciones en el fichero
		addCollections(file, listaDeColas, pilaDeListas, colaDeColas);
		// Recupero las colecciones del fichero en una lista de objetos
		List<Object> objetos = readCollections(file);
		// Muestro las colecciones de la lista
		for (Object objeto : objetos) {
			System.out.println(objeto.getClass().getSimpleName());
			System.out.println(objeto);
		}
	}

	public static void addCollections(File file, List<Queue<Vehiculo>> listaDeColas, Deque<List<Libro>> pilaDeListas, Queue<Queue<Vehiculo>> colaDeColas) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(listaDeColas);
			objectOutputStream.writeObject(pilaDeListas);
			objectOutputStream.writeObject(colaDeColas);
			objectOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error al insertar objetos: " + e.getMessage());
		}
	}

	/**
	 * Recupera las colecciones del fichero en una lista de objetos
	 *
	 * @param file Fichero del que se recuperarán las colecciones
	 * @return Lista de objetos con las colecciones que recupera del file
	 */
	public static List<Object> readCollections(File file) {
		// Creo una lista de objetos que se devolverá al main
		List<Object> objetos = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			// Mientras haya algo que leer en el fichero, los añado a la lista
			while (fileInputStream.available() > 0) {
				Object object = objectInputStream.readObject();
				objetos.add(object);
			}
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error en recuperarObjeto: " + e.getMessage());
		}
		return objetos;
	}
}
