package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import models.PumpState;

public class Database {

	public static ArrayList<PumpState> selectAllPumpStates() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<PumpState> pumpList = new ArrayList<>();
		
		try {
			// Prepare and execute Prepared Statement
			connection = Database.getConnection("jdbc:mysql://192.168.0.87:3306/tnsdb_1_12_5", "root", "123");
			String sql = "select * from PumpState WHERE ID >= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			resultSet = preparedStatement.executeQuery();

			// Process the ResultSet (if applicable)
			while (resultSet.next()) {
				pumpList.add(new PumpState(resultSet.getInt("ID"), resultSet.getString("name"),
						resultSet.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(resultSet);
			Database.closePreparedStatement(preparedStatement);
			Database.closeConnection(connection);
		}
		return pumpList;
	}

	public static Connection getConnection(String dbUrl, String user, String password) throws SQLException {

		Connection connection = DriverManager.getConnection(dbUrl, user, password);
		connection.setAutoCommit(true);
		return connection;

	}
	
	public static ConcurrentHashMap<String, PumpState> getPumpStateHashMap() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConcurrentHashMap<String, PumpState> pumpStateMap = new ConcurrentHashMap<>();
		
		try {
			// Prepare and execute Prepared Statement
			connection = Database.getConnection("jdbc:mysql://192.168.0.87:3306/tnsdb_1_12_5", "root", "123");
			String sql = "select * from PumpState WHERE ID >= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			resultSet = preparedStatement.executeQuery();

			// Process the ResultSet (if applicable)
			while (resultSet.next()) {
				pumpStateMap.put(new PumpState(resultSet.getInt("ID"), resultSet.getString("name"),
						resultSet.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(resultSet);
			Database.closePreparedStatement(preparedStatement);
			Database.closeConnection(connection);
		}
		return pumpStateMap;
	}
	
	
	
	
	
	

	public static void closeConnection(Connection connection) {

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException exception) {
			// exception.printStackTrace();
		}

	}

	public static void closeStatement(Statement statement) {

		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException exception) {
			// exception.printStackTrace();
		}

	}

	public static void closePreparedStatement(PreparedStatement preparedstatement) {

		try {
			if (preparedstatement != null) {
				preparedstatement.close();
			}
		} catch (SQLException exception) {
			// exception.printStackTrace();
		}

	}

	public static void closeResultSet(ResultSet resultSet) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException exception) {
			// exception.printStackTrace();
		}

	}
}