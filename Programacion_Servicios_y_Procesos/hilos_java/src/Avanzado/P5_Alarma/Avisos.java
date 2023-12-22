package Avanzado.P5_Alarma;

import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Avisos implements Runnable {
	private int contador = 5;

	private TimeUnit unidadTiempo;

	public Avisos() {
	}

	public Avisos(TimeUnit unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}

	@Override
	public void run() {
		//Si el contador llega a 0, se sale del programa, si no, se muestra el mensaje de aviso
		if (contador == 0) exit(0);
		else
			System.out.println("Aviso! Quedan " + contador + " " + unidadTiempo.toString() + " para que suene la alarma!");
		contador--;
	}
}
