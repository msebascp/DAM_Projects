package P1_CrearConexionesYTablas;

import java.sql.Connection;
import java.sql.Statement;

public abstract class JSBCOperations {
	public static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (Exception e) {
			System.out.println("Error al crear el Statement");
			throw new RuntimeException(e);
		}
	}

	public static void createTable(Connection connection, String sql) {
		Statement statement = createStatement(connection);
		try {
			statement.execute(sql);
		} catch (Exception e) {
			System.out.println("Error al crear la tabla");
			throw new RuntimeException(e);
		}
	}
}
