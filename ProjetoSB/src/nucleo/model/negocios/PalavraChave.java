package nucleo.model.negocios;

import java.util.HashSet;
import java.util.Set;

public class PalavraChave {
	
	private Integer codigo = 0;
	private String nome;
	private Set<Postagem> postagens;
	
	public PalavraChave() {
		postagens = new HashSet<Postagem>();
		codigo += 1;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(Set<Postagem> postagens) {
		this.postagens = postagens;
	}
	
	public void adicionaPostagem(Postagem postagem) {
		this.postagens.add(postagem);
	}
	
	public void removePostagem(Postagem postagem) {
		this.postagens.remove(postagem);
	}
	
	public boolean equals(PalavraChave palavraChave) {

		if (palavraChave.getCodigo() == this.codigo
				&& palavraChave.getNome().equals(this.nome)
				&& palavraChave.getPostagens().equals(this.postagens))
			return true;
		return false;
	}
}
