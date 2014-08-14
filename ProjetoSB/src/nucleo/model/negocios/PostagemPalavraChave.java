package nucleo.model.negocios;

public class PostagemPalavraChave {

	private Integer codigo;
	private Postagem postagem;
	private PalavraChave palavraChave;

	public PostagemPalavraChave() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public PalavraChave getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(PalavraChave palavraChave) {
		this.palavraChave = palavraChave;
	}

}
