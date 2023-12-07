package Practica5;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Contenedor {
	private ArrayList<Gimnasio> gimnasios;
	private ArrayList<Videojuego> videojuegos;

	public Contenedor(ArrayList<Gimnasio> gimnasios, ArrayList<Videojuego> videojuegos) {
		this.gimnasios = gimnasios;
		this.videojuegos = videojuegos;
	}

	public Contenedor() {
	}

	@XmlElement
	public ArrayList<Gimnasio> getGimnasios() {
		return gimnasios;
	}

	public void setGimnasios(ArrayList<Gimnasio> gimnasios) {
		this.gimnasios = gimnasios;
	}

	@XmlElement
	public ArrayList<Videojuego> getVideojuegos() {
		return videojuegos;
	}

	public void setVideojuegos(ArrayList<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}
}
