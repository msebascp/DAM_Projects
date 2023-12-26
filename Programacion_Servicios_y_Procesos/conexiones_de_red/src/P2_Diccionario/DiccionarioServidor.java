package P2_Diccionario;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class DiccionarioServidor {
	public static void main(String[] args) {
		HashMap<String, String> diccionario = new HashMap<>();
		diccionario.put("hola", "hello");
		diccionario.put("adiós", "bye");
		diccionario.put("casa", "house");
		diccionario.put("perro", "dog");
		diccionario.put("gato", "cat");
		diccionario.put("ratón", "mouse");
		diccionario.put("ordenador", "computer");

		// El servidor escucha en la dirección local en el puerto 2000
		try (DatagramSocket mySocket = new DatagramSocket(2000, InetAddress.getLocalHost())) {
			// Recibir el mensaje. Es igual que en el cliente.
			byte[] buffer = new byte[1024];
			DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
			mySocket.receive(packetR);
			String messageReceived = new String(packetR.getData()).trim();
			System.out.println("Recibido: " + messageReceived);

			if (diccionario.containsKey(messageReceived)) {
				// Preparar la respuesta
				// Conseguir el puerto para responder al cliente
				int destPort = packetR.getPort();
				//Conseguir la IP para responder al cliente
				InetAddress destAddr = packetR.getAddress();
				// Crear el mensaje de respuesta
				String text = diccionario.get(messageReceived);
				byte[] message = text.getBytes();
				// Crear el DatagramPacket
				DatagramPacket packetS = new DatagramPacket(message, message.length, destAddr, destPort);
				// enviarlo por el socket
				mySocket.send(packetS);
			} else {
				System.out.println("No se ha encontrado la palabra");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
