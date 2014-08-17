package nucleo.model.negocios;

public enum TipoMidia {
	AUDIO(1), VIDEO(2), IMAGEM(3);
	
	private int id;
	
	private TipoMidia(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static TipoMidia porId(int id) {
		for (TipoMidia m : values()) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
}
