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

	private static Connection connection = getConnection();
//	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/SuperBlogs";
	private static final String USER = "root";
	private static final String PASS = "mynewpassword";
	private static int qtd = 0;
	/**
	 * Construtor da classe.
	 */
	public JDBCDAO() {

	}

	/**
	 * Este método abre a conexão com banco.
	 * 
	 */
	private static void abrirConexao() {

		try {
			System.out.println(qtd++);
			 
			connection = DriverManager.getConnection(URL, USER, PASS);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Este método fecha a conexão com o banco.
	 */
	protected static void fecharConexao() {
		try {
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
			
			PreparedStatement stmt = null;

			String sql = "DELETE FROM " + entidade;

			try {
				stmt = getConnection().prepareStatement(sql);
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		entidades.clear();
		entidades.add("blog");
		entidades.add("comentario");
		entidades.add("midia");
		entidades.add("palavras_chave");
		entidades.add("postagem");

		for (String entidade : entidades) {
			
			PreparedStatement stmt = null;

			String sql = "ALTER TABLE " + entidade + " AUTO_INCREMENT = 1";

			try {
				stmt = getConnection().prepareStatement(sql);
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este método retorna a conexão atual.
	 * 
	 * @return conexão atual, ou null se não houver
	 */
	protected static Connection getConnection() {
		if (connection == null)
			abrirConexao();
		return connection;
	}
}
