package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Assinatura;
import nucleo.model.persistencia.dao.DAOAssinatura;

/**
 * Classe para criacao de assinantes de um blog.
 * 
 * @author Warley Vital
 */

public class JDBCDAOAssinatura extends JDBCDAO implements DAOAssinatura {

	/**
	 * Método construtor da classe JDBCDAOAssinatura
	 */
	public JDBCDAOAssinatura() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Assinatura objeto) {
		abrirConexao();

		String insetSql = "INSERT INTO assinatura VALUES (?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(insetSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public Assinatura consultar(Assinatura id) {
		abrirConexao();

		String selectSql = "SELECT * FROM assinatura WHERE login=? AND codBlog=?";
		Assinatura a = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(selectSql);
			stmt.setString(1, id.getUsuario().getLogin());
			stmt.setInt(2, id.getBlog().getCodigo());

			rs = stmt.executeQuery();

			if (rs.next()) {
				a = new Assinatura();
				a.setUsuario(new JDBCDAOUsuario().consultar(rs.getString(1)));
				a.setBlog(new JDBCDAOBlog().consultar(rs.getInt(2)));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}

		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Assinatura objeto) {
		throw new RuntimeException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Assinatura objeto) {
		abrirConexao();
		String updateSql = "DELETE FROM assinatura WHERE login=? AND codBlog=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(updateSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());

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
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Assinatura> getList() {
		abrirConexao();

		String selectList = "SELECT * FROM assinatura";
		List<Assinatura> listaA = null;
		Assinatura assinatura = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = getConnection().prepareStatement(selectList);

			rs = stmt.executeQuery();
			listaA = new ArrayList<Assinatura>();

			while (rs.next()) {
				assinatura = new Assinatura();
				assinatura.setUsuario(new JDBCDAOUsuario().consultar(rs.getString(1)));
				assinatura.setBlog(new JDBCDAOBlog().consultar(rs.getInt(2)));

				listaA.add(assinatura);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao(stmt, rs);
		}
		return listaA;
	}

	@Override
	public Integer getMaxId() {
		abrirConexao();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT MAX(codigo) FROM assinatura";
		int id = 0;

		try {
			stmt = getConnection().prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao(stmt, rs);
		}

		return id;
	}
}
