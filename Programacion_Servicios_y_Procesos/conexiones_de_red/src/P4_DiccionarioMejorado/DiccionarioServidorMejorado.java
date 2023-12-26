package P4_DiccionarioMejorado;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class DiccionarioServidorMejorado {
	public static void main(String[] args) {
		HashMap<String, String> diccionario = new HashMap<>();
		diccionario.put("hola", "hello");
		diccionario.put("adiós", "bye");
		diccionario.put("casa", "house");
		diccionario.put("perro", "dog");
		diccionario.put("gato", "cat");
		diccionario.put("ratón", "mouse");
		diccionario.put("ordenador", "computer");
		// El servidor escucha en la dirección localhost 127.0.0.1 en el puerto 2000
		try (DatagramSocket mySocket = new DatagramSocket(2000, InetAddress.getLoopbackAddress())
		) {
			System.out.println("Escuchando...");
			// Bucle infinito para escuchar peticiones
			// Es una mala práctica, pero en este caso se hace para aprender a usar sockets
			while (true) {
				// Recibir el mensaje. Es igual que en el cliente.
				byte[] received = new byte[1024];
				DatagramPacket packetR = new DatagramPacket(received, received.length);
				mySocket.receive(packetR);
				System.out.println("Paquete recibido");
				String messageReceived = new String(packetR.getData()).trim();
				System.out.println("Recibido: " + messageReceived);

				if (diccionario.containsKey(messageReceived)) {
					// Conseguir el puerto y la dirección del cliente
					int destPort = packetR.getPort();
					InetAddress destAddr = packetR.getAddress();
					// Crear el mensaje de respuesta
					String text = diccionario.get(messageReceived);
					byte[] message = text.getBytes();
					// Crear el DatagramPacket
					DatagramPacket packetServer = new DatagramPacket(message, message.length, destAddr, destPort);
					// enviarlo por el socket
					mySocket.send(packetServer);
				} else {
					System.out.println("No se ha encontrado la palabra");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
