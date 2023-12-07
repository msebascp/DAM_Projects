package Practica4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SmartPhone {
	private String marca;
	private String modelo;
	private int precio;

	public SmartPhone(String marca, String modelo, int precio) {
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}

	public SmartPhone() {
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
		return "SmartPhone{" +
				"marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", precio=" + precio +
				'}';
	}
}
