package nucleo.model.negocios;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Postagem extends Observable {

	private int codigo;
	private String titulo;
	private String conteudo;
	private Blog blog;
	private Set<PalavraChave> palavraChaves;

	public Postagem() {
		palavraChaves = new HashSet<PalavraChave>();
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

	public Set<PalavraChave> getPalavraChaves() {
		return palavraChaves;
	}

	public void setPalavraChaves(Set<PalavraChave> palavraChaves) {
		this.palavraChaves = palavraChaves;
	}
	
	public void adicionaPalavraChave(PalavraChave palavraChave) {
		this.palavraChaves.add(palavraChave);
		// mais
	}
	
	public void removePalavraChave(PalavraChave palavraChave) {
		this.palavraChaves.remove(palavraChave);
		// mais
	}

}
