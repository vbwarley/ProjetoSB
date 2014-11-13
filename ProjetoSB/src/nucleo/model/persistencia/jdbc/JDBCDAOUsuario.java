package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;

/**
 * Esta classe provê os métodos necessários à manuntenção de um usuário da
 * aplicação.
 * 
 * @author Warley Vital
 */
public class JDBCDAOUsuario extends JDBCDAO implements DAOUsuario {

	/**
	 * Construtor da classe.
	 */
	public JDBCDAOUsuario() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Usuario objeto) {

		String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlEmail = "SELECT * FROM usuario WHERE email = ?";

		PreparedStatement stmt = null;
		PreparedStatement stmtEmail = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmtEmail = getConnection().prepareStatement(sqlEmail);
			stmtEmail.setString(1, objeto.getEmail());

			rs = stmtEmail.executeQuery();

			if (objeto.getNome() == null)
				stmt.setString(3, objeto.getLogin());
			else
				stmt.setString(3, objeto.getNome());

			stmt.setString(1, objeto.getLogin());
			stmt.setString(2, objeto.getSenha());
			stmt.setString(4, String.valueOf(objeto.getSexo()));
			stmt.setDate(5, objeto.getDataNascimento());
			stmt.setString(6, objeto.getEmail());
			stmt.setString(7, objeto.getQuemSouEu());
			stmt.setString(8, objeto.getInteresses());
			stmt.setString(9, objeto.getEndereco());
			stmt.setString(10, objeto.getFilmes());
			stmt.setString(11, objeto.getLivros());
			stmt.setString(12, objeto.getMusicas());

			if (!rs.next())
				stmt.execute();

			stmt.close();
			stmtEmail.close();
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
	public Usuario consultar(String id) {

		String selectSQL = "SELECT * FROM usuario WHERE login = ?";

		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// recuperando dados do usuario
			stmt = getConnection().prepareStatement(selectSQL);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString(1));
				u.setSenha(rs.getString(2));
				u.setNome(rs.getString(3));
				u.setSexo(rs.getString(4));
				u.setDataNascimento(rs.getDate(5));
				u.setEmail(rs.getString(6));
				u.setQuemSouEu(rs.getString(7));
				u.setInteresses(rs.getString(8));
				u.setEndereco(rs.getString(9));
				u.setFilmes(rs.getString(10));
				u.setLivros(rs.getString(11));
				u.setMusicas(rs.getString(12));

				u.getBlogsPossuidos().addAll(new JDBCDAOBlog().getBlogsPorUsuario(u));
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Usuario objeto) {

		String sqlUpdate = "UPDATE usuario SET senha=?,nome=?,email=?,sexo=?,data_nascimento=?,endereco=?,interesses=?,quem_sou=?,filmes=?,livros=?,musicas=? WHERE login=?";

		PreparedStatement stmt = null;

		try {

			stmt = getConnection().prepareStatement(sqlUpdate);

			if (objeto.getNome() == null)
				stmt.setString(2, objeto.getLogin());
			else
				stmt.setString(2, objeto.getNome());
			stmt.setString(1, objeto.getSenha());
			stmt.setString(3, objeto.getEmail());
			stmt.setString(4, String.valueOf(objeto.getSexo()));
			stmt.setDate(5, objeto.getDataNascimento());
			stmt.setString(6, objeto.getEndereco());
			stmt.setString(7, objeto.getInteresses());
			stmt.setString(8, objeto.getQuemSouEu());
			stmt.setString(9, objeto.getFilmes());
			stmt.setString(10, objeto.getLivros());
			stmt.setString(11, objeto.getMusicas());
			stmt.setString(12, objeto.getLogin());

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
	public void deletar(Usuario objeto) {

		String sqlDelete = "DELETE FROM usuario WHERE login = ?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sqlDelete);

			stmt.setString(1, objeto.getLogin());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Usuario> getList() {

		String sqlList = "SELECT * FROM usuario";

		List<Usuario> lu = null;
		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sqlList);
			rs = stmt.executeQuery(sqlList);

			lu = new ArrayList<Usuario>();

			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo"));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				lu.add(u);
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return lu;
	}

	@Override
	public List<Usuario> consultarPorNome(String nome, String order, int limit) {

		String sql = "SELECT * FROM usuario WHERE Ucase(nome) LIKE Ucase('%" + nome + "%') ORDER BY nome " + order
				+ ",email " + order + " LIMIT ?";

		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, limit);

			rs = stmt.executeQuery();

			while (rs.next()) {

				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo"));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				u.getBlogsPossuidos().addAll(new JDBCDAOBlog().getBlogsPorUsuario(u));

				resultado.add(u);
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	@Override
	public List<Usuario> consultarPorLogin(String login, String order, int limit) {

		String sql = "SELECT * FROM usuario WHERE Ucase(login) LIKE Ucase('%" + login + "%') ORDER BY nome " + order
				+ ", email " + order + " LIMIT ?";

		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, limit);

			rs = stmt.executeQuery();

			while (rs.next()) {
				// if (rs.getString("nome") == null)
				// continue;

				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo"));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				u.getBlogsPossuidos().addAll(new JDBCDAOBlog().getBlogsPorUsuario(u));

				resultado.add(u);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	@Override
	public List<Usuario> consultarPorEmail(String email) {

		String sql = "SELECT * FROM usuario WHERE email = ?";

		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, email);

			rs = stmt.executeQuery();

			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo"));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				u.getBlogsPossuidos().addAll(new JDBCDAOBlog().getBlogsPorUsuario(u));

				resultado.add(u);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	@Override
	public List<Usuario> consultarPorIntervaloData(String from, String to, String order, int limit) {
	
		String sql = "SELECT * FROM usuario WHERE data_nascimento BETWEEN ? AND ? ORDER BY nome " + order
				+ ", email " + order + " LIMIT ?";
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		Usuario u = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, from);
			stmt.setString(2, to);
			stmt.setInt(3, limit);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo"));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				u.getBlogsPossuidos().addAll(new JDBCDAOBlog().getBlogsPorUsuario(u));

				resultado.add(u);
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	@Override
	public boolean validacaoLogin(String login, String senha) {

		Usuario u = consultar(login);

		if (u == null || !u.getSenha().equals(senha))
			return false;

		return true;
	}

	@Override
	public List<Blog> getBlogsSeguidos(Usuario usuario) {

		String sql = "SELECT codBlog FROM assinatura WHERE login = ?";

		List<Blog> blogs = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			blogs = new ArrayList<Blog>();

			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());

			rs = stmt.executeQuery();

			while (rs.next()) {

				Blog blog = new JDBCDAOBlog().consultar(rs.getInt(1));
				blogs.add(blog);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogs;
	}
}
