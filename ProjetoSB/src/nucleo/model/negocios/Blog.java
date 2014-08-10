package nucleo.model.negocios;

public class Blog {
	
	private int codigo;
	private String titulo;
	private String descricao;
	private String imagemFundo;
	private boolean autorizaComentario;
	private boolean autorizaComentarioAnonimo;
	
	public Blog() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemFundo() {
		return imagemFundo;
	}

	public void setImagemFundo(String imagemFundo) {
		this.imagemFundo = imagemFundo;
	}

	public boolean isAutorizaComentario() {
		return autorizaComentario;
	}

	public void setAutorizaComentario(boolean autorizaComentario) {
		this.autorizaComentario = autorizaComentario;
	}

	public boolean isAutorizaComentarioAnonimo() {
		return autorizaComentarioAnonimo;
	}

	public void setAutorizaComentarioAnonimo(boolean autorizaComentarioAnonimo) {
		this.autorizaComentarioAnonimo = autorizaComentarioAnonimo;
	}
	
	
}
