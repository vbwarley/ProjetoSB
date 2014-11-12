package nucleo.model.persistencia.dao;

import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;

public interface DAOUsuario extends DAO<Usuario, String> {

	boolean validacaoLogin(String login, String senha);

	List<Usuario> consultarPorNome(String match, String order, int maxentries);

	List<Usuario> consultarPorLogin(String match, String order, int maxentries);

	List<Blog> getBlogsSeguidos(Usuario usuario);

	List<Usuario> consultarPorEmail(String match);

	List<Usuario> consultarPorIntervaloData(String from, String to, String order, int maxentries);

}
