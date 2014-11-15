package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Assinatura;
import nucleo.model.persistencia.dao.DAOAssinatura;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.factory.DAOFactory;

/**
 * Classe para criacao de assinantes de um blog.
 * 
 * @author Warley Vital
 */

public class JDBCDAOAssinatura extends JDBCDAO implements DAOAssinatura {

	private DAOFactory factory = DAOFactory.getDAOFactory();
	private DAOUsuario daoUsuario = factory.getDAOUsuario();
	private DAOBlog daoBlog = factory.getDAOBlog();

	
	/**
	 * Método construtor da classe JDBCDAOAssinatura
	 */
	public JDBCDAOAssinatura() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Assinatura objeto) {

		String insetSql = "INSERT INTO assinatura VALUES (?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(insetSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());

			stmt.execute();
			stmt.close();
			
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
	public Assinatura consultar(Assinatura id) {

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
				
				a.setUsuario(daoUsuario.consultar(rs.getString(1)));
				a.setBlog(daoBlog.consultar(rs.getInt(2)));
			}
			
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
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
		String updateSql = "DELETE FROM assinatura WHERE login=? AND codBlog=?";

		PreparedStatement stmt = null;

		try {
			stmt = getConnection().prepareStatement(updateSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Assinatura> getList() {

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
								
				assinatura.setUsuario(daoUsuario.consultar(rs.getString(1)));
				assinatura.setBlog(daoBlog.consultar(rs.getInt(2)));

				listaA.add(assinatura);
			}
			
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaA;
	}

	@Override
	public Integer getMaxId() {

		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT MAX(codigo) FROM assinatura";
		int id = 0;

		try {
			stmt = getConnection().prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next())
				id = rs.getInt(1);

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
