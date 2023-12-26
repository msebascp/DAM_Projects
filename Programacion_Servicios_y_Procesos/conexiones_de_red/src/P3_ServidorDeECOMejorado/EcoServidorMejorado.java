package P3_ServidorDeECOMejorado;

import java.net.ServerSocket;
import java.net.Socket;

public class EcoServidorMejorado {
	public static void main(String[] args) {
		System.out.println("Escuchando...");

		try (
				ServerSocket server = new ServerSocket(6000);
		) {
			//Para la práctica usamos un bucle infinito para que el servidor no se cierre
			//Aunque no es una buena práctica
			while (true) {
				Socket service = server.accept();
				System.out.println("Conexión aceptada");
				SocketThread socketThread = new SocketThread(service);
				socketThread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
