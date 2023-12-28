package P3_SQLInjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	private static final String driver = "com.mysql.cj.jdbc.Driver";

	private MySQLConexion() {
	}

	public static Connection newInstance(String url, String user, String password) throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar con la base de datos, en el método newInstance()");
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos, en el método newInstance()");
			e.printStackTrace();
		}
		return connection;
	}
}
