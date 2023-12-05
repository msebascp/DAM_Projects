package Practica4;

import java.io.Serializable;

/**
 * Clase padre de Coche y Moto
 */
public class Vehiculo implements Serializable {
	private String matricula;

	public Vehiculo(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
