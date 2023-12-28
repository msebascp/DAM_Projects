package P1_CrearConexionesYTablas;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String option;
		do {
			System.out.println();
			menu();
			option = sc.nextLine();
			switch (option) {
				case "1":
					System.out.println("Conexión MySQL:");
					mysql_conexion();
					break;
				case "2":
					System.out.println("Conexión H2:");
					h2_conexion();
					break;
				case "3":
					createTablesMySQL();
					break;
				case "4":
					createTablesH2();
				case "5":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!option.equals("5"));
		sc.close();
	}

	public static void menu() {
		System.out.println("1. Crear conexión MySQL");
		System.out.println("2. Crear conexión H2");
		System.out.println("3. Crear tablas en MySQL");
		System.out.println("4. Crear tablas en H2");
		System.out.println("5. Salir");
		System.out.print("Opción: ");
	}

	public static Connection crearConexionMySQL() {
		String urlMySQL = "jdbc:mysql://localhost/oposiciones";
		String userMySQL = "root";
		String passwordMySQL = "";

		Connection connection;
		try {
			connection = MySQLConexion.newInstance(urlMySQL, userMySQL, passwordMySQL);
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos, en el método crearConexionMySQL()");
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static Connection crearConexionH2() {
		String urlH2 = "jdbc:h2:" + Path.of("oposiciones").toAbsolutePath();
		String userH2 = "root";
		String passwordH2 = "root";

		Connection connection;
		try {
			connection = H2Conexion.newInstance(urlH2, userH2, passwordH2);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static void h2_conexion() {
		Connection connection = crearConexionH2();
		System.out.println("Conexión establecida");
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Conexión H2 cerrada");
	}

	public static void mysql_conexion() {
		Connection connection = crearConexionMySQL();
		System.out.println("Conexión establecida");
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Conexión MySQL cerrada");
	}

	public static String sqlCreateTablePersonas = "CREATE TABLE IF NOT EXISTS `personas` " +
			" (`id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
			"  `nombre` varchar(30) NOT NULL, " +
			"  `password` varchar(30) NOT NULL, " +
			"  `telefono` INTEGER NOT NULL)";

	public static String sqlCreateTableDirecciones = "CREATE TABLE IF NOT EXISTS `direcciones` " +
			" (`id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
			"  `persona_id` INTEGER NOT NULL, " +
			"  `direccion` VARCHAR(50) NOT NULL, " +
			"  FOREIGN KEY (persona_id) REFERENCES personas(id))";

	public static void createTablesMySQL() {
		Connection connection = crearConexionMySQL();
		JSBCOperations.createTable(connection, sqlCreateTablePersonas);
		JSBCOperations.createTable(connection, sqlCreateTableDirecciones);
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Conexión MySQL cerrada");
	}

	public static void createTablesH2() {
		Connection connection = crearConexionH2();
		JSBCOperations.createTable(connection, sqlCreateTablePersonas);
		JSBCOperations.createTable(connection, sqlCreateTableDirecciones);
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Conexión H2 cerrada");
	}
}