package Basico.P8_Lavaplatos;

public class SecadorThread extends Thread {
	private PilaDePlatos pilaDePlatos;
	private int numPlatos;

	public SecadorThread(PilaDePlatos pilaDePlatos, int numPlatos) {
		this.pilaDePlatos = pilaDePlatos;
		this.numPlatos = numPlatos;
	}

	@Override
	public void run() {
		for (int i = 0; i < numPlatos; i++) {
			pilaDePlatos.secarPlatos();
			try {
				sleep(150);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int getNumPlatos() {
		return numPlatos;
	}

	public void setNumPlatos(int numPlatos) {
		this.numPlatos = numPlatos;
	}
}
