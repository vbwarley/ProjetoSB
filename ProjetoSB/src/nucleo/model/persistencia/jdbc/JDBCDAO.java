package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCDAO {

	private Connection connection;
	
	public JDBCDAO() {
		
	}
	
	protected void abrirConexao() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection("jdbc:mysql://localhost/SuperBlogs","root","warley");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void fecharConexao() {
		try {
			if (!connection.isClosed()) {
				connection.close();
				this.connection = null;
			} else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		return connection;
	}
}
