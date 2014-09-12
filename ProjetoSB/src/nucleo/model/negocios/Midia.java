package nucleo.model.negocios;

public class Midia {

	private int codigo;
	private TipoMidia tipo;
	private String nomeArquivo;
	private ComentarioComposite comentario;
	private Postagem postagem;

	/**
	 * Método construtor de Mídia
	 * @author nathalia
	 */
	public Midia() {
		
	}

	/**
	 * Método que recupera o código de uma mídia
	 * @return int codigo
	 * @author nathalia
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método que atribui um código a uma mídia
	 * @param codigo int
	 * @author nathalia
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que recupera o tipo de uma mídia
	 * @return TipoMidia tipo - retorna o tipo da postagem
	 * @author nathalia
	 */
	public TipoMidia getTipo() {
		return tipo;
	}

	/**
	 * Método que atribui um tipo a uma mídia
	 * @param tipo TipoMidia
	 * @author nathalia
	 */
	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método que recupera o nome do arquivo de uma mídia
	 * @return String nomeArquivo
	 * @author nathalia
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * Método que atribui um nome em uma mídia
	 * @param nomeArquivo String
	 * @author nathalia
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	/**
	 * Método que recupera um comentário
	 * @return ComentarioComposite comentario
	 * @author nathalia
	 */
	public ComentarioComposite getComentario() {
		return comentario;
	}

	/**
	 * Método que atribui um comentário a uma mídia
	 * @param comentario ComentarioComposite
	 * @author nathalia
	 */
	public void setComentario(ComentarioComposite comentario) {
		this.comentario = comentario;
	}

	/**
	 * Método que recupera uma postagem
	 * @return Postagem postagem
	 * @author nathalia
	 */
	public Postagem getPostagem() {
		return postagem;
	}

	/**
	 * Método que atribui uma postagem
	 * @param postagem Postagem
	 * @author nathalia
	 */
	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	/**
	 * Método que verifica se a midia que foi passada como parâmetro é igual a esta midia.
	 * @param midia Midia
	 * @return boolean true (se a verificação for verdadeira), false (se a verificação for falsa)
	 */
	public boolean equals(Midia midia) {

		if (midia.getCodigo() == this.codigo
				&& midia.getTipo().getId() == this.tipo.getId()
				&& midia.getNomeArquivo().equals(this.nomeArquivo)
				&& (midia.getComentario() != null ? midia.getComentario().equals(this.comentario) : (midia.getComentario() == this.comentario))
				&& (midia.getPostagem() != null ? midia.getPostagem().equals(this.postagem) : (midia.getPostagem() == this.postagem)))
			return true;
		return false;
	}
}
