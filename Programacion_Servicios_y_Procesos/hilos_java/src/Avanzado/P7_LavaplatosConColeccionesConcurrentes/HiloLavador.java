package Avanzado.P7_LavaplatosConColeccionesConcurrentes;

public class HiloLavador extends Thread {

	PiladePlatos pila;
	int totalPlatos;

	public HiloLavador(PiladePlatos p, int totalPlatos) {
		this.pila = p;
		this.totalPlatos = totalPlatos;
	}

	@Override
	public void run() {
		for (int i = 0; i < totalPlatos; i++) {
			Plato plato = new Plato(i);
			pila.lavar(plato);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
