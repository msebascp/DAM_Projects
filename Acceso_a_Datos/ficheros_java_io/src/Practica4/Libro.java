package Practica4;

import java.io.Serializable;

public class Libro implements Serializable {
	private String isdn;
	private String name;
	private float price;

	public Libro(String isdn, String name, float price) {
		this.isdn = isdn;
		this.name = name;
		this.price = price;
	}

	public String getIsdn() {
		return isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Libro -> " +
				"isdn = '" + isdn + '\'' +
				", nombre = '" + name + '\'' +
				", precio = " + price;
	}
}
