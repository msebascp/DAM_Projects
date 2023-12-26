package P8_SerializacionUDP;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadSendProduct extends Thread {
	private final DatagramPacket packet;

	private final Producto product;

	public ThreadSendProduct(DatagramPacket packet, Producto finalProduct) {
		this.packet = packet;
		this.product = finalProduct;
	}

	@Override
	public void run() {
		try (
				// Se establece un servidor utilizando un DatagramSocket
				// sin especificar nada, ya que solo se va a enviar un objeto
				DatagramSocket socket = new DatagramSocket();
				// Se prepara el canal de salida de objetos hacia el cliente
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
		) {
			// Se prepara el objeto para ser enviado
			objectOutputStream.writeObject(product);
			// Se convierte el objeto serializado a un array de bytes
			byte[] finalProductSend = byteArrayOutputStream.toByteArray();
			// Se obtiene el puerto y la dirección del cliente a partir del DatagramPacket original
			int clientPort = packet.getPort();
			InetAddress clientAddress = packet.getAddress();

			// Se crea un nuevo DatagramPacket con los datos serializados y la información del cliente
			DatagramPacket packetSend = new DatagramPacket(finalProductSend, finalProductSend.length, clientAddress, clientPort);

			// Se envía el DatagramPacket al cliente
			socket.send(packetSend);
		} catch (Exception e) {
			System.out.println("Error en ThreadSendFinalProduct: " + e.getMessage());
		}

	}
}
