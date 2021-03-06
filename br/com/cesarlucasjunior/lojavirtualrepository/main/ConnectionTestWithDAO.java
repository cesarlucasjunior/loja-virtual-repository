package br.com.cesarlucasjunior.lojavirtualrepository.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.cesarlucasjunior.lojavirtualrepository.dao.ProdutoDAO;
import br.com.cesarlucasjunior.lojavirtualrepository.factory.ConnectionFactory;
import br.com.cesarlucasjunior.lojavirtualrepository.model.Produto;

public class ConnectionTestWithDAO {
	
	public static void main(String[] args) throws SQLException {
		
		Produto p1 = new Produto("C?moda", "C?moda Horizontal", 2);
		
		//The try with resource will be the responsible to close the resources i.e the connection.
		try(Connection connection = new ConnectionFactory().getConnectionDataBase()) {
			ProdutoDAO produtoDTO = new ProdutoDAO(connection);
			produtoDTO.save(p1);
			System.out.println("---------------- LISTAGEM DE DADOS TABELA PRODUTO ----------------");
			for(Produto produto : produtoDTO.list()) {
				System.out.println(produto.toString());
			}
		}
	}
}
