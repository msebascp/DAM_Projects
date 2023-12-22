package Basico.P5_CarreraPrioridades;

import java.util.Random;

public class ContadorHilo extends Thread {
	private int counter;
	private boolean finish = true;

	public ContadorHilo() {
	}

	public ContadorHilo(int priority) {
		if (priority < 1) {
			Thread.currentThread().setPriority(1);
		} else Thread.currentThread().setPriority(Math.min(priority, 10));
	}

	@Override
	public void run() {
		//La variable finish nos permite finalizar el hilo cuando lo necesitemos
		while (counter < 1000 && finish) {
			counter++;
			try {
				if (getAleatoryNumber() > Thread.currentThread().getPriority()) {
					Thread.sleep(100);
				}
				Thread.sleep(5);
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
		return random.nextInt(10);
	}
}
