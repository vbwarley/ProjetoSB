package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.dao.DAOPostagem;

public class JDBCDAOPostagem extends JDBCDAO implements DAOPostagem<Postagem, Integer>{

	@Override
	public void criar(Postagem objeto) {
		String sql = "INSERT INTO postagem VALUES (?,?,?,?,?)";
		String sqlPP = "INSERT INTO postagem_palavras (?,?)";
		String sqlP = "INSERT INTO palavras_chaves (?,?)";
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			PreparedStatement stmtPalavras_chave = getConnection().prepareStatement(sqlP);
			PreparedStatement stmtPostagem_palavras = getConnection().prepareStatement(sqlPP);
			
			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			
			stmt.execute();
					
			
		} catch (Exception e) {
			
		}

	}

	@Override
	public Postagem consultar(Integer id) {
		
		return null;
	}

	@Override
	public void alterar(Postagem objeto) {
		
		
	}

	@Override
	public void deletar(Postagem objeto) {
		
		
	}

	@Override
	public List<Postagem> getList() {
		
		return null;
	}
}
