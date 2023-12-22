package Basico.P7_Banco;

public class GastarThread extends Thread {
	private final CuentaBancaria cuenta;

	public GastarThread(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
            /*
            Método sincronizado
            this.cuenta.sacarDinero(1000);
            System.out.println("Retirados 1000€. Balance: " + cuenta.getBalance());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

			// Método con objeto sincronizado
			synchronized (cuenta) {
				this.cuenta.takeMoney(1000);
				System.out.println("Retirados 1000€. Balance: " + cuenta.getBalance());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
