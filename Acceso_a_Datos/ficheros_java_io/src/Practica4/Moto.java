package Practica4;

public class Moto extends Vehiculo {
	private int cilindrada;

	public Moto(String matricula, int cilindrada) {
		super(matricula);
		this.cilindrada = cilindrada;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	@Override
	public String toString() {
		return "Moto -> " +
				"matrícula = " + this.getMatricula() +
				", cilindrada = " + cilindrada;
	}
}
