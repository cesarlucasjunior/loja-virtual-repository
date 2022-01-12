package br.com.cesarlucasjunior.lojavirtualrepository.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryOld {
	
	public Connection getConnectionDataBase() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", 
				"root", "");		
	}
	
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
