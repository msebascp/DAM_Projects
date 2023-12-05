package Practica4;

public class Coche extends Vehiculo {
	private int seats;

	public Coche(String matricula, int seats) {
		super(matricula);
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Coche -> " +
				"matrícula = " + this.getMatricula() +
				", plazas = " + seats;
	}
}
