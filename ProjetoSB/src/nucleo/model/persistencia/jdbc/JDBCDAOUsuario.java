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
			stmt.setString(4, objeto.getEmail());
			stmt.setDate(5, objeto.getDataNascimento());
			stmt.setString(6, objeto.getEndereco());
			stmt.setString(7, objeto.getInteresses());
			stmt.setString(8, objeto.getQuemSouEu());
			stmt.setString(9, objeto.getFilmes());
			stmt.setString(10, objeto.getLivro());
			stmt.setString(11, objeto.getMusicas());

			stmt.execute();

			for (Blog blog : objeto.getAssinatura()) {
				stmtBlog.setString(1, objeto.getLogin());
				stmtBlog.setInt(2, blog.getCodigo());

				stmtBlog.execute();
			}

			stmt.close();
			stmtBlog.close();

		} catch (SQLException e) {
			throw new RuntimeException();
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
				u.setEmail(rs.getString(4));
				u.setDataNascimento(rs.getDate(5));
				u.setEndereco(rs.getString(6));
				u.setInteresses(rs.getString(7));
				u.setQuemSouEu(rs.getString(8));
				u.setFilmes(rs.getString(9));
				u.setLivro(rs.getString(10));
				u.setMusicas(rs.getString(11));

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
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
		return u;
	}

	@Override
	public void alterar(Usuario objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE usuario SET senha=?,nome=?,email=?,data_nascimento=?"
				+ "endereco=?,interesses=?,quem_sou_eu=?,filmes=?,livros=?,musicas=?"
				+ "WHERE login=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getSenha());
			stmt.setString(2, objeto.getNome());
			stmt.setString(3, objeto.getEmail());
			stmt.setDate(4, objeto.getDataNascimento());
			stmt.setString(5, objeto.getEndereco());
			stmt.setString(6, objeto.getInteresses());
			stmt.setString(7, objeto.getQuemSouEu());
			stmt.setString(8, objeto.getFilmes());
			stmt.setString(9, objeto.getLivro());
			stmt.setString(10, objeto.getMusicas());
			stmt.setString(11, objeto.getLogin());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
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
		List<Usuario> lu = null;
		Usuario u = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery(sqlList);

			while (rs.next()) {
				u = new Usuario();
				lu = new ArrayList<Usuario>();

				u.setLogin(rs.getString(1));
				u.setSenha(rs.getString(2));
				u.setNome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setDataNascimento(rs.getDate(5));
				u.setEndereco(rs.getString(6));
				u.setInteresses(rs.getString(7));
				u.setQuemSouEu(rs.getString(8));
				u.setFilmes(rs.getString(9));
				u.setLivro(rs.getString(10));
				u.setMusicas(rs.getString(11));

				lu.add(u);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return lu;
	}

}
