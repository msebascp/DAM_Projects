package P2_Diccionario;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DiccionarioCliente {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (DatagramSocket mySocket = new DatagramSocket()) {
			// Crear el paquete a enviar
			String text = sc.nextLine();
			// array de bytes.
			byte[] message = text.getBytes();
			// creamos el paquete con el mensaje, la longitud, la IP y el puerto
			// usamos getLocalHost() porque el servidor está en la misma máquina el que cliente.
			DatagramPacket packetS = new DatagramPacket(message, message.length, InetAddress.getLocalHost(), 2000);
			// enviamos el paquete por el socket.
			mySocket.send(packetS);

			// Recibir la respuesta
			// creamos un array de bytes para recibir los datos
			byte[] buffer = new byte[1024];
			// creamos el paquete vacío con el buffer para almacenar los datos
			DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
			// Esperamos recibir una respuesta durante 5 segundos
			// si no hay respuesta, se lanza un mensaje de error
			mySocket.setSoTimeout(5000);
			try {
				mySocket.receive(packetR);
				// convertimos los datos recibidos en un String
				String messageReceived = new String(packetR.getData()).trim();
				System.out.println("Recibido: " + messageReceived);
			} catch (IOException e) {
				System.out.println("No se ha encontrado la palabra");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
