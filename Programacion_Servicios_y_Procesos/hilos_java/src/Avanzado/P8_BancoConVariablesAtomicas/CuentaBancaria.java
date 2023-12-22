package Avanzado.P8_BancoConVariablesAtomicas;

import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancaria {
	private final AtomicInteger balance = new AtomicInteger(0);
	private final ReentrantLock lock = new ReentrantLock();

	public CuentaBancaria(int balance) {
		this.balance.set(balance);
	}

	/**
	 * Muestra el balance de la cuenta
	 */
	public void verBalance() {
		System.out.println("Balance: " + balance);
	}

	/**
	 * Suma la cantidad indicada al balance de la cuenta
	 *
	 * @param cantidad Cantidad a sumar
	 */
	public void meterDinero(int cantidad) {
		lock.lock();
		try {
			balance.getAndAdd(cantidad);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Resta la cantidad indicada al balance de la cuenta
	 *
	 * @param cantidad Cantidad a restar
	 */
	public void sacarDinero(int cantidad) {
		lock.lock();
		try {
			balance.getAndAdd(-cantidad);
		} finally {
			lock.unlock();
		}

	}

	public AtomicInteger getBalance() {
		return balance;
	}

	public void setBalance(AtomicInteger balance) {
		this.balance.set(balance.get());
	}
}
