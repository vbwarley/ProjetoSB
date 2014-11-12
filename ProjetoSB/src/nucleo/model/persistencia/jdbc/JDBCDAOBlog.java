package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOBlog;

/**
 * Classe para criacao de Blogs
 * 
 * @author Raiane
 */
public class JDBCDAOBlog extends JDBCDAO implements DAOBlog {

	
	/**
	 * Método construtor da classe JDBCDAOBlog
	 */
	public JDBCDAOBlog() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Blog objeto) {
		String sql = "INSERT INTO blog (titulo,descricao,imagemFundo,autorizacaoNormal,autorizacaoAnonimo,login) VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getDescricao());
			stmt.setString(3, objeto.getImagemFundo());
			stmt.setBoolean(4, objeto.isAutorizaComentario());
			stmt.setBoolean(5, objeto.isAutorizaComentarioAnonimo());
			stmt.setString(6, objeto.getUsuario().getLogin());

			stmt.execute();

			rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public Blog consultar(Integer id) {

		String selectSQL = "SELECT * FROM blog WHERE codigo = ?";
		Blog b = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// recuperando dados do blog
			stmt = getConnection().prepareStatement(selectSQL);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				b = new Blog();
								
				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));
				b.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Blog objeto) {
		
		String sqlUpdate = "UPDATE blog SET titulo=?,descricao=?,imagemFundo=?,autorizacaoNormal=?,autorizacaoAnonimo=? WHERE codigo=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getDescricao());
			stmt.setString(3, objeto.getImagemFundo());
			stmt.setBoolean(4, objeto.isAutorizaComentario());
			stmt.setBoolean(5, objeto.isAutorizaComentarioAnonimo());
			stmt.setInt(6, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Blog objeto) {

		String sqlDelete = "DELETE FROM blog WHERE codigo = ?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sqlDelete);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public List<Blog> consultarPorNome(String titulo, String order, int limit) {

		String sql = "SELECT * FROM blog WHERE Ucase(titulo) LIKE Ucase('%" + titulo + "%') ORDER BY titulo " + order
				+ " LIMIT ?";

		ArrayList<Blog> resultado = new ArrayList<Blog>();
		Blog b = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, limit);

			rs = stmt.executeQuery();

			while (rs.next()) {

				b = new Blog();
				
				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));
				b.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

				resultado.add(b);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 

		return resultado;
	}

	public List<Blog> consultarPorDescricao(String descricao, String order, int limit) {

		String sql = "SELECT * FROM blog WHERE Ucase(descricao) LIKE Ucase('%" + descricao + "%') ORDER BY descricao "
				+ order + " LIMIT ?";

		ArrayList<Blog> resultado = new ArrayList<Blog>();
		Blog b = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, limit);

			rs = stmt.executeQuery();

			while (rs.next()) {

				b = new Blog();
				
				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));
				b.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

				resultado.add(b);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Blog> getList() {

		String sqlList = "SELECT * FROM blog";

		List<Blog> bu = null;
		Blog b = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sqlList);
			rs = stmt.executeQuery(sqlList);
			bu = new ArrayList<Blog>();

			while (rs.next()) {
				b = new Blog();
				
				b.setCodigo(rs.getInt(1));
				b.setTitulo(rs.getString(2));
				b.setDescricao(rs.getString(3));
				b.setImagemFundo(rs.getString(4));
				b.setAutorizaComentario(rs.getBoolean(5));
				b.setAutorizaComentarioAnonimo(rs.getBoolean(6));
				b.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

				bu.add(b);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return bu;
	}

	public Integer getMaxId() {

		String sql = "SELECT MAX(codigo) FROM blog";
		int id = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return id;
	}

	@Override
	public Set<Blog> getBlogsPorUsuario(Usuario usuario) {

		String sql = "SELECT * FROM blog WHERE login=?";

		Set<Blog> blogs = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			blogs = new HashSet<Blog>();

			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Blog blog = new Blog();

				blog.setCodigo(rs.getInt(1));
				blog.setTitulo(rs.getString(2));
				blog.setDescricao(rs.getString(3));
				blog.setImagemFundo(rs.getString(4));
				blog.setAutorizaComentario(rs.getBoolean(5));
				blog.setAutorizaComentarioAnonimo(rs.getBoolean(6));
				blog.setUsuario(usuario);

				blogs.add(blog);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blogs;

	}

	@Override
	public void removerAssinante(Blog blog, Usuario usuario) {

		String sql = "DELETE FROM assinatura WHERE codBlog = ? AND login = ?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, blog.getCodigo());
			stmt.setString(2, usuario.getLogin());

			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} 
	}

}
