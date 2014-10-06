package nucleo.model.negocios.busca;

import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.factory.DAOFactory;

public class BuscaBlogNome implements BuscaStrategy<Blog> {

	private DAOFactory factory = DAOFactory.getDAOFactory();
	private DAOBlog daoBlog = factory.getDAOBlog();
	
	@Override
	public List<Blog> buscar(String match, String order, int offset,
			int maxentries) {		
		List<Blog> resultados = daoBlog.consultarPorNome(match, order,
				maxentries);

		if (resultados.size() > maxentries) {
			return resultados.subList(offset, offset + maxentries);
		} else {
			return resultados.subList(offset, resultados.size());
		}
	}
}
