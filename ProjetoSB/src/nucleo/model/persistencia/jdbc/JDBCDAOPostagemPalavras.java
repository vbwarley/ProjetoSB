package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PostagemPalavra;
import nucleo.model.persistencia.dao.DAOPostagemPalavra;

/**
 * Classe para objetos do tipo postagem e palavra-chave.
 * @author nathalia
 *
 */
public class JDBCDAOPostagemPalavras extends JDBCDAO implements
		DAOPostagemPalavra {

	/**
	 * Construtor da classe.
	 */
	public JDBCDAOPostagemPalavras() {

	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(PostagemPalavra objeto) {
		abrirConexao();
		String insetSql = "INSERT INTO postagem_palavras VALUES (?,?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(insetSql);
			stmt.setInt(1, objeto.getPostagem().getCodigo());
			stmt.setInt(2, objeto.getPalavraChave().getCodigo());

			stmt.execute();

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public PostagemPalavra consultar(PostagemPalavra id) {
		abrirConexao();
		String selectSql = "SELECT * FROM postagem_palavras WHERE codPostagem=? AND codPalavra=?";
		PostagemPalavra pp = null;

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSql);
			stmt.setInt(1, id.getPostagem().getCodigo());
			stmt.setInt(2, id.getPalavraChave().getCodigo());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pp = new PostagemPalavra();
				pp.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(1)));
				pp.setPalavraChave(new JDBCDAOPalavraChave().consultar(rs
						.getInt(2)));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		return pp;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(PostagemPalavra objeto) {
		throw new RuntimeException();
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(PostagemPalavra objeto) {
		abrirConexao();
		String updateSql = "DELETE FROM postagem_palavras WHERE codPostagem=? AND codPalavra=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(updateSql);
			stmt.setInt(1, objeto.getPostagem().getCodigo());
			stmt.setInt(2, objeto.getPalavraChave().getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<PostagemPalavra> getList() {
		abrirConexao();
		String selectList = "SELECT * FROM postagem_palavras";
		List<PostagemPalavra> listaPP = null;
		PostagemPalavra pp = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(
					selectList);

			ResultSet rs = stmt.executeQuery();
			listaPP = new ArrayList<PostagemPalavra>();

			while (rs.next()) {
				pp = new PostagemPalavra();
				pp.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(1)));
				pp.setPalavraChave(new JDBCDAOPalavraChave().consultar(rs
						.getInt(2)));

				listaPP.add(pp);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		return listaPP;
	}
}
