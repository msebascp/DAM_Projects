package Avanzado.P2_LavaplatosConCondiciones;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PiladePlatos {

	Stack<Plato> pila = new Stack();
	//Creo un lock y dos condiciones para controlar el acceso a la pila
	ReentrantLock lock = new ReentrantLock();
	Condition conditionPilaLlena = lock.newCondition();
	Condition conditionPilaVacia = lock.newCondition();


	public void lavar(Plato plato) {
		try {
			lock.lock();
			while (pila.size() >= 5) {
				//Si la pila esta llena espero
				conditionPilaLlena.await();
			}
			pila.push(plato);
			System.out.println("Lavado " + plato.toString() + " Platos en la Pila " + pila.size());
			//Aviso a los hilos que están esperando a que la pila no este vacía
			conditionPilaVacia.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//Desbloqueo el lock en el finally para que se desbloquee siempre
			lock.unlock();
		}
	}

	public Plato secar() {
		Plato p = null;
		try {
			lock.lock();
			while (pila.isEmpty()) {

				conditionPilaVacia.await();
			}
			p = pila.pop();
			System.out.println("Secado " + p.toString() + " Platos en la Pila " + pila.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//Aviso a los hilos que están esperando a que la pila no este llena
			conditionPilaLlena.signal();
			//Desbloqueo el lock en el finally para que se desbloquee siempre
			lock.unlock();
		}
		return p;
	}
}
