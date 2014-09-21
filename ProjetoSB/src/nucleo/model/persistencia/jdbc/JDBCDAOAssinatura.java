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
 * @author Warley Vital
 */

public class JDBCDAOAssinatura extends JDBCDAO implements DAOAssinatura {

	/**
	 * Método construtor da classe JDBCDAOAssinatura
	 */
	public JDBCDAOAssinatura() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Assinatura objeto) {
		abrirConexao();
		String insetSql = "INSERT INTO assinatura VALUES (?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(insetSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());
			
			stmt.execute();
			
			stmt.close();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public Assinatura consultar(Assinatura id) {
		abrirConexao();
		String selectSql = "SELECT * FROM assinatura WHERE login=? AND codBlog=?";
		Assinatura a = null;
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(selectSql);
			stmt.setString(1, id.getUsuario().getLogin());
			stmt.setInt(2, id.getBlog().getCodigo());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				a = new Assinatura();
				a.setUsuario(new JDBCDAOUsuario().consultar(rs.getString(1)));
				a.setBlog(new JDBCDAOBlog().consultar(rs.getInt(2)));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		
		return a;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Assinatura objeto) {
		throw new RuntimeException();		
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	public void deletar(Assinatura objeto) {
		abrirConexao();
		String updateSql = "DELETE FROM assinatura WHERE login=? AND codBlog=?";
				
		try {
			PreparedStatement stmt = getConnection().prepareStatement(updateSql);
			stmt.setString(1, objeto.getUsuario().getLogin());
			stmt.setInt(2, objeto.getBlog().getCodigo());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override	
	public List<Assinatura> getList() {
		abrirConexao();
		String selectList = "SELECT * FROM assinatura";
		List<Assinatura> listaA = null;
		Assinatura assinatura = null;
		
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(selectList);
			
			ResultSet rs = stmt.executeQuery();
			listaA = new ArrayList<Assinatura>();
			
			while (rs.next()) {
				assinatura = new Assinatura();
				assinatura.setUsuario(new JDBCDAOUsuario().consultar(rs.getString(1)));
				assinatura.setBlog(new JDBCDAOBlog().consultar(rs.getInt(2)));
				
				listaA.add(assinatura);
			}
				
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
		return listaA;
	}
}
