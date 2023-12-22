package Basico.P6_JoinThreads;

/**
 * Clase que extiende de hilo y ejecuta un contador de 0 a 1000
 */
public class ContadorHilo extends Thread {
	private int counter;

	private Thread waitThread = null;

	public ContadorHilo() {
	}

	public ContadorHilo(Thread waitThread) {
		this.waitThread = waitThread;
	}

	@Override
	public void run() {
		if (waitThread != null) {
			try {
				waitThread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		//La variable finish nos permite finalizar el hilo cuando lo necesitemos
		while (counter < 1000) {
			counter++;
			try {
				sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int getCounter() {
		return counter;
	}
}
