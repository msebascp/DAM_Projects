package Basico.P5_CarreraPrioridades;

import static java.lang.Thread.sleep;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//Se crea 3 hilos de la clase ContadorHilo y se les asigna un nombre
		ContadorHilo hilo1 = new ContadorHilo();
		hilo1.setPriority(2);
		hilo1.setName("A");
		ContadorHilo hilo2 = new ContadorHilo();
		hilo2.setPriority(10);
		hilo2.setName("B");
		ContadorHilo hilo3 = new ContadorHilo();
		hilo3.setPriority(5);
		hilo3.setName("C");
		//Se inician los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();
		//Mostramos los contadores de cada hilo hasta que uno llegue a 1000
		do {
			int contador1 = hilo1.getCounter();
			int contador2 = hilo2.getCounter();
			int contador3 = hilo3.getCounter();
			System.out.print("Hilo " + hilo1.getName() + ": " + contador1 + ", ");
			System.out.print("Hilo " + hilo2.getName() + ": " + contador2 + ", ");
			System.out.println("Hilo " + hilo3.getName() + ": " + contador3);
            /*Si un hilo llega a 100 detenemos los otros hilos, para simplificar se establece
            para todos los hilos la variable finish a 'false' para detener su while*/
			if (contador1 == 1000 || contador2 == 1000 || contador3 == 1000) {
				hilo1.setFinish(false);
				hilo2.setFinish(false);
				hilo3.setFinish(false);
			}
			//Se usa el sleep para no mostrar demasiadas líneas
			sleep(100);

		} while (hilo1.isAlive() || hilo2.isAlive() || hilo3.isAlive());
	}
}
