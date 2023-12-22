package Basico.P1_Fibonacci;

public class Fibonacci extends Thread {
	private final int n;

	public Fibonacci(int n) {
		this.n = n;
	}

	@Override
	public void run() {
		int a = 1, b = 1, c;
		System.out.println("Fibonacci de " + n + ":");
		while (b <= n) {
			System.out.print(a + " " + b + " ");
			c = a + b;
			a = b;
			b = c;
		}
		System.out.println();
	}
}
