package Avanzado.P1_BancoConLocks;

import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancaria {
	private int balance;
	private ReentrantLock lock = new ReentrantLock();

	public CuentaBancaria(int balance) {
		this.balance = balance;
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
			balance += cantidad;
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
			balance -= cantidad;
		} finally {
			lock.unlock();
		}

	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
