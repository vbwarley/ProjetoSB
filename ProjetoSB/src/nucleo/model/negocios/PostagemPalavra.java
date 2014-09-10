package nucleo.model.negocios;

public class PostagemPalavra {
	
	private Postagem postagem;
	private PalavraChave palavraChave;
	
	/**
	 * Método construtor de PostagemPalavra.
	 * @author nathalia
	 */
	public PostagemPalavra() {
		
	}

	/**
	 * Método que recupera uma postagem.
	 * @return Postagem postagem
	 * @author nathalia
	 */
	public Postagem getPostagem() {
		return postagem;
	}

	/**
	 * Método que atribui dados a uma postagem
	 * @param postagem Postagem
	 * @author nathalia
	 */
	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	/**
	 * Método que recupera uma palavra chave.
	 * @return PalavraChave palavraChave
	 * @author nathalia
	 */
	public PalavraChave getPalavraChave() {
		return palavraChave;
	}

	/**
	 * Método que atribui/cria uma palavra chave
	 * @param palavraChave PalavraChave
	 * @author nathalia
	 */
	public void setPalavraChave(PalavraChave palavraChave) {
		this.palavraChave = palavraChave;
	}
	
}
