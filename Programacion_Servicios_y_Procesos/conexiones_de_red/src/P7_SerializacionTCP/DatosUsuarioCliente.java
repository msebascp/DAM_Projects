package P7_SerializacionTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DatosUsuarioCliente {
	public static void main(String[] args) {
		try (
				// Se establece una conexión al servidor en el localhost y puerto 6000
				Socket mySocket = new Socket("localhost", 6000);
				ObjectInputStream objIn = new ObjectInputStream(mySocket.getInputStream());
				Scanner sc = new Scanner(System.in)
		) {
			System.out.println("Conectado al servidor.");
			// Recibimos el objeto usuario vacío del servidor
			Usuario usuario = (Usuario) objIn.readObject();
			// Pedimos al usuario que introduzca su nombre de usuario y contraseña
			// y los guardamos en el objeto usuario
			System.out.print("Introduce tu nombre de usuario: ");
			usuario.setUsuario(sc.nextLine());
			System.out.print("Introduce tu contraseña: ");
			usuario.setPassword(sc.nextLine());
			// Enviamos el objeto usuario al servidor con la información usuario y password
			try (ObjectOutputStream objOut = new ObjectOutputStream(mySocket.getOutputStream())) {
				objOut.writeObject(usuario);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
