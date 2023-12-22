package Avanzado.P1_BancoConLocks;

import java.util.ArrayList;

public class Banco {
	public static void main(String[] args) {
		CuentaBancaria cuenta = new CuentaBancaria(1000);
		// Creamos 20 threads para ahorrar y 20 para gastar
		ArrayList<AhorrarThread> ahorrarThreads = new ArrayList<>();
		ArrayList<GastarThread> gastarThreads = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			AhorrarThread ahorrarThread = new AhorrarThread(cuenta);
			GastarThread gastarThread = new GastarThread(cuenta);
			ahorrarThreads.add(ahorrarThread);
			gastarThreads.add(gastarThread);
			ahorrarThread.start();
			gastarThread.start();
		}
		// Esperamos a que todos los threads terminen y mostramos el balance final
		do {
			if (Thread.activeCount() == 2) {
				cuenta.verBalance();
			}
		} while (Thread.activeCount() > 2);
	}
}
