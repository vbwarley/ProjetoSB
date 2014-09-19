package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Midia;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.persistencia.dao.DAOMidia;

/**
 * Classe para criacao de Midia
 * @author Raiane
 */

public class JDBCDAOMidia extends JDBCDAO implements DAOMidia<Midia, Integer> {

	/**
	 * Método construtor da classe JDBCDAOMidia
	 * @author Raiane
	 */
	public JDBCDAOMidia() {
		
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	
	/**
	 * Método para criação de uma midia
	 * @param objeto Midia - parâmetro passado ao método criar
	 * @author raiane
	 */
	
	public void criar(Midia objeto) {
		abrirConexao();
		String sqlCriar = "INSERT INTO midia(nome,codTipo,codComentario,codPostagem) VALUES (?,?,?,?)";
		String sqlCriarTM = "INSERT INTO tipo_midia VALUES (?,?)";
		String sqlVerificaTM = "SELECT * FROM tipo_midia";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlCriar, PreparedStatement.RETURN_GENERATED_KEYS);
			PreparedStatement stmtTM = getConnection().prepareStatement(sqlCriarTM);
			PreparedStatement stmtVTM = getConnection().prepareStatement(sqlVerificaTM);
			
			ResultSet rsVTM = stmtVTM.executeQuery();
			
			if (!rsVTM.next())
				for (TipoMidia tipoMidia : TipoMidia.values()) {
					stmtTM.setInt(1, tipoMidia.getId());
					stmtTM.setString(2, tipoMidia.toString());
					stmtTM.execute();
				}
					
			stmt.setString(1, objeto.getNomeArquivo());
			stmt.setInt(2, objeto.getTipo().getId());
			
			if (objeto.getComentario() == null)
				stmt.setNull(3, Types.NULL);
			else
				stmt.setInt(3, objeto.getComentario().getCodigo());
			
			if (objeto.getPostagem() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, objeto.getPostagem().getCodigo());
		
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));

			stmt.close();
			rs.close();
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
	
	/**
	 * Método para consulta de uma midia
	 * @param id Integer - identificador de uma midia
	 * @return Midia m - retorna os dados de uma midia
	 * @author raiane
	 */
	
	public Midia consultar(Integer id) {
		abrirConexao();
		String selectSQL = "SELECT * FROM midia WHERE codigo = ?";

		Midia m = null;

		try {
			// recuperando dados da midia
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				m = new Midia();

				m.setCodigo(rs.getInt(1));
				m.setNomeArquivo(rs.getString(2));
				m.setTipo(TipoMidia.porId(rs.getInt(3)));
				m.setComentario(new JDBCDAOComentario().consultar(rs.getInt(4)));
				m.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(5)));

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return m;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	
	/**
	 * Método que altera os dados de uma midia
	 * @param objeto Midia - parâmetro passado ao método alterar
	 * @author raiane
	 */
	
	public void alterar(Midia objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE midia SET nome=?,codTipo=?,codComentario=?,codPostagem=? WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getNomeArquivo());
			stmt.setInt(2, objeto.getTipo().getId());
			if (objeto.getComentario() == null)
				stmt.setNull(3, Types.NULL);
			else
				stmt.setInt(3, objeto.getComentario().getCodigo());
			
			if (objeto.getPostagem() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, objeto.getPostagem().getCodigo());
		
			stmt.setInt(4, objeto.getPostagem().getCodigo());
			stmt.setInt(5, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
	
	/**
	 * Método que exclui uma midia
	 * @param objeto Midia - parâmetro passado ao método deletar
	 * @author raiane
	 */
	
	public void deletar(Midia objeto) {
		abrirConexao();
		String sqlDelete = "DELETE FROM midia WHERE codigo = ?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlDelete);

			stmt.setInt(1, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	
	/**
	 * Método para criação de uma lista de midias
	 * @return List<Midia> lm - retorna uma lista de midias do blog
	 * @author raiane
	 */
	
	public List<Midia> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM midia";
		List<Midia> lm = null;
		Midia m = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery();
			lm = new ArrayList<Midia>(); // pode ser HashSet
			
			while (rs.next()) {
				m = new Midia();
				
				m.setCodigo(rs.getInt(1));
				m.setNomeArquivo(rs.getString(2));
				m.setTipo(TipoMidia.porId(rs.getInt(3)));
				m.setComentario(new JDBCDAOComentario().consultar(rs.getInt(4)));
				m.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt(5)));

				lm.add(m);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return lm;
	}
}
