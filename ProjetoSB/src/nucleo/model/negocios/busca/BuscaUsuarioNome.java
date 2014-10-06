package nucleo.model.negocios.busca;

import java.util.List;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.factory.DAOFactory;

public class BuscaUsuarioNome implements BuscaStrategy<Usuario> {
	
	private DAOFactory factory = DAOFactory.getDAOFactory(); 
	private DAOUsuario daoUsuario = factory.getDAOUsuario();
	
	@Override
	public List<Usuario> buscar(String match, String order, int offset,
			int maxentries) {
		List<Usuario> resultados = daoUsuario.consultarPorNome(match, order,
				maxentries);

		if (resultados.size() > maxentries) 
			return resultados.subList(offset, offset + maxentries);
		else 
			return resultados.subList(offset, resultados.size());
		
	}

}
