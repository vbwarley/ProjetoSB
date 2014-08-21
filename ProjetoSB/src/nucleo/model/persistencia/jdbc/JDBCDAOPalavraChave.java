package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PalavraChave;
import nucleo.model.persistencia.dao.DAOPalavraChave;

public class JDBCDAOPalavraChave extends JDBCDAO implements
		DAOPalavraChave<PalavraChave, Integer> {

	public JDBCDAOPalavraChave() {
		abrirConexao();
	}

	@Override
	public void criar(PalavraChave objeto) {
		String insertSql = "INSERT INTO palavras_chave VALUES (?,?)";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(insertSql);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getNome());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public PalavraChave consultar(Integer id) {
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
			stmt.setInt(1, id);
			ResultSet rsPP = stmtPP.executeQuery();

			while (rs.next()) {
				pc = new PalavraChave();

				pc.setCodigo(id);
				pc.setNome(rs.getString(2));

				// adiciona as postagens no objeto pc
				while (rsPP.next())
					pc.adicionaPostagem(new JDBCDAOPostagem().consultar(rsPP
							.getInt(1)));
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
	public void alterar(PalavraChave objeto) {
		String updateSql = "UPDATE palavras_chave SET nome=? WHERE codigo = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(updateSql);

			stmt.setString(1, objeto.getNome());
			stmt.setInt(2, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(PalavraChave objeto) {
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
	public List<PalavraChave> getList() {
		String sqlList = "SELECT * FROM palavras_chave";
		List<PalavraChave> lpc = null;
		PalavraChave pc = null;

		// recupera as postagens associadas
		String sqlListPP = "SELECT * FROM postagem_palavras WHERE codPalavra = ?";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);
			PreparedStatement stmtPP = getConnection().prepareStatement(
					sqlListPP);

			ResultSet rs = stmt.executeQuery();
			ResultSet rsPP = stmtPP.executeQuery();

			while (rs.next()) {
				pc = new PalavraChave();
				lpc = new ArrayList<PalavraChave>();

				pc.setCodigo(rs.getInt(1));
				pc.setNome(rs.getString(2));

				// adiciona as postagens (se tiver) à palavra-chave recuperada
				while (rsPP.next())
					pc.adicionaPostagem(new JDBCDAOPostagem().consultar(rsPP
							.getInt(1)));

				lpc.add(pc);
			}

			stmt.close();
			stmtPP.close();
			rs.close();
			rsPP.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return lpc;
	}
}