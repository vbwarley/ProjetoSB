package sessao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Classe com os métodos para criar mapa que armazena os usu�rios ligados ao
 * sistema com sessionId como chave do mapa e o login como valor
 *
 * @since 28.09.14
 * @author grupo
 */
public class GerenciadorSessao {

	/*
	 * Mapa que guardará os usuários que estão logados no sistema O SessionId
	 * será a chave do mapa e o login do Usuario será o valor
	 */
	private Map<Integer, String> sessoes = null;
	private Random rand = null;
	private static GerenciadorSessao instance = null;

	private GerenciadorSessao() {
		sessoes = new HashMap<Integer, String>();
		rand = new Random();
	}

	public static GerenciadorSessao getInstance() {
		if (instance == null) {
			instance = new GerenciadorSessao();
			return instance;
		}

		return instance;
	}

	/**
	 * Método para criar e retornar uma id de um usuario que est� logando no
	 * sistema
	 */
	public Integer login(String login) {
		Integer id = rand.nextInt();
		while (sessoes.containsKey(id)) {
			id = rand.nextInt();
		}

		sessoes.put(id, login);

		return id;
	}

	/**
	 * Método para averiguar a sess�o, se � v�lida
	 */
	public boolean verificaSessaoAtiva(String login) {
		if (sessoes.containsValue(login)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método para retornar login a partir do Id
	 */
	public String getLoginById(Integer id) {
		if (sessoes.containsKey(id)) {
			return sessoes.get(id);
		}

		return null;
	}

	/**
	 * Método para apagar o id do mapa como consequ�ncia da desconex�o do
	 * sistema
	 */
	public boolean logoff(Integer id) {
		if (sessoes.containsKey(id)) {
			sessoes.remove(id);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método para apagar os ids no mapa
	 */
	public void resetSessions() {
		sessoes.clear();
	}

}