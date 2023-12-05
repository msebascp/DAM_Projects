package Practica2;

import java.io.Serializable;

public class Persona implements Serializable {
	String name;
	int age;
	String address;

	public Persona(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Persona() {
	}
}
