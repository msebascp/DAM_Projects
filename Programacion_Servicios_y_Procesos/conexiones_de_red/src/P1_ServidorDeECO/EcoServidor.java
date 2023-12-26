package P1_ServidorDeECO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EcoServidor {
	public static void main(String[] args) {
		String message = "";
		try (
				// Iniciamos los recursos con un try with resource para que se cierren
				ServerSocket server = new ServerSocket(6000);
				Socket service = server.accept();
				DataInputStream socketIn = new DataInputStream(service.getInputStream());
				DataOutputStream socketOut = new DataOutputStream(service.getOutputStream());
		) {
			// El servidor espera mensajes del cliente y los devuelve en mayúsculas
			// hasta que recibe "ciao"
			do {
				message = socketIn.readUTF();
				System.out.println("Mensaje de cliente: " + message);
				message = message.toUpperCase();
				socketOut.writeUTF(message);
			} while (!message.trim().equals("CIAO"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
