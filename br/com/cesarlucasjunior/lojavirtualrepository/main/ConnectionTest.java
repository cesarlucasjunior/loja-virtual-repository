package br.com.cesarlucasjunior.lojavirtualrepository.main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.cesarlucasjunior.lojavirtualrepository.factory.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) {
		
		System.out.println("Trying to establish a database connection...");
		
		try {
			Connection connection = new ConnectionFactory().getConnectionDataBase();
			System.out.println("Database connection established.");
			getResultDataBase(connection);
			connection.close();
			System.out.println("Database connection closed.");
		} catch (SQLException error) {
			System.out.println("Error connecting to database - " + error.getMessage());
		}
	}
	
	public static void getResultDataBase(Connection connection) throws SQLException {		
		Statement statement = connection.createStatement(); //Creating a SQL query
		Boolean isAList = statement.execute("SELECT * FROM PRODUTO");
		System.out.println("The statement return a list: " + isAList);
		//Getting the result of statement:
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()){
			Integer id = resultSet.getInt("ID");
			String nome = resultSet.getString("NOME");
			String descricao = resultSet.getString("DESCRICAO");
			
			System.out.println("ID: " + id + " NOME: " + nome + " DESCRIÇÃO: " + descricao);
			System.out.println("-------------------------");
		}
	} 
}