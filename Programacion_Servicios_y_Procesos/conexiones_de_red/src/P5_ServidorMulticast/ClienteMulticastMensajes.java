package P5_ServidorMulticast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticastMensajes {
	public static void main(String[] args) {
		// Crear un socket multicast en el puerto 6000
		try (MulticastSocket ms = new MulticastSocket(6000)) {
			InetAddress group = InetAddress.getByName("225.0.0.1");
			ms.joinGroup(group);
			String message = "";
			// Bucle para recibir mensajes hasta que se ingrese "stop"
			do {
				byte[] buffer = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				ms.receive(packet);
				message = new String(packet.getData());
				System.out.println("Mensaje Recibido: " + message.trim());
			} while (!message.trim().equalsIgnoreCase("stop"));
			ms.leaveGroup(group);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
