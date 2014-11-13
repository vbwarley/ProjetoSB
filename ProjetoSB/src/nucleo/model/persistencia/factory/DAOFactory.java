package nucleo.model.persistencia.factory;

import nucleo.model.persistencia.dao.DAOAssinatura;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOComentario;
import nucleo.model.persistencia.dao.DAOMidia;
import nucleo.model.persistencia.dao.DAOPalavraChave;
import nucleo.model.persistencia.dao.DAOPostagem;
import nucleo.model.persistencia.dao.DAOUsuario;

/**
 * Fábrica abstrata de DAOs que tem como função a criação de uma familía de DAOs.
 * @author Warley Vital
 */
public abstract class DAOFactory {
	
	/**
	 * Instância da classe filha de DAOFactory, JDBCDAOFactory.
	 */
	private static final Class<JDBCDAOFactory> FACTORY_CLASS = JDBCDAOFactory.class;
	
	/**
	 * Método que retorna uma instância de DAOUsuario.
	 * 
	 * @return
	 */
	public abstract DAOUsuario getDAOUsuario();

	/**
	 * Método que retorna uma instância de DAOBlog.
	 *
	 * @return
	 */
	public abstract DAOBlog getDAOBlog();
	
	/**
	 * Método que retorna uma instância de DAOPostagem.
	 * 
	 * @return
	 */
	public abstract DAOPostagem getDAOPostagem();
	
	/**
	 * Método que retorna uma instância de DAOMidia.
	 * 
	 * @return
	 */
	public abstract DAOMidia getDAOMidia();
	
	/**
	 * Método que retorna uma instância de DAOPalavraChave.
	 * 
	 * @return
	 */
	public abstract DAOPalavraChave getDAOPalavraChave();
	
	/**
	 * Método que retorna uma instância de DAOComentario.
	 * 
	 * @return
	 */
	public abstract DAOComentario getDAOComentario();
	
	/**
	 * Método que retorna uma instância de DAOAssinatura.
	 * 
	 * @return
	 */	
	public abstract DAOAssinatura getDAOAssinatura();
	
	/**
	 * Método que retorna uma instância de DAOFactory, onde conterá a referência de sua classe filha, JDBCDAOFactory.
	 * 
	 * @return um objeto de DAOFactory
	 */
	public static DAOFactory getDAOFactory() {
		try {
			return FACTORY_CLASS.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	
	
}
