package P8_SerializacionUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteSubasta {
	public static void main(String[] args) {
		try (
				// Se establece un cliente
				DatagramSocket socket = new DatagramSocket();
				Scanner sc = new Scanner(System.in)
		) {
			// Enviar saludo al servidor
			byte[] saludoCliente = "hola".getBytes();
			DatagramPacket packetClientSent = new DatagramPacket(saludoCliente, saludoCliente.length, InetAddress.getLocalHost(), 6000);
			socket.send(packetClientSent);

			// Recibir el producto del servidor
			byte[] productReceived = new byte[1024];
			DatagramPacket packet = new DatagramPacket(productReceived, productReceived.length, InetAddress.getLocalHost(), 6000);
			socket.receive(packet);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(productReceived);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Producto producto = (Producto) objectInputStream.readObject();
			System.out.println("Producto recibido: " + producto.getNombre() + " " + producto.getPrecio());

			// Se pide al usuario que escriba su nombre y su puja
			System.out.println("Escribe en una línea tu nombre y tu puja:");
			// Separamos el nombre de la puja por espacios
			String[] puja = sc.nextLine().split(" ");

			// Se envía el producto con la puja y el comprador al servidor
			producto.setComprador(puja[0]);
			producto.setPrecio(Integer.parseInt(puja[1]));

			// Enviar el producto con la puja y el comprador al servidor
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(producto);
			byte[] productSend = byteArrayOutputStream.toByteArray();
			DatagramPacket packet2 = new DatagramPacket(productSend, productSend.length, InetAddress.getLocalHost(), 6000);
			socket.send(packet2);

			//Recibir el producto final del servidor
			byte[] finalProductReceived = new byte[1024];
			DatagramPacket packetR = new DatagramPacket(finalProductReceived, finalProductReceived.length);
			socket.receive(packetR);
			ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(finalProductReceived);
			ObjectInputStream objectInputStream2 = new ObjectInputStream(byteArrayInputStream2);
			Producto productoFinal = (Producto) objectInputStream2.readObject();
			System.out.println("Producto final: " + productoFinal.toString());
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error en el cliente: " + e.getMessage());
		}
	}
}
