package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe abstrata responsável pela conexão com o banco.
 * 
 * @author Warley Vital
 */
public abstract class JDBCDAO {

	private Connection connection;

	/**
	 * Construtor da classe.
	 */
	public JDBCDAO() {

	}

	/**
	 * Este método abre a conexão com banco.
	 * 
	 */
	protected void abrirConexao() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/SuperBlogs", "root",
						"mynewpassword");
			else if (connection.isClosed())
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/SuperBlogs", "root",
						"mynewpassword");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método fecha a conexão com o banco.
	 */
	protected void fecharConexao() {
		try {
			if (!connection.isClosed())
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void resetarBD() {
		abrirConexao();
		String sql = "DROP DATABASE SuperBlogs";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}

	/**
	 * Este método retorna a conexão atual.
	 * 
	 * @return conexão atual, ou null se não houver
	 */
	protected Connection getConnection() {
		return connection;
	}
}
