package br.com.cesarlucasjunior.lojavirtualrepository.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.cesarlucasjunior.lojavirtualrepository.dto.ProdutoDAO;
import br.com.cesarlucasjunior.lojavirtualrepository.factory.ConnectionFactory;
import br.com.cesarlucasjunior.lojavirtualrepository.model.Produto;

public class ConnectionTestWithDAO {
	
	public static void main(String[] args) throws SQLException {
		
		Produto p1 = new Produto("Cômoda", "Cômoda Horizontal", 2);
		
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
