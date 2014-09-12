package nucleo.model.negocios;

/**
 * Enumeração com tipos de mídia.
 * 
 * @author Warley Vital
 *
 */
public enum TipoMidia {
	AUDIO(1), VIDEO(2), IMAGEM(3);

	/**
	 * ID do tipo de mídia.
	 */
	private int id;

	/**
	 * Construtor que atribui a cada item da enumeração sempre o mesmo ID.
	 * 
	 * @param id
	 *            a ser atribuído
	 */
	private TipoMidia(int id) {
		this.id = id;
	}

	/**
	 * Retorna o ID da enumeração.
	 * 
	 * @return id da enumeração
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retrona um tipo de mídia de acordo com o ID passado.
	 * 
	 * @param id
	 *            da enumeração
	 * @return um tipo de mídia se o ID existe, caso contrário null
	 */
	public static TipoMidia porId(int id) {
		for (TipoMidia m : values()) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
}
