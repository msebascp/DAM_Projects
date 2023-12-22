package Avanzado.P4_CarreraHilosConExecutors;

public class RunnerThread extends Thread {
	// Nº actual
	int num;

	public RunnerThread(String name) {
		this.setName(name);
		num = 1;
	}

	public String getRunnerName() {
		return this.getName();
	}

	public int getRunnerNumber() {
		return num;
	}

	@Override
	public void run() {
		while (num < 1000) {
			System.gc();
			num++;
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}