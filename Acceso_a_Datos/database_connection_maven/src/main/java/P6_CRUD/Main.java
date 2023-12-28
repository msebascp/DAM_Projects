package P6_CRUD;

import javax.swing.*;
import java.awt.*;
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
			option = JOptionPane.showInputDialog(menu());
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
					crearNuevoArtista();
					break;
				case "4":
					mostrarArtistas();
					break;
				case "5":
					actualizarArtista();
					break;
				case "6":
					eliminarArtista();
					break;
				case "7":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
			}
		} while (!option.equals("7"));
		sc.close();
	}

	public static String menu() {
		return """
				1. Crear conexión MySQL
				2. Crear conexión H2
				3. Crear nuevo artista
				4. Ver a todos los artistas
				5. Actualizar artista
				6. Eliminar artista
				7. Salir
				Opción:\s""";
	}

	private static void eliminarArtista() {
		try {
			String id = JOptionPane.showInputDialog("Id del artista a eliminar: ");
			Connection connection = crearConexionMySQL();
			// SQL segura para evitar SQL Injection
			String sql = "DELETE FROM artistas WHERE id = ? ;";
			ResultSet resultSet = JSBCOperations.eliminarArtista(connection, sql, id);
			JOptionPane.showMessageDialog(null, "Artista eliminado correctamente");
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void actualizarArtista() {
		try {
			String id = JOptionPane.showInputDialog("Id del artista a actualizar: ");
			String nombre = JOptionPane.showInputDialog("Nombre del artista: ");
			String fundacion = JOptionPane.showInputDialog("Fundación del artista: ");
			int miembros = Integer.parseInt(JOptionPane.showInputDialog("Número de miembros del artista: "));
			String pais = JOptionPane.showInputDialog("País del artista: ");
			Connection connection = crearConexionMySQL();
			// SQL segura para evitar SQL Injection
			String sql = "UPDATE artistas SET nombre = ?, fundacion = ?, miembros = ?, pais = ? WHERE id = ? ;";
			ResultSet resultSet = JSBCOperations.editarArtista(connection, sql, nombre, fundacion, miembros, pais, id);
			JOptionPane.showMessageDialog(null, "Artista creado correctamente");
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void mostrarArtistas() {
		try {
			Connection connection = crearConexionMySQL();
			String sql = "SELECT * from artistas";
			ResultSet resultSet = JSBCOperations.leerArtistas(connection, sql);
			try {
				if (resultSet != null) {
					StringBuilder mensaje = new StringBuilder();
					while (!resultSet.isLast()) {
						mensaje.append("Datos del artista ->\n");
						resultSet.next();
						mensaje.append("Id: ").append(resultSet.getInt("id")).append(", ");
						mensaje.append("Nombre: ").append(resultSet.getString("nombre")).append(", ");
						mensaje.append("Fundación: ").append(resultSet.getString("fundacion")).append(", ");
						mensaje.append("Miembros: ").append(resultSet.getInt("miembros")).append(", ");
						mensaje.append("País: ").append(resultSet.getString("pais")).append("\n");
					}
					JTextArea textArea = new JTextArea(mensaje.toString());
					textArea.setEditable(false);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setViewportView(textArea);
					scrollPane.setPreferredSize(new Dimension(500, 500));
					JOptionPane.showMessageDialog(null, scrollPane);
				}
			} catch (SQLException e) {
				System.out.println("Error al mostrar los datos");
				throw new RuntimeException(e);
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void crearNuevoArtista() {
		try {
			String nombre = JOptionPane.showInputDialog("Nombre del artista: ");
			String fundacion = JOptionPane.showInputDialog("Fundación del artista: ");
			int miembros = Integer.parseInt(JOptionPane.showInputDialog("Número de miembros del artista: "));
			String pais = JOptionPane.showInputDialog("País del artista: ");
			Connection connection = crearConexionMySQL();
			// SQL segura para evitar SQL Injection
			String sql = "INSERT INTO artistas (nombre, fundacion, miembros, pais) VALUES (?, ?, ?, ?) ;";
			ResultSet resultSet = JSBCOperations.crearArtista(connection, sql, nombre, fundacion, miembros, pais);
			JOptionPane.showMessageDialog(null, "Artista creado correctamente");
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection crearConexionMySQL() {
		String urlMySQL = "jdbc:mysql://localhost/musica";
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
		String urlH2 = "jdbc:h2:" + Path.of("hospital").toAbsolutePath().toString();
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
}
