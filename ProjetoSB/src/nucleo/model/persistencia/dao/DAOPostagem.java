package nucleo.model.persistencia.dao;

import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;

public interface DAOPostagem extends DAO<Postagem, Integer> {

	Integer getMaxId();

	List<Postagem> getPostagensPorBlog(Blog blog);

}
