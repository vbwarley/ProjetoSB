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
 * 
 * @author Warley Vital
 */
public class JDBCDAOPalavraChave extends JDBCDAO implements DAOPalavraChave {

	/**
	 * Método construtor da classe JDBCDAOPalavraChave
	 */
	public JDBCDAOPalavraChave() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(PalavraChave objeto) {
		abrirConexao();
		String insertSql = "INSERT INTO palavras_chave (nome) VALUES (?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, objeto.getNome());
			stmt.execute();

			rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public PalavraChave consultar(Integer id) {
		abrirConexao();
		String selectSql = "SELECT * FROM palavras_chave WHERE codigo = ?";
		String selectSqlPP = "SELECT * FROM postagem_palavras WHERE codPalavra = ?";

		PalavraChave pc = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtPP = null;
		ResultSet rsPP = null;

		try {
			// recupera palavras-chave
			stmt = getConnection().prepareStatement(selectSql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			// recupera postagens com a palavra-chave pesquisada
			stmtPP = getConnection().prepareStatement(selectSqlPP);
			stmtPP.setInt(1, id);
			rsPP = stmtPP.executeQuery();

			while (rs.next()) {
				pc = new PalavraChave();

				pc.setCodigo(id);
				pc.setNome(rs.getString(2));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
			fecharConexao(stmtPP, rsPP);
		}

		return pc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(PalavraChave objeto) {
		abrirConexao();
		String updateSql = "UPDATE palavras_chave SET nome=? WHERE codigo = ?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(updateSql);

			stmt.setString(1, objeto.getNome());
			stmt.setInt(2, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(PalavraChave objeto) {
		abrirConexao();
		String deleteSql = "DELETE FROM palavras_chave WHERE codigo=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(deleteSql);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao(stmt, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<PalavraChave> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM palavras_chave";
		List<PalavraChave> lpc = null;
		PalavraChave pc = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sqlList);
			rs = stmt.executeQuery();

			lpc = new ArrayList<PalavraChave>();

			while (rs.next()) {
				pc = new PalavraChave();

				pc.setCodigo(rs.getInt(1));
				pc.setNome(rs.getString(2));

				lpc.add(pc);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}

		return lpc;
	}
}