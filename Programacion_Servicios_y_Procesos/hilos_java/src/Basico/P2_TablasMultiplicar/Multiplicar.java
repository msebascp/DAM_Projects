package Basico.P2_TablasMultiplicar;

public class Multiplicar extends Thread {
	private int n;

	public Multiplicar(int numero) {
		this.n = numero;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(n + " x " + i + " = " + (n * i));
		}
	}
}
