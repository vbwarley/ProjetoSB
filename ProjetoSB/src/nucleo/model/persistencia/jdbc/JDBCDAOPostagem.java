package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.dao.DAOPostagem;

public class JDBCDAOPostagem extends JDBCDAO implements
		DAOPostagem<Postagem, Integer> {

	public JDBCDAOPostagem() {
		abrirConexao();
	}

	@Override
	public void criar(Postagem objeto) {
		String sql = "INSERT INTO postagem VALUES (?,?,?,?,?)";

		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			stmt.setInt(4, objeto.getBlog().getCodigo());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

	}

	@Override
	public Postagem consultar(Integer id) {
		String PostagemSQL = "Select * from postagem where codigo = ?";
		Postagem p = null;

		JDBCDAOBlog b = new JDBCDAOBlog();

		try {

			PreparedStatement stmt = getConnection().prepareStatement(
					PostagemSQL);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Postagem();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(b.consultar(rs.getInt(4)));

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return p;
	}

	@Override
	public void alterar(Postagem objeto) {
		String sqlUpdate = "UPDATE postagem SET codigo=?,titulo=?,conteudo=?,codBlog"
				+ "WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			stmt.setInt(4, objeto.getBlog().getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(Postagem objeto) {
		String sqlDelete = "DELETE FROM postagem WHERE codigo = ?";

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
	public List<Postagem> getList() {
		String sqlList = "SELECT * FROM postagem";
		List<Postagem> po = null;
		Postagem p = null;

		JDBCDAOBlog b = new JDBCDAOBlog();

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery(sqlList);

			while (rs.next()) {
				p = new Postagem();
				po = new ArrayList<Postagem>();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(b.consultar(rs.getInt(4)));

				po.add(p);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return po;
	}
}
