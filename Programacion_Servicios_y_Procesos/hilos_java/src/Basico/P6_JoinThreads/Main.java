package Basico.P6_JoinThreads;

import static java.lang.Thread.sleep;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//Se crea 3 hilos de la clase ContadorHilo y les paso el hilo a esperar
		ContadorHilo hilo1 = new ContadorHilo();
		hilo1.setName("A");
		hilo1.start();
		ContadorHilo hilo2 = new ContadorHilo(hilo1);
		hilo2.setName("B");
		hilo2.start();
		ContadorHilo hilo3 = new ContadorHilo(hilo2);
		hilo3.setName("C");
		hilo3.start();
		do {
			System.out.println("Hilo " + hilo1.getName() + ": " + hilo1.getCounter());
			sleep(100);
		} while (hilo1.isAlive());
		do {
			System.out.println("Hilo " + hilo2.getName() + ": " + hilo2.getCounter());
			sleep(100);
		} while (hilo2.isAlive());
		do {
			System.out.println("Hilo " + hilo3.getName() + ": " + hilo3.getCounter());
			sleep(100);
		} while (hilo3.isAlive());
	}
}
