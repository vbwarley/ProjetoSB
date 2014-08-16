package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.persistencia.dao.DAOBlog;

public class JDBCDAOBlog extends JDBCDAO implements DAOBlog<Blog, Integer> {

	public JDBCDAOBlog() {
		abrirConexao();
	}

	@Override
	public void criar(Blog objeto) {
		String sql = "INSERT INTO blog VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getDescricao());
			stmt.setString(4, objeto.getImagemFundo());
			stmt.setBoolean(5, objeto.isAutorizaComentario());
			stmt.setBoolean(6, objeto.isAutorizaComentarioAnonimo());

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Blog consultar(Integer id) {
		String selectSQL = "SELECT * FROM blog WHERE codigo = ?";
		Blog b = null;

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				b = new Blog();

				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return b;
	}

	@Override
	public void alterar(Blog objeto) {

		String sqlUpdate = "UPDATE blog SET titulo=?,descricao=?,imagemFundo=?,autorizaComentario=?,autorizaComentarioAnonimo=?"
				+ "WHERE titulo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getDescricao());
			stmt.setString(4, objeto.getImagemFundo());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void deletar(Blog objeto) {

		String sqlDelete = "DELETE FROM blog WHERE codigo = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlDelete);

			stmt.setString(1, objeto.getTitulo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public List<Blog> getList() {

		String sqlList = "SELECT * FROM blog";
		List<Blog> bu = null;
		Blog b = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery(sqlList);

			while (rs.next()) {
				b = new Blog();
				bu = new ArrayList<Blog>();

				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));

				bu.add(b);
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return bu;
	}

}
