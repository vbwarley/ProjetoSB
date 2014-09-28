package nucleo.model.persistencia.factory;

import nucleo.model.busca.BuscaBlog;
import nucleo.model.busca.BuscaPostagem;
import nucleo.model.busca.BuscaStrategy;
import nucleo.model.busca.BuscaUsuario;

/**
 * Fábrica de buscas.
 * 
 * @author Warley Vital
 * @param <T> - tipo de parâmetro que será utilizado. Por exemplo: Usuario.
 */
public class BuscaFactory<T> {
	private static final int TIPO_USUARIO = 1;
	private static final int TIPO_BLOG = 2;
	private static final int TIPO_POSTAGEM = 3;

	/**
	 * Este método retorna um tipo específicio de busca.
	 * @param buscaTipo - tipo da busca a ser retornada
	 * @return uma instância de determinda busca
	 */
	public BuscaStrategy<T> criarBuscar(int buscaTipo) {
		switch (buscaTipo) {
		case TIPO_USUARIO:
			return new BuscaUsuario<>();
		case TIPO_BLOG:
			return new BuscaBlog<>();
		case TIPO_POSTAGEM:
			return new BuscaPostagem<>();
		default:
			return null;
		}
		
	}
}
