package nucleo.model.persistencia.dao;

import java.util.List;
import java.util.Set;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;

public interface DAOBlog extends DAO<Blog, Integer> {

	Integer getMaxId();

	void removerAssinante(Blog blog, Usuario user);

	List<Blog> consultarPorNome(String match, String order, int maxentries);

	List<Blog> consultarPorDescricao(String match, String order, int maxentries);

	Set<Blog> getBlogsPorUsuario(Usuario usuario);

}
