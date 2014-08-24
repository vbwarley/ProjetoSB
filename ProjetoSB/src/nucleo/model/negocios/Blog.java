package nucleo.model.negocios;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Blog extends Observable {

	private int codigo;
	private String titulo;
	private String descricao;
	private String imagemFundo;
	private boolean autorizaComentario;
	private boolean autorizaComentarioAnonimo;
	private Usuario usuario;
	private Set<Usuario> assinantes;

	public Blog() {
		assinantes = new HashSet<Usuario>();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Usuario> getAssinantes() {
		return assinantes;
	}

	public void setAssinantes(Set<Usuario> assinantes) {
		this.assinantes = assinantes;
	}

	public boolean equals(Blog blog) {
		int iguais = 0;
		if (blog.getCodigo() == this.codigo
				&& blog.getTitulo().equals(this.titulo)
				&& blog.getDescricao().equals(this.descricao)
				&& blog.getImagemFundo().equals(this.imagemFundo)
				&& blog.isAutorizaComentario() == this.autorizaComentario
				&& blog.isAutorizaComentarioAnonimo() == this.autorizaComentarioAnonimo
				&& blog.getUsuario().equals(this.usuario)) {
			for (Usuario usuarioP : blog.getAssinantes())
				for (Usuario usuario : this.getAssinantes())
					if (usuarioP.equals(usuario))
						iguais++;
			if (iguais == this.getAssinantes().size() && this.getAssinantes().size() == blog.getAssinantes().size())
				return true;
		}
		return false;
	}
}
