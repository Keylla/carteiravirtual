package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ifce.jwallet.exception.DaoException;

public class ConnectionFactory {

	// OpenShit connection
	private static String JDBC_DRIVER = "org.postgresql.Driver";
	//private static String JDBC_URL = "jdbc:postgresql://127.5.217.2:5432/carteiravirtual";
	//private static String JDBC_USER = "adminli4abet";
	//private static String JDBC_PASSWORD = "3jBGjM7RgKAs";
	
	// local connection
//	private static String JDBC_DRIVER = "org.postgresql.Driver";
	private static String JDBC_URL = "jdbc:postgresql://localhost:5433/jwallet";
	private static String JDBC_USER = "postgres";
	private static String JDBC_PASSWORD = "123";
//	private static String JDBC_PASSWORD = "UWtIwP1cYwsA";	

	public static Connection getConnection() throws DaoException {

		try {

			Class.forName(JDBC_DRIVER);

			return DriverManager.getConnection(JDBC_URL, JDBC_USER,
					JDBC_PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DaoException(
					"Conex�o n�o dispon�vel no momento, tente mais tarde.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(
					"Conex�o n�o dispon�vel no momento, tente mais tarde.");
		}

	}
	
	
	public static void closeConnection(Connection con, PreparedStatement pstm ){
		
		
		
			try {
				
				if (pstm != null)
				pstm.close();
				
				if (con != null)
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
}
