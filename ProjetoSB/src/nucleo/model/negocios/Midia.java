package nucleo.model.negocios;

public class Midia {

	private int codigo;
	private TipoMidia tipo;
	private String nomeArquivo;
	private ComentarioComposite comentario;
	private Postagem postagem;

	public Midia() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TipoMidia getTipo() {
		return tipo;
	}

	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public ComentarioComposite getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioComposite comentario) {
		this.comentario = comentario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public boolean equals(Midia midia) {

		if (midia.getCodigo() == this.codigo
				&& midia.getTipo().getId() == this.tipo.getId()
				&& midia.getNomeArquivo().equals(this.nomeArquivo)
				&& (midia.getComentario() != null ? midia.getComentario().equals(this.comentario) : true)
				&& (midia.getPostagem() != null ? midia.getComentario().equals(
						this.postagem) : true))
			return true;
		return false;
	}
}
