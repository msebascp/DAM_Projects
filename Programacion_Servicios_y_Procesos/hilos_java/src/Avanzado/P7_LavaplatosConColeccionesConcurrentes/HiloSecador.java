package Avanzado.P7_LavaplatosConColeccionesConcurrentes;

public class HiloSecador extends Thread {
	PiladePlatos pila;
	int totalPlatos;

	public HiloSecador(PiladePlatos pila, int totalPlatos) {
		this.pila = pila;
		this.totalPlatos = totalPlatos;
	}

	@Override
	public void run() {
		//Para que no seque antes de tiempo
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < totalPlatos; i++) {
			Plato plato = pila.secar();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
