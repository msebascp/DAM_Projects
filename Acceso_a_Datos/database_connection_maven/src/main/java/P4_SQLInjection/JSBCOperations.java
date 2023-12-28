package P4_SQLInjection;

import java.sql.*;

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

	public static ResultSet searchData(Connection connection, String sql) {
		Statement statement = createStatement(connection);
		try {
			statement.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("Error al buscar datos");
			throw new RuntimeException(e);
		}
		try {
			return statement.getResultSet();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected static PreparedStatement createPreparedStatement(Connection connection, String sql) {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Error al crear el PreparedStatement");
			throw new RuntimeException(e);
		}
	}

	public static ResultSet searchDataSecure(Connection connection, String sqlSearchPersona, String id, String password) {
		PreparedStatement preparedStatement = createPreparedStatement(connection, sqlSearchPersona);
		try {
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error al buscar datos");
			throw new RuntimeException(e);
		}
		try {
			return preparedStatement.getResultSet();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
