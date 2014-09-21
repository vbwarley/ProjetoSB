package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.persistencia.dao.DAOComentario;

/**
 * Classe para criacao de Comentários
 * @author Douglas
 */
public class JDBCDAOComentario extends JDBCDAO implements
		DAOComentario<ComentarioComposite, Integer> {

	/**
	 * Método construtor da classe JDBCDAOComentario
	 */
	public JDBCDAOComentario() {

	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(ComentarioComposite objeto) {
		abrirConexao();
		String sql = "INSERT INTO comentario (titulo,conteudo,tipo,comentarioPai,codPostagem,login) VALUES (?,?,?,?,?,?)";

		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setString(3, objeto.getTipo());
			if (objeto.getComentarioPai() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, objeto.getComentarioPai().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());
			stmt.setString(6, objeto.getUsuario().getLogin());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override	
	public ComentarioComposite consultar(Integer id) {
		abrirConexao();

		String sql = "SELECT * FROM comentario WHERE codigo = ?";

		List<ComentarioComposite> listC = null; // lista de comentarios
		ComentarioComposite comentario = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtCP = null;
		ResultSet rsCP = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			// sql para recuperar a lista de comentarios, se existir
			stmtCP = getConnection().prepareStatement(
					"SELECT codigo FROM comentario WHERE comentarioPai = ?");

			stmtCP.setInt(1, id);
			stmtCP.execute();
			rsCP = stmtCP.getResultSet();

			if (rs.next()) {
				if (rs.getString("tipo").equals(
						ComentarioNormal.class.getSimpleName()))
					comentario = new ComentarioNormal();
				else
					comentario = new ComentarioAnonimo();

				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setTipo(rs.getString("tipo"));

				// se houver registros, esta será a lista de comentarios
				listC = new ArrayList<ComentarioComposite>();

				while (rsCP.next())
					listC.add(new JDBCDAOComentario().consultar(rsCP.getInt("codigo")));

				comentario.setListaComentarios(listC);
				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs
						.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs
						.getString("login")));

			}

			stmt.close();
			rs.close();
			stmtCP.close();
			rsCP.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fecharConexao();

		}

		return comentario;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(ComentarioComposite objeto) {
		abrirConexao();

		String sql = "UPDATE comentario SET titulo=?,conteudo=?,tipo=?,comentarioPai=?,codPostagem=?,login=? WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setString(3, objeto.getTipo());
			if (objeto.getComentarioPai() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, objeto.getComentarioPai().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());
			stmt.setString(6, objeto.getUsuario().getLogin());
			stmt.setInt(7, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<ComentarioComposite> getList() {
		
		abrirConexao();

		String sql = "SELECT * FROM comentario";

		List<ComentarioComposite> listC = null; 
		ComentarioComposite comentario = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtCP = null;
		ResultSet rsCP = null;

		try {
				
			stmt = getConnection().prepareStatement(sql);
			rs = stmt.executeQuery();

			stmtCP = getConnection().prepareStatement(
					"SELECT codigo FROM comentario WHERE comentarioPai = ?");
			
			
			listC = new ArrayList<ComentarioComposite>();

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

				while (rsCP.next())
					comentario.addComentario(new JDBCDAOComentario().consultar(rsCP.getInt("codigo")));

				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs
						.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs
						.getString("login")));


				listC.add(comentario);
			}
			stmt.close();
			rs.close();
			stmtCP.close();
			rsCP.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fecharConexao();

		}
		return listC;
	}
}
