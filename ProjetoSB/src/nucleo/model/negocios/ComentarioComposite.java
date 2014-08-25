package nucleo.model.negocios;

import java.util.ArrayList;
import java.util.List;

public abstract class ComentarioComposite {

	private int codigo;
	private String titulo;
	private String conteudo;
	private Postagem Postagem;
	private String tipo;
	private ComentarioComposite comentarioPai;
	private Usuario usuario;
	private List<ComentarioComposite> listaComentarios = new ArrayList<ComentarioComposite>();

	public ComentarioComposite() {
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

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public List<ComentarioComposite> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<ComentarioComposite> list) {
		this.listaComentarios = list;
	}

	public Postagem getPostagem() {
		return Postagem;
	}

	public void setPostagem(Postagem postagem) {
		Postagem = postagem;
	}

	public ComentarioComposite getComentarioPai() {
		return comentarioPai;
	}

	public void setComentarioPai(ComentarioComposite comentarioPai) {
		this.comentarioPai = comentarioPai;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void addComentario(ComentarioComposite comentario) {
		this.listaComentarios.add(comentario);
	}

	public void removeComentario(ComentarioComposite comentario) {
		this.listaComentarios.remove(comentario);
	}

	public boolean equals(ComentarioComposite comentario) {
		int iguais = 0;
		 
		if (comentario.getCodigo() == this.getCodigo() 
				&& comentario.getTitulo().equals(this.getTitulo())
				&& comentario.getConteudo().equals(this.getConteudo())
				&& comentario.getPostagem().equals(this.getPostagem())
//				&& comentario.getComentarioPai().getCodigo() == this.getComentarioPai().getCodigo()
				&& comentario.getUsuario().equals(this.getUsuario())) {
			for (ComentarioComposite comentarioP : comentario.getListaComentarios())
				for (ComentarioComposite comentarioT : this.getListaComentarios())
					if (comentarioP.equals(comentarioT))
						iguais++;
			if (iguais == this.getListaComentarios().size() && this.getListaComentarios().size() == comentario.getListaComentarios().size())
				return true;
		}
		return false;
	}
}
