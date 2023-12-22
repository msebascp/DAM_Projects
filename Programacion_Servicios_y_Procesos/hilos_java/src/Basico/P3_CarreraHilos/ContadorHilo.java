package Basico.P3_CarreraHilos;

import java.util.Random;

public class ContadorHilo extends Thread {
	private int counter;

	public ContadorHilo() {
	}

	@Override
	public void run() {
		while (counter < 1000) {
			counter++;
			try {
				sleep(getAleatoryNumber());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int getCounter() {
		return counter;
	}

	/**
	 * Devuelve un número entre 0 y 99, con esto obtenemos aleatoriedad en la carrera
	 **/
	public static int getAleatoryNumber() {
		Random random = new Random();
		return random.nextInt(100);
	}
}
