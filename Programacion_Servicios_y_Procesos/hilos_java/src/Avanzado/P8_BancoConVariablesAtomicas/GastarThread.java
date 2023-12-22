package Avanzado.P8_BancoConVariablesAtomicas;

public class GastarThread extends Thread {
	private CuentaBancaria cuenta;

	public GastarThread(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			this.cuenta.sacarDinero(1000);
			System.out.println("Retirados 1000€. Balance: " + cuenta.getBalance());
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}
}
