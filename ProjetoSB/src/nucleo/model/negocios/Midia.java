package nucleo.model.negocios;

public abstract class Midia {

	private int codigo;
	private TipoMidia tipo;
	private String nomeArquivo;
	private String tipoEsp;
	private String descricaoArquivo;

	/**
	 * Método construtor de Mídia
	 * 
	 * @author nathalia
	 */
	public Midia() {

	}

	/**
	 * Método que recupera o código de uma mídia
	 * 
	 * @return int codigo
	 * @author nathalia
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método que atribui um código a uma mídia
	 * 
	 * @param codigo
	 *            int
	 * @author nathalia
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que recupera o tipo de uma mídia
	 * 
	 * @return TipoMidia tipo - retorna o tipo da postagem
	 * @author nathalia
	 */
	public TipoMidia getTipo() {
		return tipo;
	}

	/**
	 * Método que atribui um tipo a uma mídia
	 * 
	 * @param tipo
	 *            TipoMidia
	 * @author nathalia
	 */
	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método que recupera o nome do arquivo de uma mídia
	 * 
	 * @return String nomeArquivo
	 * @author nathalia
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * Método que atribui um nome em uma mídia
	 * 
	 * @param nomeArquivo
	 *            String
	 * @author nathalia
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoEsp() {
		return tipoEsp;
	}

	public void setTipoEsp(String tipoEsp) {
		this.tipoEsp = tipoEsp;
	}

	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	/**
	 * Método que verifica se a midia que foi passada como parâmetro é igual a
	 * esta midia.
	 * 
	 * @param midia
	 *            Midia
	 * @return boolean true (se a verificação for verdadeira), false (se a
	 *         verificação for falsa)
	 */
	public boolean equals(Midia midia) {

		if (midia.getCodigo() == this.codigo
				&& midia.getTipo().getId() == this.tipo.getId()
				&& midia.getNomeArquivo().equals(this.nomeArquivo))
			return true;
		return false;
	}
	
	
}
