package nucleo.model.persistencia.factory;

import nucleo.model.negocios.busca.BuscaStrategy;
import nucleo.model.negocios.busca.BuscaUsuarioLogin;
import nucleo.model.negocios.busca.BuscaUsuarioNome;

/**
 * Fábrica de buscas.
 * 
 * @author Warley Vital
 * @param <T> - tipo de parâmetro que será utilizado. Por exemplo: Usuario.
 */
public class BuscaFactory {
	private static final int TIPO_USUARIO_NOME = 1;
	private static final int TIPO_USUARIO_LOGIN = 2;

	/**
	 * Este método retorna um tipo específicio de busca.
	 * @param buscaTipo - tipo da busca a ser retornada
	 * @return uma instância de determinda busca
	 */
	public BuscaStrategy<?> criarBuscar(int buscaTipo) {
		switch (buscaTipo) {
		case TIPO_USUARIO_NOME:
			return new BuscaUsuarioNome();
		case TIPO_USUARIO_LOGIN:
			return new BuscaUsuarioLogin();
		default:
			return null;
		}
		
	}
}
