package P8_SerializacionUDP;

import java.io.Serializable;

public class Producto implements Serializable {
	private String nombre;
	private int precio;
	private String comprador;

	/**
	 * Constructor de la clase Producto, el comprador se inicializa a vacío
	 *
	 * @param nombre Nombre del producto
	 * @param precio Precio del producto
	 */
	public Producto(String nombre, int precio) {
		this.nombre = nombre;
		this.precio = precio;
		this.comprador = "";
	}

	@Override
	public String toString() {
		return "Producto{" +
				"nombre='" + nombre + '\'' +
				", precio=" + precio +
				", comprador='" + comprador + '\'' +
				'}';
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
}
