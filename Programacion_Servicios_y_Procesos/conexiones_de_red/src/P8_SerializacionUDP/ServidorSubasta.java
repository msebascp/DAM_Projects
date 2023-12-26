package P8_SerializacionUDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ServidorSubasta {
	public static void main(String[] args) {
		try (
				// Se establece un servidor en el puerto 6000
				DatagramSocket socket = new DatagramSocket(6000, InetAddress.getLocalHost())
		) {
			// Se establece el número máximo de clientes y productos
			int MAXCLIENTSANDPRODUCTS = 3;
			int clientsCounter = 0;
			int productsCounter = 0;
			// Se crea una lista de productos, para luego obtener el producto ganador
			ArrayList<Producto> productsList = new ArrayList<>();
			// Se crea una lista de paquetes, para guardar la información de los clientes
			ArrayList<DatagramPacket> packetsList = new ArrayList<>();
			// Mientras no se llegue al número máximo de clientes y productos
			do {
				// Se recibe algo del cliente
				byte[] received = new byte[1024];
				DatagramPacket packetReceived = new DatagramPacket(received, received.length);
				socket.receive(packetReceived);
				packetsList.add(packetReceived);
				String messageReceived = new String(packetReceived.getData()).trim();
				// Si el mensaje recibido es "hola", se envía el producto al cliente
				if (messageReceived.equalsIgnoreCase("hola")) {
					System.out.println("Cliente conectado");
					// Creo el producto de la subasta
					Producto product = new Producto("iPhone", 1000);
					ThreadSendProduct threadSendProduct = new ThreadSendProduct(packetReceived, product);
					threadSendProduct.start();
					clientsCounter++;
				}
				// Si el mensaje no es hola, se recibe el producto del cliente
				else {
					ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(received);
					ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
					Producto producto = (Producto) objectInputStream.readObject();
					System.out.println("Producto recibido: " + producto.toString());
					productsList.add(producto);
					productsCounter++;
				}
			} while (clientsCounter < MAXCLIENTSANDPRODUCTS || productsCounter < MAXCLIENTSANDPRODUCTS);
			// Se obtiene el producto ganador
			Producto winningProduct = Collections.max(productsList, Comparator.comparing(Producto::getPrecio));
			System.out.println("Producto ganador: " + winningProduct.toString());
			// Se envía el producto ganador a los clientes
			for (DatagramPacket packet : packetsList) {
				ThreadSendProduct threadSendFinalProduct = new ThreadSendProduct(packet, winningProduct);
				threadSendFinalProduct.start();
			}
		} catch (IOException e) {
			System.out.println("Error en ServidorSubasta: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
