package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;

/**
 * Esta classe provê os métodos necessários à manuntenção de um usuário da aplicação.
 * @author Warley Vital
 */
public class JDBCDAOUsuario extends JDBCDAO implements
		DAOUsuario<Usuario, String> {

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
		abrirConexao();
		String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

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
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public Usuario consultar(String id) {
		abrirConexao();
		String selectSQL = "SELECT * FROM usuario WHERE login = ?";

		Usuario u = null;

		try {
			// recuperando dados do usuario
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

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
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
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
		abrirConexao();
		String sqlUpdate = "UPDATE usuario SET senha=?,nome=?,email=?,sexo=?,data_nascimento=?,endereco=?,interesses=?,quem_sou=?,filmes=?,livros=?,musicas=? WHERE login=?";

		try {
			abrirConexao();
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getLogin());

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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Usuario> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM usuario";

		List<Usuario> lu = null;
		Usuario u = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);
			ResultSet rs = stmt.executeQuery(sqlList);

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

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return lu;
	}
}
