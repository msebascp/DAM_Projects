package Basico.P8_Lavaplatos;

import java.util.ArrayDeque;
import java.util.Deque;

public class PilaDePlatos {
	private Deque<Plato> pila = new ArrayDeque<>();

	public PilaDePlatos() {
	}

	/**
	 * Añade un plato a la pila si hay espacio, si no, espera a que haya espacio
	 *
	 * @param plato Plato a añadir a la pila
	 */
	public synchronized void lavarPlatos(Plato plato) {
		try {
			while (pila.size() >= 5) {
				System.out.println("Pila llena");
				wait();
			}
			pila.push(plato);
			System.out.println("Plato " + plato.getNumPlato() + " añadido a la pila");
			notify();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Saca un plato de la pila si hay platos, si no, espera a que haya platos
	 */
	public synchronized void secarPlatos() {
		try {
			while (pila.isEmpty()) {
				System.out.println("Pila vacía");
				wait();
			}
			Plato platoSeco = pila.pop();
			System.out.println("Secando plato: " + platoSeco.getNumPlato());
			notify();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public Deque<Plato> getPila() {
		return pila;
	}

	public void setPila(Deque<Plato> pila) {
		this.pila = pila;
	}

	@Override
	public String toString() {
		// devolver todos los platos de la pila
		StringBuilder stringBuilder = new StringBuilder();
		for (Plato plato : pila) {
			stringBuilder.append("Plato ").append(plato.getNumPlato()).append("\n");
		}
		return stringBuilder.toString();
	}
}
