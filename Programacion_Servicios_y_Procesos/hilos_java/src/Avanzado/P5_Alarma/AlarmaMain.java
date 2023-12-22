package Avanzado.P5_Alarma;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AlarmaMain {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Ingrese en que tiempo quiere que suene la alarma: ");
		int tiempo = scanner.nextInt();
		//Restamos la cantidad de avisos que queremos que suenen antes de que suene la alarma
		int tiempoAvisos = tiempo - 5;
		TimeUnit unidad = pedirUnidadTiempo();
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
		Alarma alarma = new Alarma();
		Avisos avisos = new Avisos(unidad);
		executor.schedule(alarma, tiempo, unidad);
		executor.scheduleAtFixedRate(avisos, tiempoAvisos, 1, unidad);
	}

	/**
	 * Pide al usuario que ingrese la unidad de tiempo en la que quiere que suene la alarma
	 *
	 * @return La unidad de tiempo en la que quiere que suene la alarma
	 */
	public static TimeUnit pedirUnidadTiempo() {
		System.out.println("Ingrese la unidad de tiempo en la que quiere que suene la alarma: ");
		System.out.println("1. Segundos");
		System.out.println("2. Minutos");
		System.out.println("3. Horas");
		System.out.println("4. Dias");
		int opcion = scanner.nextInt();
		switch (opcion) {
			case 1:
				return TimeUnit.SECONDS;
			case 2:
				return TimeUnit.MINUTES;
			case 3:
				return TimeUnit.HOURS;
			case 4:
				return TimeUnit.DAYS;
			default:
				return TimeUnit.SECONDS;
		}
	}
}
