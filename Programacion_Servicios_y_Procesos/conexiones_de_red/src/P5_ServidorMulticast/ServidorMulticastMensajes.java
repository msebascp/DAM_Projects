package P5_ServidorMulticast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ServidorMulticastMensajes {
	public static void main(String[] args) {
		// Crear una cadena de mensaje para almacenar la entrada del servidor
		String message = "";
		// Utilizar el bloque try-with-resources para garantizar que los recursos se cierren correctamente
		try (
				// Crear un socket multicast en el puerto 6000
				MulticastSocket ms = new MulticastSocket(6000);
				Scanner scanner = new Scanner(System.in)
		) {
			InetAddress group = InetAddress.getByName("225.0.0.1");
			ms.joinGroup(group);
			// Bucle para enviar mensajes hasta que se ingrese "stop"
			do {
				System.out.print("Mensaje para los clientes: ");
				message = scanner.nextLine();
				DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, 6000);
				ms.send(packet);

			} while (!message.equalsIgnoreCase("stop"));
			ms.leaveGroup(group);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
