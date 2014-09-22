package nucleo.model.persistencia.factory;

import nucleo.model.busca.BuscaUsuario;

public class BuscaFactory<T> {
	private static final int TIPO_USUARIO = 1;
	
	public BuscaUsuario<T> criarBuscar(int buscaTipo) {
		switch (buscaTipo) {
		case TIPO_USUARIO:
			return new BuscaUsuario<T>();
		default:
			return null;
		}
		
	}
}
