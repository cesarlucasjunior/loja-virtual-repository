package br.com.cesarlucasjunior.lojavirtualrepository.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooled = new ComboPooledDataSource();
		comboPooled.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooled.setUser("root");
		comboPooled.setPassword("");
		
		this.dataSource = comboPooled;
	}

	public Connection getConnectionDataBase() throws SQLException {
		return this.dataSource.getConnection();
	}
}
