package P6_ChatMulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastChatThread extends Thread {
	private MulticastSocket ms;

	public MulticastChatThread(MulticastSocket ms) {
		this.ms = ms;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				byte[] buffer = new byte[1024];
				// Recibir un paquete de datos del grupo multicast
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				ms.receive(packet);
				// Extraer el mensaje del paquete y mostrarlo en la consola
				String receivedMessage = new String(packet.getData());
				System.out.println(receivedMessage.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
