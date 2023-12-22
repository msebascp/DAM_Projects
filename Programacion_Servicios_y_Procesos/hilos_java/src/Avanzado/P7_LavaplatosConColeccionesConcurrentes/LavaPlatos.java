package Avanzado.P7_LavaplatosConColeccionesConcurrentes;

public class LavaPlatos {

	public static void main(String[] args) {

		PiladePlatos p = new PiladePlatos();
		HiloLavador lavador = new HiloLavador(p, 20);
		HiloSecador secador = new HiloSecador(p, 20);
		lavador.start();
		secador.start();
	}

}
