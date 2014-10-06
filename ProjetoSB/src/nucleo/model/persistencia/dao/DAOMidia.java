package nucleo.model.persistencia.dao;

import nucleo.model.negocios.Midia;

public interface DAOMidia extends DAO<Midia, Integer> {

	Integer getMaxId();

}
