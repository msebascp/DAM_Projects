package Basico.P8_Lavaplatos;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		PilaDePlatos pilaDePlatos = new PilaDePlatos();
		LavadorThread lavadorThread = new LavadorThread(pilaDePlatos, 20);
		SecadorThread secadorThread = new SecadorThread(pilaDePlatos, 20);
		lavadorThread.start();
		secadorThread.start();
		lavadorThread.join();
		secadorThread.join();
		System.out.println("Platos restantes en la pila:");
		System.out.println(pilaDePlatos);
	}
}
