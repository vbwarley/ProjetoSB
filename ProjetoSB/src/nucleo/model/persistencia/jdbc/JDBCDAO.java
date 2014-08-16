package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCDAO {

	private Connection connection;
	
	public JDBCDAO() {
		
	}
	
	public void abrirConexao() {
		try {
			if (connection != null)
				connection = DriverManager.getConnection("jdbc:mysql://localhost:/banco_sb","","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fecharConexao() {
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
