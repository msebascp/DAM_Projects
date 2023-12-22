package Basico.P4_CarreraDeLaMuerte;

import java.util.Random;

/**
 * Clase que extiende de hilo y ejecuta un contador de 0 a 1000
 */
public class ContadorHilo extends Thread {
	private int counter;
	private boolean finish = true;

	public ContadorHilo() {
	}

	@Override
	public void run() {
		//La variable finish nos permite finalizar el hilo cuando lo necesitemos
		while (counter < 1000 && finish) {
			counter++;
			try {
				sleep(getAleatoryNumber());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
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
