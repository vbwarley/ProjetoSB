package nucleo.model.persistencia.dao;

import nucleo.model.negocios.Assinatura;

public interface DAOAssinatura extends DAO<Assinatura, Assinatura> {

	Integer getMaxId();

}
