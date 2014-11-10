package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.dao.DAOPostagem;

/**
 * Classe para objetos do tipo postagem, onde serão contidos, valores e métodos
 * para o mesmo.
 * 
 * @author nathalia
 */
public class JDBCDAOPostagem extends JDBCDAO implements DAOPostagem {

	/**
	 * Método construtor da classe JDBCDAOPostagem
	 */
	public JDBCDAOPostagem() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Postagem objeto) {
		abrirConexao();
		String sql = "INSERT INTO postagem (titulo,conteudo,codBlog,data_criacao) VALUES (?,?,?,?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setInt(3, objeto.getBlog().getCodigo());
			stmt.setDate(4, objeto.getData());

			stmt.execute();

			rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
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
	public Postagem consultar(Integer id) {
		abrirConexao();
		String PostagemSQL = "Select * from postagem where codigo = ?";

		Postagem p = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = getConnection().prepareStatement(PostagemSQL);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Postagem();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(new JDBCDAOBlog().consultar(rs.getInt(4)));
				p.setData(rs.getDate(5));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}

		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Postagem objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE postagem SET titulo=?,conteudo=?,codBlog=?,data_criacao=? WHERE codigo=?";

		PreparedStatement stmt = null;

		try {
			abrirConexao();

			stmt = getConnection().prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setInt(3, objeto.getBlog().getCodigo());
			stmt.setInt(4, objeto.getCodigo());
			stmt.setDate(5, objeto.getData());

			stmt.executeUpdate();
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
	public void deletar(Postagem objeto) {
		abrirConexao();

		String sqlDelete = "DELETE FROM postagem WHERE codigo = ?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sqlDelete);

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
	public List<Postagem> getList() {
		abrirConexao();

		String sqlList = "SELECT * FROM postagem";

		List<Postagem> po = null;
		Postagem p = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sqlList);
			rs = stmt.executeQuery(sqlList);

			po = new ArrayList<Postagem>();

			while (rs.next()) {
				p = new Postagem();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(new JDBCDAOBlog().consultar(rs.getInt(4)));
				p.setData(rs.getDate(5));

				po.add(p);

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao(stmt, rs);
		}

		return po;
	}

	@Override
	public List<Postagem> getPostagensPorBlog(Blog blog) {
		abrirConexao();

		String sql = "SELECT * FROM postagem WHERE codBlog = ?";

		List<Postagem> posts = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			posts = new ArrayList<Postagem>();

			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, blog.getCodigo());

			rs = stmt.executeQuery();

			while (rs.next()) {

				Postagem post = new Postagem();
				post.setCodigo(rs.getInt(1));
				post.setTitulo(rs.getString(2));
				post.setConteudo(rs.getString(3));
				post.setBlog(blog);
				post.setData(rs.getDate(5));

				posts.add(post);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}

		return posts;

	}

	@Override
	public Integer getMaxId() {
		abrirConexao();

		String sql = "SELECT MAX(codigo) FROM postagem";
		int id = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}

		return id;
	}
}
