package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.persistencia.dao.DAOComentario;

public class JDBCDAOComentario extends JDBCDAO implements
		DAOComentario<ComentarioComposite, Integer> {

	public JDBCDAOComentario() {

	}

	@Override
	public void criar(ComentarioComposite objeto) {
		abrirConexao();
		String sql = "INSERT INTO comentario (titulo,conteudo,tipo,comentarioPai,codPostagem,login) VALUES (?,?,?,?,?,?)";

		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setString(3, objeto.getTipo());
			stmt.setInt(4, objeto.getComentarioPai().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());
			stmt.setString(6, objeto.getUsuario().getLogin());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt("codigo"));

			for (ComentarioComposite comentario : objeto.getListaComentarios())
				criar(comentario);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public ComentarioComposite consultar(Integer id) {
		abrirConexao();

		String sql = "SELECT * FROM comentario WHERE codigo = ?";

		ComentarioComposite comentario = null;
		// codigo, titulo, conteudo, login, comentarioPai, Postagem

		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			// sql para recuperar a lista de comentarios, se existir
			PreparedStatement stmtCP = getConnection().prepareStatement(
					"SELECT * FROM comentario WHERE comentarioPai = ?");
			ResultSet rsCP = null;
			List<ComentarioComposite> listC = null;

			while (rs.next()) {

				if (rs.getString("tipo").equals(
						ComentarioNormal.class.getSimpleName()))
					comentario = new ComentarioNormal();
				else
					comentario = new ComentarioAnonimo();

				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setTipo(rs.getString("tipo"));

				stmtCP.setInt(1, id);
				stmtCP.execute();
				rsCP = stmtCP.getResultSet();

				// se houver registros, esta será a lista de comentarios
				listC = new ArrayList<ComentarioComposite>();

				while (rsCP.next())
					listC.add(consultar(rsCP.getInt("codigo")));

				if (rs.getInt("comentarioPai") == comentario.getCodigo())
					comentario.setComentarioPai(comentario);
				else
					comentario.setComentarioPai(consultar(rs
							.getInt("comentarioPai")));

				comentario.setListaComentarios(listC);
				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs
						.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs
						.getString("login")));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return comentario;
	}

	@Override
	public void alterar(ComentarioComposite objeto) {

		abrirConexao();

		String sql = "UPDATE comentario SET titulo=?,conteudo=?,tipo=?,comentarioPai=?,codPostagem=?,login=? WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setInt(3, objeto.getCodigo());
			stmt.setInt(4, objeto.getComentarioPai().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());
			stmt.setString(6, objeto.getUsuario().getLogin());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(ComentarioComposite objeto) {

		abrirConexao();

		String sql = "DELETE FROM comentario WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public List<ComentarioComposite> getList() {

		abrirConexao();

		List<ComentarioComposite> lista = new ArrayList<ComentarioComposite>();

		String sql = "SELECT * FROM comentario";
		ComentarioComposite comentario = null;

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// sql para recuperar a lista de comentarios, se existir
			PreparedStatement stmtCP = getConnection().prepareStatement(
					"SELECT * FROM comentario WHERE comentarioPai = ?");
			ResultSet rsCP = null;
			List<ComentarioComposite> listC = null;

			while (rs.next()) {

				if (rs.getString("tipo").equals(
						ComentarioNormal.class.getSimpleName()))
					comentario = new ComentarioNormal();
				else
					comentario = new ComentarioAnonimo();

				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setTipo(rs.getString("tipo"));

				stmtCP.setInt(1, comentario.getCodigo());
				stmtCP.execute();
				rsCP = stmtCP.getResultSet();

				// se houver registros, esta será a lista de comentarios
				listC = new ArrayList<ComentarioComposite>();

				while (rsCP.next())
					listC.add(consultar(rsCP.getInt("codigo")));

				if (rs.getInt("comentarioPai") == comentario.getCodigo())
					comentario.setComentarioPai(comentario);
				else
					comentario.setComentarioPai(consultar(rs
							.getInt("comentarioPai")));

				comentario.setListaComentarios(listC);
				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs
						.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs
						.getString("login")));

				lista.add(comentario);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return lista;
	}

	// public List<ComentarioComposite> getListaComentarios(Integer codigo) {
	//
	// String sql = "SELECT * FROM comentario WHERE comentarioPai = " + codigo;
	//
	// List<ComentarioComposite> lista = new ArrayList<ComentarioComposite>();
	//
	// // codigo, titulo, conteudo, login, comentarioPai, Postagem
	// try {
	//
	// Statement stmt = getConnection().createStatement();
	// ResultSet rs = stmt.executeQuery(sql);
	//
	// while (rs.next()) {
	// ComentarioComposite comentario = new ComentarioComposite();
	//
	// comentario.setCodigo(rs.getInt("codigo"));
	// comentario.setTitulo(rs.getString("titulo"));
	// comentario.setConteudo(rs.getString("conteudo"));
	// comentario.setUsuario(new JDBCDAOUsuario().consultar(rs
	// .getString("login")));
	// comentario.setComentarioPai(new JDBCDAOComentario()
	// .consultar(codigo));
	// comentario.setPostagem(new JDBCDAOPostagem().consultar(rs
	// .getInt("postagem")));
	//
	// lista.add(comentario);
	//
	// }
	//
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// } finally {
	// fecharConexao();
	// }
	//
	// return lista;
	// }

}
