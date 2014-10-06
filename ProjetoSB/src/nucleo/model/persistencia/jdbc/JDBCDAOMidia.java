package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Midia;
import nucleo.model.negocios.MidiaComentario;
import nucleo.model.negocios.MidiaPostagem;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.persistencia.dao.DAOMidia;

/**
 * Classe para criaçãoo de uma Mídia.
 * 
 * @author Warley Vital
 */

public class JDBCDAOMidia extends JDBCDAO implements DAOMidia {

	/**
	 * Método construtor da classe JDBCDAOMidia
	 */
	public JDBCDAOMidia() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#criar(java.lang.Object)
	 */
	@Override
	public void criar(Midia objeto) {
		abrirConexao();
		String sqlCriar = "INSERT INTO midia(nome,codTipo,codComentario,codPostagem,tipo,descricao) VALUES (?,?,?,?,?,?)";
		String sqlCriarTM = "INSERT INTO tipo_midia VALUES (?,?)";
		String sqlVerificaTM = "SELECT * FROM tipo_midia";

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlCriar,
					PreparedStatement.RETURN_GENERATED_KEYS);
			PreparedStatement stmtTM = getConnection().prepareStatement(
					sqlCriarTM);
			PreparedStatement stmtVTM = getConnection().prepareStatement(
					sqlVerificaTM);

			ResultSet rsVTM = stmtVTM.executeQuery();

			if (!rsVTM.next())
				for (TipoMidia tipoMidia : TipoMidia.values()) {
					stmtTM.setInt(1, tipoMidia.getId());
					stmtTM.setString(2, tipoMidia.toString());
					stmtTM.execute();
				}

			stmt.setString(1, objeto.getNomeArquivo());
			stmt.setInt(2, objeto.getTipo().getId());

			MidiaPostagem mp = null;
			MidiaComentario mc = null;

			if (objeto instanceof MidiaPostagem) {
				mp = (MidiaPostagem) objeto;
				stmt.setString(5, mp.getClass().getSimpleName());
				stmt.setString(6, mp.getDescricaoArquivo());

			}

			if (objeto instanceof MidiaComentario) {
				mc = (MidiaComentario) objeto;
				stmt.setString(5, mc.getClass().getSimpleName());
				stmt.setString(6, mc.getDescricaoArquivo());
			}

			if (mc == null || mc.getComentario() == null)
				stmt.setNull(3, Types.NULL);
			else
				stmt.setInt(3, mc.getComentario().getCodigo());

			if (mp == null || mp.getPostagem() == null)
				stmt.setNull(4, Types.NULL);
			else
				stmt.setInt(4, mp.getPostagem().getCodigo());
			
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#consultar(java.lang.Object)
	 */
	@Override
	public Midia consultar(Integer id) {
		abrirConexao();
		String selectSQL = "SELECT * FROM midia WHERE codigo = ?";

		MidiaComentario mC = null;
		MidiaPostagem mP = null;

		try {
			// recuperando dados da midia
			PreparedStatement stmt = getConnection()
					.prepareStatement(selectSQL);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("tipo")
						.equals(MidiaComentario.class.getSimpleName())) {
					mC = new MidiaComentario();
					mC.setCodigo(rs.getInt(1));
					mC.setNomeArquivo(rs.getString(2));
					mC.setTipo(TipoMidia.porId(rs.getInt(3)));
					mC.setComentario(new JDBCDAOComentario().consultar(rs
							.getInt(4)));
					mC.setTipoEsp(rs.getString(6));
					mC.setDescricaoArquivo(rs.getString(7));
				} else {
					mP = new MidiaPostagem();
					mP.setCodigo(rs.getInt(1));
					mP.setNomeArquivo(rs.getString(2));
					mP.setTipo(TipoMidia.porId(rs.getInt(3)));

					mP.setPostagem(new JDBCDAOPostagem().consultar(rs
							.getInt(5)));
					mP.setTipoEsp(rs.getString(6));
					mP.setDescricaoArquivo(rs.getString(7));
				}

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return (mC == null) ? mP : mC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#alterar(java.lang.Object)
	 */
	@Override
	public void alterar(Midia objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE midia SET nome=?,codTipo=?,codComentario=?,codPostagem=?,descricao=? WHERE codigo=?";

		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);

			stmt.setString(1, objeto.getNomeArquivo());
			stmt.setInt(2, objeto.getTipo().getId());

			if (objeto instanceof MidiaComentario)
				if (((MidiaComentario) objeto).getComentario() == null)
					stmt.setNull(3, Types.NULL);
				else
					stmt.setInt(3, ((MidiaComentario) objeto).getComentario()
							.getCodigo());

			if (objeto instanceof MidiaPostagem)
				if (((MidiaPostagem) objeto).getPostagem() == null)
					stmt.setNull(4, Types.NULL);
				else
					stmt.setInt(4, ((MidiaPostagem) objeto).getPostagem()
							.getCodigo());

			stmt.setInt(5, objeto.getCodigo());
			stmt.setString(6, objeto.getDescricaoArquivo());

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
	 * @see nucleo.model.persistencia.dao.DAO#deletar(java.lang.Object)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.model.persistencia.dao.DAO#getList()
	 */
	@Override
	public List<Midia> getList() {
		abrirConexao();
		String sqlList = "SELECT * FROM midia";
		List<Midia> lm = null;
		MidiaComentario mC = null;
		MidiaPostagem mP = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);

			ResultSet rs = stmt.executeQuery();
			lm = new ArrayList<Midia>();

			while (rs.next()) {
				if (rs.getString("tipo")
						.equals(MidiaComentario.class.getSimpleName())) {
					mC = new MidiaComentario();
					mC.setCodigo(rs.getInt(1));
					mC.setNomeArquivo(rs.getString(2));
					mC.setTipoEsp(rs.getString(3));
					mC.setTipo(TipoMidia.porId(rs.getInt(4)));
					mC.setComentario(new JDBCDAOComentario().consultar(rs
							.getInt(5)));
					mC.setDescricaoArquivo(rs.getString(6));
					lm.add(mC);
				} else {
					mP = new MidiaPostagem();
					mP.setCodigo(rs.getInt(1));
					mP.setNomeArquivo(rs.getString(2));
					mP.setTipo(TipoMidia.porId(rs.getInt(3)));

					mP.setPostagem(new JDBCDAOPostagem().consultar(rs
							.getInt(5)));
					mP.setTipoEsp(rs.getString(6));
					mP.setDescricaoArquivo(rs.getString(7));
					lm.add(mP);
				}
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

	public Integer getMaxId() {
		abrirConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT MAX(codigo) FROM midia";
		int id = 0;

		try {
			stmt = getConnection().prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return id;
	}

}
