package nucleo.model.negocios;

/**
 * Esta classe representa a relação de assinatura entre um usuário e um blog.
 * 
 * @see {@link Usuario}
 * @see {@link Blog}
 * @author Warley Vital
 */
public class Assinatura {

	private Usuario usuario;
	private Blog blog;

	/**
	 * Construtor da classe.
	 */
	public Assinatura() {
		
	}

	/**
	 * Retorna o usuário associado à assinatura.
	 * 
	 * @return uma instância de Usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Seta o usuário referente à assinatura.
	 * 
	 * @param uma
	 *            instância de Usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorna o blog associado à assinatura.
	 * 
	 * @return uma instância de Blog
	 */
	public Blog getBlog() {
		return blog;
	}

	/**
	 * Seta o blog referente à assinatura.
	 * 
	 * @param uma
	 *            instância de Blog
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}
