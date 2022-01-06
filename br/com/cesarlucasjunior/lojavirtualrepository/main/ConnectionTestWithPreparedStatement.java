package br.com.cesarlucasjunior.lojavirtualrepository.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.cesarlucasjunior.lojavirtualrepository.factory.ConnectionFactory;

public class ConnectionTestWithPreparedStatement {

	public static void main(String[] args) {
		System.out.println("Trying to establish a database connection...");
		try {
			Connection connection = new ConnectionFactory().getConnectionDataBase();
			System.out.println("Database connection established.");
			setProductInDataBase(connection, "Mouse", "Novo mouse logitech");
			deleteProductInDataBase(connection, 3);
			getResultDataBase(connection);
			connection.close();
			System.out.println("Database connection closed.");
		} catch (SQLException error) {
			System.out.println("Error connecting to database - " + error.getMessage());
		}
	}
	
	public static void getResultDataBase(Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUTO");
		preparedStatement.execute();
		
		ResultSet resultSet = preparedStatement.getResultSet();
		System.out.println("------------------------ REGISTROS ENCONTRADOS ------------------------");
		while(resultSet.next()) {
			System.out.println("ID: " + resultSet.getInt("ID"));
			System.out.println("NOME: " + resultSet.getString("NOME"));
			System.out.println("DESCRIÇÃO: " + resultSet.getString("DESCRICAO"));
			System.out.println("------------------------------------------------------");
		}
	}
	
	public static void setProductInDataBase(Connection connection, String nomeProduto, String descricaoProduto) throws SQLException {
		PreparedStatement prepareStatement = 
				connection.prepareStatement("INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)", 
						Statement.RETURN_GENERATED_KEYS);
		
		prepareStatement.setString(1, nomeProduto);
		prepareStatement.setString(2, descricaoProduto);
		
		prepareStatement.execute();
		
		ResultSet resultSet = prepareStatement.getGeneratedKeys();
		while(resultSet.next()) {
			System.out.println("Um novo produto foi cadastrado com sucesso!");
			System.out.println("ID do novo item: " + resultSet.getInt(1));
		}		
//		System.out.println(prepareStatement.getUpdateCount() + " rows created.");
	}
	
	public static void deleteProductInDataBase(Connection connection, Integer idParaDeletar) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");		
		preparedStatement.setInt(1, idParaDeletar);
		
		preparedStatement.execute();
		
		Integer numberOfRowsImpacted = preparedStatement.getUpdateCount();
		System.out.println(numberOfRowsImpacted + " rows removed.");
			
	}
}