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
 * 
 * @author Douglas
 */
public class JDBCDAOComentario extends JDBCDAO implements DAOComentario {

	/**
	 * Método construtor da classe JDBCDAOComentario
	 */
	public JDBCDAOComentario() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(ComentarioComposite objeto) {

		String sql = "INSERT INTO comentario (titulo,conteudo,tipo,comentarioPai,codPostagem,login) VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setString(3, objeto.getClass().getSimpleName());
			if (objeto.getComentarioPai() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, objeto.getComentarioPai().getCodigo());
			stmt.setInt(5, objeto.getPostagem().getCodigo());
			if (objeto.getUsuario() == null)
				stmt.setNull(6, Types.NULL);
			else
				stmt.setString(6, objeto.getUsuario().getLogin());

			stmt.execute();

			rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));

			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public ComentarioComposite consultar(Integer id) {

		String sql = "SELECT * FROM comentario WHERE codigo = ?";

		ComentarioComposite comentario = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("tipo").equals(ComentarioNormal.class.getSimpleName()))
					comentario = new ComentarioNormal();
				else
					comentario = new ComentarioAnonimo();

				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setTipo(rs.getString("tipo"));

				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

				comentario.setListaComentarios(consultaSubComentarios(comentario));

				if (rs.getInt("comentarioPai") != Types.NULL)
					comentario.setComentarioPai(consultar(rs.getInt("comentarioPai")));
				else
					comentario.setComentarioPai(null);

			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comentario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(ComentarioComposite objeto) {

		String sql = "UPDATE comentario SET titulo=?,conteudo=?,tipo=?,comentarioPai=?,codPostagem=?,login=? WHERE codigo=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setString(3, objeto.getClass().getSimpleName());
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(ComentarioComposite objeto) {

		String sql = "DELETE FROM comentario WHERE codigo=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(sql);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();

			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<ComentarioComposite> getList() {

		String sql = "SELECT * FROM comentario";

		List<ComentarioComposite> listC = null;
		ComentarioComposite comentario = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = getConnection().prepareStatement(sql);
			rs = stmt.executeQuery();

			listC = new ArrayList<ComentarioComposite>();

			while (rs.next()) {
				if (rs.getString("tipo").equals(ComentarioNormal.class.getSimpleName()))
					comentario = new ComentarioNormal();
				else
					comentario = new ComentarioAnonimo();

				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setTipo(rs.getString("tipo"));
				
				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt("codPostagem")));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));

				comentario.setListaComentarios(consultaSubComentarios(comentario));

				if (rs.getInt("comentarioPai") != Types.NULL)
					comentario.setComentarioPai(consultar(rs.getInt("comentarioPai")));
				else
					comentario.setComentarioPai(null);
				
				listC.add(comentario);
			}

			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return listC;
	}

	@Override
	public Integer getMaxId() {

		String sql = "SELECT MAX(codigo) FROM comentario";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next())
				return rs.getInt(1);

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<ComentarioComposite> consultaSubComentarios(ComentarioComposite comentarioPai) {

		String sql = "SELECT * FROM comentario WHERE comentarioPai = ?";

		ComentarioComposite cc = null;
		List<ComentarioComposite> listC = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, comentarioPai.getCodigo());

			rs = stmt.executeQuery();
			listC = new ArrayList<ComentarioComposite>();

			while (rs.next()) {

				if (rs.getString("tipo").equals(ComentarioNormal.class.getSimpleName()))
					cc = new ComentarioNormal();
				else
					cc = new ComentarioAnonimo();

				cc.setCodigo(rs.getInt("codigo"));
				cc.setTitulo(rs.getString("titulo"));
				cc.setConteudo(rs.getString("conteudo"));
				cc.setTipo(rs.getString("tipo"));
				cc.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt("codPostagem")));
				cc.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));
				cc.setComentarioPai(comentarioPai);

				listC.add(cc);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listC;
	}
}
