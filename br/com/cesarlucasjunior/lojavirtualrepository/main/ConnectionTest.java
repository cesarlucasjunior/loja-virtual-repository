package br.com.cesarlucasjunior.lojavirtualrepository.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		
		System.out.println("Trying to establish a database connection...");
		
		try {
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", 
					"root", "");
			System.out.println("Database connection established.");
			connection.close();
			System.out.println("Database connection closed.");
		} catch (SQLException error) {
			System.out.println("Error connecting to database - " + error.getMessage());
		}

	}
}