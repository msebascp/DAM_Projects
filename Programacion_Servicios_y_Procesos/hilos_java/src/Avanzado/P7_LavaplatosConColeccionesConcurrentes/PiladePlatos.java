package Avanzado.P7_LavaplatosConColeccionesConcurrentes;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PiladePlatos {

	BlockingDeque<Plato> pila = new LinkedBlockingDeque<>(5);

	public void lavar(Plato plato) {
		try {
			pila.putFirst(plato);
			System.out.println("Lavado " + plato.toString() + " Platos en la Pila " + pila.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Plato secar() {
		Plato p = null;
		try {
			p = pila.takeFirst();
			System.out.println("Secado " + p.toString() + " Platos en la Pila " + pila.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return p;
	}
}
