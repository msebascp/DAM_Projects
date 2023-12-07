package Practica4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ordenador {
	private String marca;
	private String modelo;
	private int precio;

	public Ordenador(String marca, String modelo, int precio) {
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}

	public Ordenador() {
	}

	@XmlElement
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@XmlElement
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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
		return "Ordenador{" +
				"marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", precio=" + precio +
				'}';
	}
}
