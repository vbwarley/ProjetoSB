package nucleo.model.negocios.busca;

import java.util.List;

public interface BuscaStrategy<T> {

	List<T> buscar(String match, String order, int offset, int maxentries);

}
