package br.com.cesarlucasjunior.lojavirtualrepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.cesarlucasjunior.lojavirtualrepository.model.Categoria;

public class CategoriaDAO {

	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Categoria categoria) throws SQLException {
		try(PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO CATEGORIA (NOME) VALUES (?)", Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1, categoria.getNome());
			
			preparedStatement.execute();
			
			try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
				while(resultSet.next()) {
					System.out.println("ID DA CATEGORIA GERADA: " + resultSet.getInt("ID"));
				}
			}
		}
	}
}
