package P3_ServidorDeECOMejorado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EcoClienteMejorado {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String response = "";
		System.out.print("Introduce la dirección IP del servidor: ");
		String dirIp = scanner.nextLine();
		System.out.print("Introduce el puerto del servidor: ");
		int port = scanner.nextInt();
		scanner.nextLine();
		try (
				// Iniciamos los recursos con un try with resource para que se cierren
				Socket mySocket = new Socket(dirIp, port);
				DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
				DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
		) {
			// El cliente envía mensajes al servidor y espera la respuesta
			// hasta que recibe "CIAO"
			do {
				System.out.print("Envía un mensaje: ");
				String message = scanner.nextLine();
				socketOut.writeUTF(message);
				response = socketIn.readUTF();
				System.out.println("Mensaje del servidor: " + response);
			} while (!response.trim().equals("CIAO"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
