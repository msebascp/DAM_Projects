package Practica5;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Videojuego {
	private String nombre;
	private String genero;
	private int precio;

	public Videojuego(String nombre, String genero, int precio) {
		this.nombre = nombre;
		this.genero = genero;
		this.precio = precio;
	}

	public Videojuego() {
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@XmlElement
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Videojuego{" +
				"nombre='" + nombre + '\'' +
				", genero='" + genero + '\'' +
				", precio=" + precio +
				'}';
	}
}
