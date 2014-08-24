package nucleo.model.negocios;

import java.util.HashSet;
import java.util.Set;

public class PalavraChave {
	
	private Integer codigo;
	private String nome;
	private Set<Postagem> postagens;
	
	public PalavraChave() {
		postagens = new HashSet<Postagem>();
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
		int iguais = 0;
		
		if (palavraChave.getCodigo() == this.codigo
				&& palavraChave.getNome().equals(this.nome)) {
			for (Postagem postagemP : palavraChave.getPostagens())
				for (Postagem postagem : this.getPostagens())
					if (postagemP.getCodigo() == postagem.getCodigo())
						iguais++;
			if (iguais == this.getPostagens().size() && this.getPostagens().size() == palavraChave.getPostagens().size())
				return true;
		}
		return false;
	}
}
