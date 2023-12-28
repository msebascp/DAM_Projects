package P1_CrearConexionesYTablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Conexion {
	private H2Conexion() {
	}

	public static Connection newInstance(String url, String user, String password) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
