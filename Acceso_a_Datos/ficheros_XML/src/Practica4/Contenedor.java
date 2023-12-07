package Practica4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contenedor {
	private Automovil automovil;
	private Motocicleta motocicleta;
	private Ordenador ordenador;
	private SmartPhone smartphone;

	public Contenedor(Automovil automovil, Motocicleta motocicleta, Ordenador ordenador, SmartPhone smartphone) {
		this.automovil = automovil;
		this.motocicleta = motocicleta;
		this.ordenador = ordenador;
		this.smartphone = smartphone;
	}

	public Contenedor() {
	}

	@XmlElement
	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	@XmlElement
	public Motocicleta getMotocicleta() {
		return motocicleta;
	}

	public void setMotocicleta(Motocicleta motocicleta) {
		this.motocicleta = motocicleta;
	}

	@XmlElement
	public Ordenador getOrdenador() {
		return ordenador;
	}

	public void setOrdenador(Ordenador ordenador) {
		this.ordenador = ordenador;
	}

	@XmlElement
	public SmartPhone getSmartphone() {
		return smartphone;
	}

	public void setSmartphone(SmartPhone smartphone) {
		this.smartphone = smartphone;
	}
}
