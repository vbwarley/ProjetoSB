package nucleo.model.negocios;


public class PalavraChave {
	
	private int codigo;
	private String nome;
	
	
	/**
	 * Método construtor de palavra chave
	 * @author nathalia 
	 */
	public PalavraChave() {
	}

	/**
	 * Método que recupera o código de uma palavra chave.
	 * @return Integer codigo - retorna o código de uma palavra chave
	 * @author nathalia
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Método que atribui um código a uma palavra chave.
	 * @param codigo Integer
	 * @author nathalia
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que recupera o nome de uma palavra chave.
	 * @return String nome - retorna o nome de uma palavra chave
	 * @author nathalia
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método que atribui um nome a uma palavra chave.
	 * @param nome String
	 * @author nathalia
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método que verifica se a palavra chave que foi passada como parâmetro é igual a esta palavra chave.
	 * @param palavraChave PalavraChave
	 * @return boolean equals - retorna uma condição verdadeira ou falsa.
	 * @author nathalia
	 */
	public boolean equals(PalavraChave palavraChave) {
		if (palavraChave.getCodigo() == this.codigo
				&& palavraChave.getNome().equals(this.nome)) 
			return true;
		return false;
	}
}
