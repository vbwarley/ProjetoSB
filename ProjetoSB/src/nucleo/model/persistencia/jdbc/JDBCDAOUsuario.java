package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;

public class JDBCDAOUsuario extends JDBCDAO implements
		DAOUsuario<Usuario, String> {

	public JDBCDAOUsuario() {

	}

	@Override
	public void criar(Usuario objeto) {
		abrirConexao();
		String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlA = "INSERT INTO assinatura VALUES (?,?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			PreparedStatement stmtBlog = getConnection().prepareStatement(sqlA);

			stmt.setString(1, objeto.getLogin());
			stmt.setString(2, objeto.getSenha());
			stmt.setString(3, objeto.getNome());
			stmt.setString(4, String.valueOf(objeto.getSexo()));
			stmt.setDate(5, objeto.getDataNascimento());
			stmt.setString(6, objeto.getEmail());
			stmt.setString(7, objeto.getQuemSouEu());
			stmt.setString(8, objeto.getInteresses());
			stmt.setString(9, objeto.getEndereco());
			stmt.setString(10, objeto.getFilmes());
			stmt.setString(11, objeto.getLivros());
			stmt.setString(12, objeto.getMusicas());

			stmt.execute();

			for (Blog blog : objeto.getAssinatura()) {
				stmtBlog.setString(1, objeto.getLogin());
				stmtBlog.setInt(2, blog.getCodigo());

				stmtBlog.execute();
			}

			stmt.close();
			stmtBlog.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Usuario consultar(String id) {
		abrirConexao();
		String selectSQL = "SELECT * FROM usuario WHERE login = ?";
		String selectSQLA = "SELECT * FROM assinatura WHERE login=?";

		Usuario u = null;

		try {
			// recuperando dados do usuario
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			// recuperando dados da assinatura do usuario
			PreparedStatement stmtA = getConnection().prepareStatement(
					selectSQLA);
			stmtA.setString(1, id);
			ResultSet rsA = stmtA.executeQuery();

			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString(1));
				u.setSenha(rs.getString(2));
				u.setNome(rs.getString(3));
				u.setSexo(rs.getString(4).toCharArray()[0]);
				u.setDataNascimento(rs.getDate(5));
				u.setEmail(rs.getString(6));
				u.setQuemSouEu(rs.getString(7));
				u.setInteresses(rs.getString(8));
				u.setEndereco(rs.getString(9));
				u.setFilmes(rs.getString(10));
				u.setLivros(rs.getString(11));
				u.setMusicas(rs.getString(12));

				// recupera as assinaturas
				while (rsA.next())
					u.getAssinatura().add(
							new JDBCDAOBlog().consultar(rsA.getInt(2)));
			}

			stmt.close();
			stmtA.close();
			rs.close();
			rsA.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		return u;
	}

	@Override
	public void alterar(Usuario objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE usuario SET senha=?,nome=?,email=?,sexo=?,data_nascimento=?,endereco=?,interesses=?,quem_sou=?,filmes=?,livros=?,musicas=? WHERE login=?";
		// exclui os blogs da assinatura do usuario
		String sqlA = "";

		Usuario usuario = consultar(objeto.getLogin());
		Blog blog = null;

		if (objeto.getAssinatura().size() > usuario.getAssinatura().size()) {
			for (Blog blogN : objeto.getAssinatura())
				if (!usuario.getAssinatura().contains(blogN)) {
					sqlA = "INSERT INTO assinatura VALUES (?,?)";
					blog = blogN;
				}
		} else if (objeto.getAssinatura().size() < usuario.getAssinatura()
				.size()) {
			if (!objeto.getAssinatura().isEmpty())
				sqlA = "DELETE FROM assinatura WHERE login=? AND codBlog NOT IN(?"
						+ new String(
								new char[(objeto.getAssinatura().size() - 1)])
								.replace("\0", ",?") + ")";
			else
				sqlA = "DELETE FROM assinatura WHERE login=?";
		}

		try {
			abrirConexao();
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);
			PreparedStatement stmtBlog = getConnection().prepareStatement(sqlA);

			stmt.setString(1, objeto.getLogin());

			if (sqlA.contains("INSERT")) {
				stmtBlog.setString(1, objeto.getLogin());
				stmtBlog.setInt(2, blog.getCodigo());
				stmtBlog.execute();
			} else if (sqlA.contains("DELETE")) {
				int c = 0;

				for (Blog b : objeto.getAssinatura()) {
					c++;
					stmtBlog.setInt(c, b.getCodigo());
				}

				stmtBlog.executeUpdate();
			}

			stmt.setString(1, objeto.getSenha());
			stmt.setString(2, objeto.getNome());
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
			stmtBlog.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(Usuario objeto) {
		abrirConexao();
		String sqlDelete = "DELETE FROM usuario WHERE login = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlDelete);

			stmt.setString(1, objeto.getLogin());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	@Override
	public List<Usuario> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM usuario";
		String sqlListA = "SELECT * FROM assinatura";

		List<Usuario> lu = null;
		Usuario u = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);
			ResultSet rs = stmt.executeQuery(sqlList);

			// recuperando dados da assinatura do usuario
			PreparedStatement stmtA = getConnection()
					.prepareStatement(sqlListA);
			ResultSet rsA = stmtA.executeQuery();

			lu = new ArrayList<Usuario>();

			while (rs.next()) {
				u = new Usuario();

				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSexo(rs.getString("sexo").charAt(0));
				u.setDataNascimento(rs.getDate("data_nascimento"));
				u.setEndereco(rs.getString("endereco"));
				u.setInteresses(rs.getString("interesses"));
				u.setQuemSouEu(rs.getString("quem_sou"));
				u.setFilmes(rs.getString("filmes"));
				u.setLivros(rs.getString("livros"));
				u.setMusicas(rs.getString("musicas"));

				lu.add(u);
			}

			// para cada usuario recuperado, verifica-se se ele é assinante de
			// algum blog
			// caso positivo, o blog é adicionado
			while (rsA.next())
				for (Usuario user : lu)
					if (rsA.getString(1).equals(user.getLogin()))
						user.getAssinatura().add(
								new JDBCDAOBlog().consultar(rsA.getInt(2)));

			stmt.close();
			stmtA.close();
			rs.close();
			rsA.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return lu;
	}
}
