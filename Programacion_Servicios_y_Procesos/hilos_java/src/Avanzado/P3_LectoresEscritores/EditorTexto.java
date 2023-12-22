package Avanzado.P3_LectoresEscritores;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EditorTexto {
	private File file;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public EditorTexto(File file) {
		this.file = file;
	}

	public void leer() {
		Scanner reader = null;
		try {
			lock.readLock().lock();
			reader = new Scanner(file);
			System.out.println("Leyendo el archivo");
			while (reader.hasNextLine()) {
				System.out.println(reader.nextLine());
			}
			reader.close();
			lock.readLock().unlock();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void escribir() {
		PrintWriter pw = null;
		try {
			lock.writeLock().lock();
			pw = new PrintWriter(new BufferedWriter(new
					FileWriter(file, true)));
			pw.println("Escribiendo en el archivo");
			pw.println("Frase 1");
			pw.println("Frase 2");
			pw.close();
			lock.writeLock().unlock();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
