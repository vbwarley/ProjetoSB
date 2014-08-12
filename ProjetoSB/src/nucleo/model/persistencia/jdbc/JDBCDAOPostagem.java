package nucleo.model.persistencia.jdbc;

import java.util.List;

import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOPostagem;

public class JDBCDAOPostagem extends JDBCDAO implements DAOPostagem<Postagem, Integer>{

	@Override
	public void criar(Postagem objeto) {
		String sql = "INSERT INTO postagem VALUES (?,?,?,?,?)";
		String sqlPP = "INSERT INTO postagem_palavras (?,?)";
		String sqlP = "INSERT INTO palavras_chaves (?,?)";

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
