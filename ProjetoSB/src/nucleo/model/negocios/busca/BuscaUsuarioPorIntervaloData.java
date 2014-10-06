package nucleo.model.negocios.busca;

import java.util.List;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.factory.DAOFactory;

public class BuscaUsuarioPorIntervaloData implements BuscaStrategy<Usuario> {
	
	DAOFactory factory = DAOFactory.getDAOFactory();
	DAOUsuario daoUsuario = factory.getDAOUsuario();

	@Override
	public List<Usuario> buscar(String match, String order, int offset,
			int maxentries) {
		String[] matches = match.split(";");
		
		List<Usuario> resultados = daoUsuario.consultarPorIntervaloData(matches[0], matches[1], order,
				maxentries);

		if (resultados.size() > maxentries) {
			return resultados.subList(offset, offset + maxentries);
		} else {
			return resultados.subList(offset, resultados.size());
		}
	}
}
