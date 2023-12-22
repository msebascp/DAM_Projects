package Avanzado.P2_LavaplatosConCondiciones;

public class HiloSecador extends Thread {
	PiladePlatos pila;
	int totalPlatos;

	public HiloSecador(PiladePlatos pila, int totalPlatos) {
		this.pila = pila;
		this.totalPlatos = totalPlatos;
	}

	@Override
	public void run() {
		for (int i = 0; i < totalPlatos; i++) {

			Plato plato = pila.secar();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
