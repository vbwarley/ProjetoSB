package nucleo.model.negocios;

import java.util.HashSet;
import java.util.Set;

public class PalavraChave {
	
	private Integer codigo;
	private String nome;
	
	public PalavraChave() {
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
	
	public boolean equals(PalavraChave palavraChave) {
		if (palavraChave.getCodigo() == this.codigo
				&& palavraChave.getNome().equals(this.nome)) 
			return true;
		return false;
	}
}
