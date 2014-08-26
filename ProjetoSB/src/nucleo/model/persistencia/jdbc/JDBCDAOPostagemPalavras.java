package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PostagemPalavra;
import nucleo.model.persistencia.dao.DAOPostagemPalavra;

public class JDBCDAOPostagemPalavras extends JDBCDAO implements
		DAOPostagemPalavra {

	public JDBCDAOPostagemPalavras() {

	}

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

	@Override
	public void alterar(PostagemPalavra objeto) {
		throw new RuntimeException();
	}

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
