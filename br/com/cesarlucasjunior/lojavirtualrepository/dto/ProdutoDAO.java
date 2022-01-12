package br.com.cesarlucasjunior.lojavirtualrepository.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarlucasjunior.lojavirtualrepository.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void save(Produto produto) throws SQLException {
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(
				"INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getDescricao());
			preparedStatement.setInt(3, produto.getCategoriaId());

			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				while (resultSet.next()) {
					produto.setId(resultSet.getInt(1));
				}
			}
			
			System.out.println("ID DO PRODUTO - " + produto.getId());
		}
	}
	
	public List<Produto> list() throws SQLException{
		List<Produto> listaProduto = new ArrayList<Produto>();
		try(PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM PRODUTO")){
			preparedStatement.execute();
			
			try(ResultSet resultSet = preparedStatement.getResultSet()){
				while(resultSet.next()) {
					listaProduto.add(new Produto(resultSet.getInt("ID"), resultSet.getString("NOME"), resultSet.getString("DESCRICAO")));
				}
			}
		}
		return listaProduto;
	}

}
