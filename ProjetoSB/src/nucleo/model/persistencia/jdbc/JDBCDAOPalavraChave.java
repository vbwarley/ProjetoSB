package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PalavraChave;
import nucleo.model.persistencia.dao.DAOPalavraChave;

/**
 * Classe para criacao de Palavras Chaves
 * @author Raiane
 */

public class JDBCDAOPalavraChave extends JDBCDAO implements
		DAOPalavraChave<PalavraChave, Integer> {

	/**
	 * Método construtor da classe JDBCDAOPalavraChave
	 * @author Raiane
	 */
	
	public JDBCDAOPalavraChave() {

	}

	@Override
	
	/**
	 * Método para criação de palavras chaves em um blog
	 * @param objeto PalavraChave - parâmetro passado ao método criar
	 * @author raiane
	 */
	
	public void criar(PalavraChave objeto) {
		abrirConexao();
		String insertSql = "INSERT INTO palavras_chave (nome) VALUES (?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(
					insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, objeto.getNome());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	
	/**
	 * Método para consulta de palavras chaves de um blog
	 * @param id Integer - identificador de uma palavra chave
	 * @return PalavraChave pc - retorna os dados de palavras chaves
	 * @author raiane
	 */
	
	public PalavraChave consultar(Integer id) {
		abrirConexao();
		String selectSql = "SELECT * FROM palavras_chave WHERE codigo = ?";
		String selectSqlPP = "SELECT * FROM postagem_palavras WHERE codPalavra = ?";

		PalavraChave pc = null;

		try {
			// recupera palavras-chave
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			// recupera postagens com a palavra-chave pesquisada
			PreparedStatement stmtPP = getConnection().prepareStatement(
					selectSqlPP);
			stmtPP.setInt(1, id);
			ResultSet rsPP = stmtPP.executeQuery();

			while (rs.next()) {
				pc = new PalavraChave();

				pc.setCodigo(id);
				pc.setNome(rs.getString(2));

				// adiciona as postagens no objeto pc
				// while (rsPP.next())
				// pc.adicionaPostagem(new JDBCDAOPostagem().consultar(rsPP
				// .getInt(1)));
			}

			stmt.close();
			stmtPP.close();
			rs.close();
			rsPP.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return pc;
	}

	@Override
	
	/**
	 * Método que altera palavras chaves
	 * @param objeto PalavraChave - parâmetro passado ao método alterar
	 * @author raiane
	 */
	
	
	public void alterar(PalavraChave objeto) {
		abrirConexao();
		String updateSql = "UPDATE palavras_chave SET nome=? WHERE codigo = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(updateSql);

			stmt.setString(1, objeto.getNome());
			stmt.setInt(2, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	
	/**
	 * Método que exclui palavras chaves de um blog
	 * @param objeto PalavraChave - parâmetro passado ao método deletar
	 * @author raiane
	 */
	
	public void deletar(PalavraChave objeto) {
		abrirConexao();
		String deleteSql = "DELETE FROM palavras_chave WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(deleteSql);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	
	/**
	 * Método para criação de uma lista de palavras chaves de um blog
	 * @return List<PalavraChave> lpc - retorna uma lista de palavras chaves de um blog
	 * @author raiane
	 */
	
	public List<PalavraChave> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM palavras_chave";
		List<PalavraChave> lpc = null;
		PalavraChave pc = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);
			ResultSet rs = stmt.executeQuery();

			lpc = new ArrayList<PalavraChave>();

			while (rs.next()) {
				pc = new PalavraChave();

				pc.setCodigo(rs.getInt(1));
				pc.setNome(rs.getString(2));

				lpc.add(pc);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return lpc;
	}
}