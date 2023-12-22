package Basico.P8_Lavaplatos;

public class LavadorThread extends Thread {
	private final PilaDePlatos pilaDePlatos;
	private int numPlatos;

	public LavadorThread(PilaDePlatos pilaDePlatos, int numPlatos) {
		this.pilaDePlatos = pilaDePlatos;
		this.numPlatos = numPlatos;
	}

	@Override
	public void run() {
		for (int i = 0; i < numPlatos; i++) {
			Plato plato = new Plato(i);
			pilaDePlatos.lavarPlatos(plato);
			try {
				sleep(50);
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
