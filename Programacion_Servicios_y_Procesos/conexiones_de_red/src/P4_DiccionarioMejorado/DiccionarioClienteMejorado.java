package P4_DiccionarioMejorado;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DiccionarioClienteMejorado {
	public static void main(String[] args) {
		// Pedir la dirección IP y el puerto del servidor
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce la dirección IP del servidor: ");
		String dirIp = sc.nextLine();
		System.out.print("Introduce el puerto del servidor: ");
		int port = sc.nextInt();
		sc.nextLine();
		try (DatagramSocket mySocket = new DatagramSocket()) {
			String text = "";
			do {
				System.out.print("Introduce la palabra a traducir: ");
				text = sc.nextLine();
				// array de bytes por el que se enviará el mensaje
				byte[] message = text.getBytes();
				// creamos el paquete con el mensaje, la longitud, el nombre del host o la ip, y el puerto
				DatagramPacket packetServer = new DatagramPacket(message, message.length, InetAddress.getByName(dirIp), port);
				// enviamos el paquete por el socket.
				mySocket.send(packetServer);
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
			} while (!text.trim().equalsIgnoreCase("stop"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
