package Basico.P7_Banco;

public class CuentaBancaria {
	private int balance;

	public CuentaBancaria(int balance) {
		this.balance = balance;
	}

	/**
	 * Muestra el balance de la cuenta
	 */
	public void seeBalance() {
		System.out.println("Balance: " + balance);
	}

    /*public synchronized void meterDinero(int cantidad) {
        balance += cantidad;
    }*/

	/**
	 * Suma la cantidad indicada al balance de la cuenta
	 *
	 * @param cantidad Cantidad a sumar
	 */
	public void putMoney(int cantidad) {
		balance += cantidad;
	}

    /*public synchronized void sacarDinero(int cantidad) {
        balance -= cantidad;
    }*/

	/**
	 * Resta la cantidad indicada al balance de la cuenta
	 *
	 * @param cantidad Cantidad a restar
	 */
	public void takeMoney(int cantidad) {
		balance -= cantidad;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
