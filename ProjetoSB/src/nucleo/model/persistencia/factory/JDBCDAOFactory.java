package nucleo.model.persistencia.factory;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Midia;
import nucleo.model.negocios.PalavraChave;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOComentario;
import nucleo.model.persistencia.dao.DAOMidia;
import nucleo.model.persistencia.dao.DAOPalavraChave;
import nucleo.model.persistencia.dao.DAOPostagem;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOComentario;
import nucleo.model.persistencia.jdbc.JDBCDAOMidia;
import nucleo.model.persistencia.jdbc.JDBCDAOPalavraChave;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

/**
 * Fábrica de DAOs.
 * @author Warley Vital
 */
public class JDBCDAOFactory extends DAOFactory {

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOUsuario()
	 */
	@Override
	public DAOUsuario<Usuario, String> getDAOUsuario() {
		DAOUsuario<Usuario, String> usuario = new JDBCDAOUsuario();
		return usuario;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOBlog()
	 */
	@Override
	public DAOBlog<Blog, Integer> getDAOBlog() {
		DAOBlog<Blog, Integer> blog = new JDBCDAOBlog();
		return blog;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOPostagem()
	 */
	@Override
	public DAOPostagem<Postagem, Integer> getDAOPostagem() {
		DAOPostagem<Postagem, Integer> postagem = new JDBCDAOPostagem();
		return postagem;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOMidia()
	 */
	@Override
	public DAOMidia<Midia, Integer> getDAOMidia() {
		DAOMidia<Midia, Integer> midia = new JDBCDAOMidia();
		return midia;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOComentario()
	 */
	@Override
	public DAOComentario<ComentarioComposite, Integer> getDAOComentario() {
		DAOComentario<ComentarioComposite, Integer> comentario = new JDBCDAOComentario();
		return comentario;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOPalavraChave()
	 */
	@Override
	public DAOPalavraChave<PalavraChave, Integer> getDAOPalavraChave() {
		DAOPalavraChave<PalavraChave, Integer> palavraChave = new JDBCDAOPalavraChave();
		return palavraChave;
	}

}
