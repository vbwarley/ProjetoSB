package nucleo.model.persistencia.factory;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Midia;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOComentario;
import nucleo.model.persistencia.dao.DAOMidia;
import nucleo.model.persistencia.dao.DAOPostagem;
import nucleo.model.persistencia.dao.DAOUsuario;

public abstract class DAOFactory {
	
	private static final Class<JDBCDAOFactory> FACTORY_CLASS = JDBCDAOFactory.class;
	
	public abstract DAOUsuario<Usuario, String> getDAOUsuario();
	public abstract DAOBlog<Blog, Integer> getDAOBlog();
	public abstract DAOPostagem<Postagem, Integer> getDAOPostagem();
	public abstract DAOMidia<Midia, Integer> getDAOMidia();
	public abstract DAOComentario<ComentarioComposite, Integer> getDAOComentario();
	
	public static DAOFactory getDAOFactory() {
		try {
			return (DAOFactory) FACTORY_CLASS.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
	
}
