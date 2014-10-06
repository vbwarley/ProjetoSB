package nucleo.model.persistencia.dao;

import nucleo.model.negocios.ComentarioComposite;

public interface DAOComentario extends DAO<ComentarioComposite, Integer> {

	Integer getMaxId();


}
