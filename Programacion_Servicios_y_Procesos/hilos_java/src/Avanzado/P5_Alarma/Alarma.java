package Avanzado.P5_Alarma;

public class Alarma implements Runnable {

	public Alarma() {
	}

	@Override
	public void run() {
		System.out.println("Alarma! Alarma! Alarma!");
	}
}
