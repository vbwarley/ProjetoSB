package nucleo.model.negocios;

import java.util.Observable;

public class Postagem extends Observable {

	private int codigo;
	private String titulo;
	private String conteudo;
	private Blog blog;

	public Postagem() {
		// aqui sera chamado o update dos observadores
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
		// aqui sera chamado o update dos observadores
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public boolean equals(Postagem postagem) {
		
		if (postagem.getCodigo() == (this.codigo) 
				&& postagem.getConteudo().equals(this.conteudo)
				&& postagem.getTitulo().equals(this.titulo)
				&& postagem.getBlog().getCodigo() == this.blog.getCodigo()) 
			return true;
		return false;
	}
}
