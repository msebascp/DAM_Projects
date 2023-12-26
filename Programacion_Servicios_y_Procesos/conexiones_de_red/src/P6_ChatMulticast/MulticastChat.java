package P6_ChatMulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastChat {
	public static void main(String[] args) {
		String multicastAddress = "225.0.0.1";
		int multicastPort = 6000;

		try (MulticastSocket ms = new MulticastSocket(multicastPort);
		     Scanner scanner = new Scanner(System.in)) {
			InetAddress group = InetAddress.getByName(multicastAddress);
			ms.joinGroup(group);
			// Pedir nombre de usuario
			System.out.print("Introduce nombre de usuario: ");
			String username = scanner.nextLine();
			// Crear un hilo para recibir mensajes del grupo multicast
			MulticastChatThread receiverThread = new MulticastChatThread(ms);
			receiverThread.start();
			// Bucle para enviar mensajes hasta que se ingrese "ciao"
			String message = "";
			String fullMessage = "";
			do {
				System.out.println("Escribe tu mensaje: ");
				message = scanner.nextLine();
				fullMessage = username + ": " + message;
				DatagramPacket packet = new DatagramPacket(fullMessage.getBytes(), fullMessage.length(), group, multicastPort);
				ms.send(packet);
				if (message.trim().equalsIgnoreCase("ciao"))
					receiverThread.interrupt();
			} while (!message.trim().equalsIgnoreCase("ciao"));
			receiverThread.interrupt();
			ms.leaveGroup(group);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
