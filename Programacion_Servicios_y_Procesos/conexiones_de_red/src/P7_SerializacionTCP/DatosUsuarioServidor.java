package P7_SerializacionTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DatosUsuarioServidor {
	public static void main(String[] args) {
		try (
				// Se intenta abrir un servidor en el puerto 6000
				ServerSocket server = new ServerSocket(6000);
				Socket service = server.accept();
				// Preparamos los canales de entrada y salida de objetos
				ObjectOutputStream objOut = new ObjectOutputStream(service.getOutputStream())
		) {
			System.out.println("Conexión establecida.");
			// Creamos un objeto usuario vacío
			Usuario usuario = new Usuario();
			// Enviamos el objeto usuario al cliente
			objOut.writeObject(usuario);
			// Recibimos el objeto usuario del cliente, con la información usuario y password
			ObjectInputStream objIn = new ObjectInputStream(service.getInputStream());
			usuario = (Usuario) objIn.readObject();
			// Mostramos el objeto usuario recibido
			System.out.println("Usuario recibido: " + usuario.toString());
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
