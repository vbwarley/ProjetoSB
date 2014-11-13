package nucleo.model.persistencia.factory;

import nucleo.model.persistencia.dao.DAOAssinatura;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOComentario;
import nucleo.model.persistencia.dao.DAOMidia;
import nucleo.model.persistencia.dao.DAOPalavraChave;
import nucleo.model.persistencia.dao.DAOPostagem;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.jdbc.JDBCDAOAssinatura;
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
	public DAOUsuario getDAOUsuario() {
		DAOUsuario usuario = new JDBCDAOUsuario();
		return usuario;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOBlog()
	 */
	@Override
	public DAOBlog getDAOBlog() {
		DAOBlog	 blog = new JDBCDAOBlog();
		return blog;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOPostagem()
	 */
	@Override
	public DAOPostagem getDAOPostagem() {
		DAOPostagem postagem = new JDBCDAOPostagem();
		return postagem;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOMidia()
	 */
	@Override
	public DAOMidia getDAOMidia() {
		DAOMidia midia = new JDBCDAOMidia();
		return midia;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOComentario()
	 */
	@Override
	public DAOComentario getDAOComentario() {
		DAOComentario comentario = new JDBCDAOComentario();
		return comentario;
	}

	/* (non-Javadoc)
	 * @see nucleo.model.persistencia.factory.DAOFactory#getDAOPalavraChave()
	 */
	@Override
	public DAOPalavraChave getDAOPalavraChave() {
		DAOPalavraChave palavraChave = new JDBCDAOPalavraChave();
		return palavraChave;
	}

	@Override
	public DAOAssinatura getDAOAssinatura() {
		DAOAssinatura assinatura = new JDBCDAOAssinatura();
		return assinatura;
	}

}
