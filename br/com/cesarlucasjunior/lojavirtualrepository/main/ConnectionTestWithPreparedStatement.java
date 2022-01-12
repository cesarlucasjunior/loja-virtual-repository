package br.com.cesarlucasjunior.lojavirtualrepository.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.cesarlucasjunior.lojavirtualrepository.factory.ConnectionFactory;

public class ConnectionTestWithPreparedStatement {

	public static void main(String[] args) throws SQLException {
		System.out.println("Trying to establish a database connection...");
		
		try(Connection connection = new ConnectionFactory().getConnectionDataBase()){
			connection.setAutoCommit(false);
			try {
				System.out.println("Database connection established.");
				setProductInDataBase(connection, "Mouse", "Novo mouse logitech");
				setProductInDataBase(connection, "Rádio", "Coisa de vovó");
				deleteProductInDataBase(connection, 3);
				getResultDataBase(connection);
				connection.commit();
				System.out.println("Database connection closed.");
			} catch (Exception error) {
				error.printStackTrace();
				System.out.println("An error occurred while operating with the database.");
				connection.rollback();
				System.out.println("Rollback completed!");
			}
		}
	}
	
	public static void getResultDataBase(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUTO")){
			preparedStatement.execute();
			
			try(ResultSet resultSet = preparedStatement.getResultSet()){
				System.out.println("------------------------ REGISTROS ENCONTRADOS ------------------------");
				while(resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("ID"));
					System.out.println("NOME: " + resultSet.getString("NOME"));
					System.out.println("DESCRIÇÃO: " + resultSet.getString("DESCRICAO"));
					System.out.println("------------------------------------------------------");
				}
				//resultSet.close();
			}
			//preparedStatement.close();
		}
	}
	
	public static void setProductInDataBase(Connection connection, String nomeProduto, String descricaoProduto) throws Exception {
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement("INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)", 
						Statement.RETURN_GENERATED_KEYS)){
		
			preparedStatement.setString(1, nomeProduto);
			preparedStatement.setString(2, descricaoProduto);
			
			preparedStatement.execute();
			
			try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
				while(resultSet.next()) {
					System.out.println("Um novo produto foi cadastrado com sucesso!");
					System.out.println("ID do novo item: " + resultSet.getInt(1));
				}
			}
			//Returning the number of rows impacted with the query:
			//System.out.println(prepareStatement.getUpdateCount() + " rows created.");
		}
	}
	
	public static void deleteProductInDataBase(Connection connection, Integer idParaDeletar) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?")){		
			preparedStatement.setInt(1, idParaDeletar);
			
			preparedStatement.execute();
			
			Integer numberOfRowsImpacted = preparedStatement.getUpdateCount();
			System.out.println(numberOfRowsImpacted + " rows removed.");
			preparedStatement.close();
		}
	}
}