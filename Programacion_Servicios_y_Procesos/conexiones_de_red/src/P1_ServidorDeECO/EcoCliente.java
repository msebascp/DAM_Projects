package P1_ServidorDeECO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EcoCliente {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String response = "";
		try (
				// Iniciamos los recursos con un try with resource para que se cierren
				Socket mySocket = new Socket("localhost", 6000);
				DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
				DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
		) {
			// El cliente envía mensajes al servidor y espera la respuesta
			// hasta que recibe "CIAO"
			do {
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
