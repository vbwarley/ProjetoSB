package nucleo.model.negocios;

import java.util.Observable;
import java.util.Set;

public class Postagem extends Observable {

	private int codigo;
	private String titulo;
	private String conteudo;
	private Set<String> palavrasChave;
	private Blog blog;

	public Postagem() {
		
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

	public Set<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(Set<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	

}