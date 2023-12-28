package P5_Wildcard;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
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
					break;
				case "5":
					showPersonaByIdMySQL();
					break;
				case "6":
					showPersonaByIdH2();
					break;
				case "7":
					sqlInjectionMySQL();
					break;
				case "8":
					sinSQLInjectionMySQL();
					break;
				case "9":
					wildCardSQLConsultations();
					break;
				case "10":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!option.equals("10"));
		sc.close();
	}

	public static void menu() {
		System.out.println("1. Crear conexión MySQL");
		System.out.println("2. Crear conexión H2");
		System.out.println("3. Crear tablas en MySQL");
		System.out.println("4. Crear tablas en H2");
		System.out.println("5. Mostrar persona por id en MySQL");
		System.out.println("6. Mostrar persona por id en H2");
		System.out.println("7. SQL Injection en MySQL");
		System.out.println("8. Sin SQL Injection en MySQL");
		System.out.println("9. Wild Card SQL");
		System.out.println("10. Salir");
		System.out.print("Opción: ");
	}

	public static Connection crearConexionMySQL() {
		String urlMySQL = "jdbc:mysql://localhost/hospital";
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
		String urlH2 = "jdbc:h2:" + Path.of("hospital").toAbsolutePath();
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

	public static String sqlCreateTablePacientes = "CREATE TABLE IF NOT EXISTS `pacientes` " +
			" (`id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
			"  `nombre` VARCHAR(30) NOT NULL, " +
			"  `direccion` VARCHAR(30) NOT NULL, " +
			"  `telefono` INTEGER NOT NULL)";

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
		JSBCOperations.createTable(connection, sqlCreateTablePacientes);
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Tabla pacientes creada");
	}

	public static void showPersonaByIdMySQL() {
		Connection connection = crearConexionMySQL();
		showPersonaById(connection);
	}

	public static void showPersonaByIdH2() {
		Connection connection = crearConexionH2();
		showPersonaById(connection);
	}

	public static void showPersonaById(Connection connection) {
		System.out.print("Id del opositor a buscar: ");
		int id = sc.nextInt();
		sc.nextLine();

		String sqlSearchPersona = "SELECT * FROM opositores WHERE id = " + id;

		ResultSet resultSet = JSBCOperations.searchData(connection, sqlSearchPersona);

		if (resultSet != null) {
			try {
				System.out.println("Datos del opositor con id " + id);
				while (resultSet.next()) {
					System.out.println("Id: " + resultSet.getInt("id"));
					System.out.println("Nombre: " + resultSet.getString("nombre"));
					System.out.println("Nacimiento: " + resultSet.getString("nacimiento"));
					System.out.println("Teléfono: " + resultSet.getInt("telefono"));
				}
			} catch (SQLException e) {
				System.out.println("Error al mostrar los datos");
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("No se ha encontrado ningún opositor con ese id");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
		System.out.println("Conexión MySQL cerrada");
	}

	public static void sqlInjectionMySQL() {
		Connection connection = crearConexionH2();
		System.out.print("Id del paciente: ");
		String id = sc.nextLine();
		System.out.print("Dirección del paciente: ");
		String direction = sc.nextLine();

		String sqlSearchPaciente = "SELECT * FROM pacientes WHERE id = '" + id + "' AND direccion = '" + direction + "'";

		ResultSet resultSet = JSBCOperations.searchData(connection, sqlSearchPaciente);

		if (resultSet != null) {
			try {
				while (!resultSet.isLast()) {
					System.out.println("Datos del paciente ->");
					resultSet.next();
					System.out.println("Id: " + resultSet.getInt("id"));
					System.out.println("Nombre: " + resultSet.getString("nombre"));
					System.out.println("Dirección: " + resultSet.getString("direccion"));
					System.out.println("Teléfono: " + resultSet.getInt("telefono"));
				}
			} catch (SQLException e) {
				System.out.println("Error al mostrar los datos");
			}
		} else {
			System.out.println("No se ha encontrado ningún paciente con ese id");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
	}

	public static void sinSQLInjectionMySQL() {
		Connection connection = crearConexionH2();
		System.out.print("Id del paciente: ");
		String id = sc.nextLine();
		System.out.print("Dirección del paciente: ");
		String direction = sc.nextLine();

		String sqlSearchPersona = "SELECT * FROM pacientes WHERE id = ? AND direccion = ?;";

		ResultSet resultSet = JSBCOperations.searchDataSecure(connection, sqlSearchPersona, id, direction);
		if (resultSet != null) {
			try {
				while (!resultSet.isLast()) {
					System.out.println("Datos del paciente ->");
					resultSet.next();
					System.out.println("Id: " + resultSet.getInt("id"));
					System.out.println("Nombre: " + resultSet.getString("nombre"));
					System.out.println("Dirección: " + resultSet.getString("direccion"));
					System.out.println("Teléfono: " + resultSet.getInt("telefono"));
				}
			} catch (SQLException e) {
				System.out.println("Error al mostrar los datos");
			}
		} else {
			System.out.println("No se ha encontrado ningún paciente con ese id");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
	}

	public static void wildCardSQLConsultations() {
		wildCardSQL("SELECT * FROM pacientes WHERE nombre LIKE ? || '%'", "J");
		wildCardSQL("SELECT * FROM pacientes WHERE nombre LIKE '%' || ?", "n");
		wildCardSQL("SELECT * FROM pacientes WHERE nombre NOT LIKE '%' || ? || '%'", "a");
		wildCardSQL("SELECT * FROM pacientes WHERE nombre NOT LIKE '%' || ? || '%'", "e");

	}

	public static void wildCardSQL(String selectSQL, String letter) {
		Connection connection = crearConexionH2();

		ResultSet resultSet = JSBCOperations.searchDataSecureWithWildCards(connection, selectSQL, letter);
		if (resultSet != null) {
			try {
				while (!resultSet.isLast()) {
					System.out.println("Datos del paciente ->");
					resultSet.next();
					System.out.println("Id: " + resultSet.getInt("id"));
					System.out.println("Nombre: " + resultSet.getString("nombre"));
					System.out.println("Dirección: " + resultSet.getString("direccion"));
					System.out.println("Teléfono: " + resultSet.getInt("telefono"));
				}
			} catch (SQLException e) {
				System.out.println("Error al mostrar los datos");
			}
		} else {
			System.out.println("No se ha encontrado ningún paciente con ese id");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			throw new RuntimeException(e);
		}
	}
}
