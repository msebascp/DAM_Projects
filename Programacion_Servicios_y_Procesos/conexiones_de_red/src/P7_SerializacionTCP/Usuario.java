package P7_SerializacionTCP;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
	private String usuario;
	private String password;
	private Date fechaRegistro;

	/**
	 * Constructor de la clase Usuario.
	 * Inicializa la fecha de registro con la fecha actual.
	 */
	public Usuario() {
		this.fechaRegistro = new Date();
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"usuario='" + usuario + '\'' +
				", password='" + password + '\'' +
				", fechaRegistro=" + fechaRegistro +
				'}';
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
