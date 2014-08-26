package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Assinatura;
import nucleo.model.persistencia.dao.DAOAssinatura;

public class JDBCDAOAssinatura extends JDBCDAO implements DAOAssinatura {
	
	public JDBCDAOAssinatura() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public void alterar(Assinatura objeto) {
		throw new RuntimeException();		
	}

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
