package Basico.P2_TablasMultiplicar;

public class Main {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			Multiplicar m = new Multiplicar(i);
			m.start();
		}
	}
}
