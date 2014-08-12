package nucleo.model.persistencia.dao;

import java.util.List;

import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Usuario;

public interface DAO<T, type> {

	void criar(T objeto);
	T consultar(type id);
	void alterar(T objeto);
	void deletar(T objeto);
	List<T> getList();
}
