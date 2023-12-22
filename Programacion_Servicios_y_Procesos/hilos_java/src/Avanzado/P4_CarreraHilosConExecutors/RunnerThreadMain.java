package Avanzado.P4_CarreraHilosConExecutors;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RunnerThreadMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Número de hilos competidores: ");
		int numHilos = sc.nextInt();
		sc.close();
		ArrayList<RunnerThread> corredores = new ArrayList<>();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		for (int i = 0; i < numHilos; i++) {
			RunnerThread t = new RunnerThread("Hilo " + i);
			corredores.add(t);
			executor.execute(t);
		}

		do {
			try {
				for (RunnerThread t : corredores) {
					System.out.print(t.getRunnerName() + " -> " + t.getRunnerNumber() + "\t\t");
				}
				System.out.println();
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (executor.getActiveCount() > 0);

		executor.shutdown();
		System.out.println("La Carrera ha terminado");
	}
}