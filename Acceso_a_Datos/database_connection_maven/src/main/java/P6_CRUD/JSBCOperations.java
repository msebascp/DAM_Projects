package P6_CRUD;

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

	public static ResultSet leerArtistas(Connection connection, String sql) {
		PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
		try {
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

	public static ResultSet crearArtista(Connection connection, String sql, String nombre, String fundacion, int miembros, String pais) {
		PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
		try {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, fundacion);
			preparedStatement.setInt(3, miembros);
			preparedStatement.setString(4, pais);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al crear el artista");
			throw new RuntimeException(e);
		}
		try {
			return preparedStatement.getResultSet();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet editarArtista(Connection connection, String sql, String nombre, String fundacion, int miembros, String pais, String id) {
		PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
		try {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, fundacion);
			preparedStatement.setInt(3, miembros);
			preparedStatement.setString(4, pais);
			preparedStatement.setString(5, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al editar el artista");
			throw new RuntimeException(e);
		}
		try {
			return preparedStatement.getResultSet();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet eliminarArtista(Connection connection, String sql, String id) {
		PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
		try {
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el artista");
			throw new RuntimeException(e);
		}
		try {
			return preparedStatement.getResultSet();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
