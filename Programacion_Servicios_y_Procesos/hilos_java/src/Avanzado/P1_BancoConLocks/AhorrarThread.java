package Avanzado.P1_BancoConLocks;

public class AhorrarThread extends Thread {
	private CuentaBancaria cuenta;

	public AhorrarThread(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			this.cuenta.meterDinero(1000);
			System.out.println("Ingresados 1000€. Balance: " + cuenta.getBalance());
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}
}
