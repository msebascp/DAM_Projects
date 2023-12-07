package Practica1.Practica1_3;

import java.io.Serializable;

public class Curso implements Serializable {
	int id;
	String nombre;
	int numAlumnos;

	public Curso(int id, String nombre, int numAlumnos) {
		this.id = id;
		this.nombre = nombre;
		this.numAlumnos = numAlumnos;
	}

	@Override
	public String toString() {
		return "Curso{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", numAlumnos=" + numAlumnos +
				'}';
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}
}
