package Basico.P3_CarreraHilos;

import static java.lang.Thread.sleep;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//Se crea 3 hilos de la clase ContadorHilo y se les asigna un nombre
		ContadorHilo hilo1 = new ContadorHilo();
		hilo1.setName("A");
		ContadorHilo hilo2 = new ContadorHilo();
		hilo2.setName("B");
		ContadorHilo hilo3 = new ContadorHilo();
		hilo3.setName("C");
		//Se inician los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();
		//Mostramos los contadores de cada hilo mientras haya uno vivo
		do {
			System.out.print("Hilo " + hilo1.getName() + ": " + hilo1.getCounter() + ", ");
			System.out.print("Hilo " + hilo2.getName() + ": " + hilo2.getCounter() + ", ");
			System.out.println("Hilo " + hilo3.getName() + ": " + hilo3.getCounter());
			//Se usa el sleep para no mostrar demasiadas líneas
			sleep(100);
		} while (hilo1.isAlive() || hilo2.isAlive() || hilo3.isAlive());
	}
}
