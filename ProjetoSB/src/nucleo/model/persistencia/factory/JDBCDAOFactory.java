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
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

public class JDBCDAOFactory extends DAOFactory {

	@Override
	public DAOUsuario<Usuario, String> getDAOUsuario() {
		DAOUsuario<Usuario, String> usuario = new JDBCDAOUsuario(); 
		return usuario;
	}

	@Override
	public DAOBlog<Blog, Integer> getDAOBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOPostagem<Postagem, Integer> getDAOPostagem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOMidia<Midia, Integer> getDAOMidia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOComentario<ComentarioComposite, Integer> getDAOComentario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOPalavraChave<PalavraChave, Integer> getDAOPalavraChave() {
		// TODO Auto-generated method stub
		return null;
	}

}
