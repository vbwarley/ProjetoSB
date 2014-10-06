package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata responsável pela conexão com o banco.
 * 
 * @author Warley Vital
 */
public abstract class JDBCDAO {

	private static Connection connection;
	

	/**
	 * Construtor da classe.
	 */
	public JDBCDAO() {

	}

	/**
	 * Este método abre a conexão com banco.
	 * 
	 */
	protected static void abrirConexao() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			if (connection == null)
//				connection = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/SuperBlogs", "root",
//						"mynewpassword");
//			else if (connection.isClosed())
//				connection = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/SuperBlogs", "root",
//						"mynewpassword");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperBlogs", "root", "mynewpassword");
//            statement = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
		}
	}

	/**
	 * Este método fecha a conexão com o banco.
	 */
	protected static void fecharConexao() {
		try {
			if (connection != null)
				connection.close();				

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void resetarBD() {

		List<String> entidades = new ArrayList<String>();
		entidades.add("usuario");
		entidades.add("blog");
		entidades.add("postagem");
		entidades.add("palavras_chave");
		entidades.add("postagem_palavras");
		entidades.add("comentario");
		entidades.add("assinatura");
		entidades.add("tipo_midia");
		entidades.add("midia");

		for (String entidade : entidades) {
			abrirConexao();
			PreparedStatement stm = null;

			String sql = "DELETE FROM " + entidade;

			try {
				stm = getConnection().prepareStatement(sql);
				stm.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				fecharConexao();
			}
		}

		entidades.clear();
		entidades.add("blog");
		entidades.add("comentario");
		entidades.add("midia");
		entidades.add("palavras_chave");
		entidades.add("postagem");

		for (String entidade : entidades) {
			abrirConexao();
			PreparedStatement stm = null;

			String sql = "ALTER TABLE " + entidade + " AUTO_INCREMENT = 1";

			try {
				stm = getConnection().prepareStatement(sql);
				stm.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				fecharConexao();
			}
		}
	}

	/**
	 * Este método retorna a conexão atual.
	 * 
	 * @return conexão atual, ou null se não houver
	 */
	protected static Connection getConnection() {
		return connection;
	}
}
