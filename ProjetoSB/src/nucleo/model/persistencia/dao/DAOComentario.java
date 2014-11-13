package nucleo.model.persistencia.dao;

import java.util.List;

import nucleo.model.negocios.ComentarioComposite;

public interface DAOComentario extends DAO<ComentarioComposite, Integer> {

	Integer getMaxId();

	List<ComentarioComposite> consultaSubComentarios(ComentarioComposite comentarioPai);


}
