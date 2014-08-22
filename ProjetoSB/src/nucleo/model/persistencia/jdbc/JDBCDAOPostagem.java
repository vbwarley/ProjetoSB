package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PalavraChave;
import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.dao.DAOPostagem;

public class JDBCDAOPostagem extends JDBCDAO implements
		DAOPostagem<Postagem, Integer> {

	public JDBCDAOPostagem() {
		
	}

	@Override
	public void criar(Postagem objeto) {
		
		abrirConexao();
		String sql = "INSERT INTO postagem (titulo,conteudo,codBlog) VALUES (?,?,?)";
		String sqlPostagemPalavras = "INSERT INTO postagem_palavras VALUES (?,?)";

		
		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			PreparedStatement stmtPostPalavras = getConnection()
					.prepareStatement(sqlPostagemPalavras);

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setInt(3, objeto.getBlog().getCodigo());
			
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
				objeto.setCodigo(rs.getInt(1));
			
			for (PalavraChave palavraChave : objeto.getPalavraChaves()) {
				stmtPostPalavras.setInt(1, objeto.getCodigo());
				stmtPostPalavras.setInt(2, palavraChave.getCodigo());

				stmtPostPalavras.execute();
			}

			stmt.close();
			stmtPostPalavras.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

	}

	@Override
	public Postagem consultar(Integer id) {
		abrirConexao();
		String PostagemSQL = "Select * from postagem where codigo = ?";
		String PalavraSQL = "Select * from postagem_palavras where codPostagem = ?";

		Postagem p = null;

		try {

			PreparedStatement stmt = getConnection().prepareStatement(
					PostagemSQL);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			PreparedStatement stmtPalavra = getConnection().prepareStatement(
					PalavraSQL);
			stmtPalavra.setInt(1, id);
			ResultSet rsPC = stmtPalavra.executeQuery();

			while (rs.next()) {
				p = new Postagem();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(new JDBCDAOBlog().consultar(rs.getInt(4)));

				while (rsPC.next())
					p.getPalavraChaves()
							.add(new JDBCDAOPalavraChave().consultar(rsPC
									.getInt(2)));

			}

			stmt.close();
			stmtPalavra.close();
			rs.close();
			rsPC.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}

		return p;
	}

	@Override
	public void alterar(Postagem objeto) {
		abrirConexao();
		String sqlUpdate = "UPDATE postagem SET titulo=?,conteudo=?,codBlog=? WHERE codigo=?";
		String sqlPost = "";

		Postagem postagem = consultar(objeto.getCodigo());
		PalavraChave palavra_chave = null;

		if (objeto.getPalavraChaves().size() > postagem.getPalavraChaves()
				.size()) {
			for (PalavraChave palavra_chave1 : objeto.getPalavraChaves())
				if (!postagem.getPalavraChaves().contains(palavra_chave)) {
					sqlPost = "INSERT INTO postagem_palavras VALUES (?,?)";
					palavra_chave = palavra_chave1;
				}
		} else if (objeto.getPalavraChaves().size() < postagem
				.getPalavraChaves().size()) {
			if (!objeto.getPalavraChaves().isEmpty())
				sqlPost = "DELETE FROM postagem_palavras WHERE codPostagem=? AND codPalavra NOT IN(?"
						+ new String(
								new char[(objeto.getPalavraChaves().size() - 1)])
								.replace("\0", ",?") + ")";
			else
				sqlPost = "DELETE FROM postagem_palaras WHERE codPalavra=?";
		}

		try {
			abrirConexao();
			
			PreparedStatement stmt = getConnection()
					.prepareStatement(sqlUpdate);
			PreparedStatement stmtPalavraChave = getConnection()
					.prepareStatement(sqlPost);

			if (sqlPost.contains("INSERT")) {
				stmtPalavraChave.setInt(1, objeto.getCodigo());
				stmtPalavraChave.setInt(2, postagem.getCodigo());
				stmtPalavraChave.execute();
			} else if (sqlPost.contains("DELETE")) {
				int c = 0;

				for (PalavraChave palavraC : objeto.getPalavraChaves()) {
					c++;
					stmtPalavraChave.setInt(c, palavraC.getCodigo());
				}

				stmtPalavraChave.executeUpdate();
			}

			stmt.setString(1, objeto.getTitulo());
			stmt.setString(2, objeto.getConteudo());
			stmt.setInt(3, objeto.getBlog().getCodigo());
			stmt.setInt(4, objeto.getCodigo());

			stmt.executeUpdate();
			stmt.close();
			stmtPalavraChave.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(Postagem objeto) {
		abrirConexao();
		
		String sqlDelete = "DELETE FROM postagem WHERE codigo = ?";

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

	@Override
	public List<Postagem> getList() {
		abrirConexao();
		
		String sqlList = "SELECT * FROM postagem";
		String sqlListPC = "SELECT * FROM postagem_palavras";

		List<Postagem> po = null;
		Postagem p = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sqlList);
			ResultSet rs = stmt.executeQuery(sqlList);

			PreparedStatement stmtPC = getConnection().prepareStatement(
					sqlListPC);
			ResultSet rsPC = stmtPC.executeQuery();
			po = new ArrayList<Postagem>();
			
			while (rs.next()) {
				p = new Postagem();
				

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(new JDBCDAOBlog().consultar(rs.getInt(4)));

				po.add(p);

			}

			while (rsPC.next()) {
				for (Postagem postagem : po)
					if (rsPC.getInt(1) == postagem.getCodigo()) {
						postagem.getPalavraChaves().add(
								new JDBCDAOPalavraChave().consultar(rsPC
										.getInt(2)));

					}
			}

			stmt.close();
			stmtPC.close();
			rs.close();
			rsPC.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			fecharConexao();
		}

		return po;
	}
}
