package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Midia;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.persistencia.dao.DAOMidia;

public class JDBCDAOMidia extends JDBCDAO implements DAOMidia<Midia, Integer> {

	public JDBCDAOMidia() {
		abrirConexao();
	}

	@Override
	public void criar(Midia objeto) {
		String sqlCriar = "INSERT INTO midia VALUES (?,?,?,?,?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlCriar);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getNomeArquivo());
			stmt.setInt(3, objeto.getTipo().getId());
			stmt.setInt(4, objeto.getComentario().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Midia consultar(Integer id) {
		String selectSQL = "SELECT * FROM midia WHERE codigo = ?";

		Midia m = null;

		try {
			// recuperando dados da midia
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				m = new Midia();

				m.setCodigo(id);
				m.setNomeArquivo(rs.getString(2));
				m.setTipo(TipoMidia.porId(rs.getInt(3)));
				m.setComentario(new JDBCDAOComentario().consultar(rs.getInt(4)));
				m.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(5)));

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return m;
	}

	@Override
	public void alterar(Midia objeto) {
		String sqlUpdate = "UPDATE midia SET nome=?,codTipo=?,codComentario=?,codPostagem=? WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getNomeArquivo());
			stmt.setInt(2, objeto.getTipo().getId());
			stmt.setInt(3, objeto.getComentario().getCodigo());
			stmt.setInt(4, objeto.getPostagem().getCodigo());
			stmt.setInt(5, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(Midia objeto) {
		String sqlDelete = "DELETE FROM midia WHERE codigo = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlDelete);

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
	public List<Midia> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM usuario";
		List<Midia> lm = null;
		Midia m = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				m = new Midia();
				lm = new ArrayList<Midia>(); // pode ser HashSet

				m.setCodigo(rs.getInt(1));
				m.setNomeArquivo(rs.getString(2));
				m.setTipo(TipoMidia.porId(rs.getInt(3)));
				m.setComentario(new JDBCDAOComentario().consultar(rs.getInt(4)));
				m.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(5)));

				lm.add(m);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return lm;
	}
}
