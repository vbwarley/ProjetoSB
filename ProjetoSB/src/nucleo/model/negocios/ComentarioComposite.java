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

	
	
	/**
	 * Construtor da classe ComentarioComposite
	 * @author Douglas
	 */
	public ComentarioComposite() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método que recupera o código do comentário
	 * @return Int codigo - retorna o código do comentário
	 * @author Douglas
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método que atribue um código ao comentário
	 * @param codigo Int - parâmetro passado ao método setCodigo
	 * @author Douglas
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que recupera o título do comentário
	 * @return String titulo - retorna o titulo do comentário
	 * @author Douglas
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Método que atribue um titulo ao comentário
	 * @param titulo String - parâmetro passado ao método setTitulo
	 * @author Douglas
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Método que retorna o titulo do comentário
	 * @return String conteudo - retorna o conteúdo do comentário
	 * @author Douglas
	 */
	public String getConteudo() {
		return conteudo;
	}

	/**
	 * Método que atribue um conteúdo ao comentário
	 * @param conteudo String - parâmetro passado ao método setConteudo
	 * @author Douglas
	 */
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * Método que recupera uma lista dos comentários de comentários filhos, caso este (this) seja um comentário pai 
	 * @return List<ComentarioComposite> retorna uma lista de comentários
	 * @author Douglas
	 */
	public List<ComentarioComposite> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * Método para setar comentários filhos, caso este (this) comentário seja um comentário pai.
	 * @param list List - parâmetro passado ao método setListaComentarios
	 * @author Douglas
	 */
	public void setListaComentarios(List<ComentarioComposite> list) {
		this.listaComentarios = list;
	}

	/**
	 * Método que retorna a postagem do blog
	 * @return Postagem postagem - retorna uma postagem do blog
	 * @author Douglas
	 */
	public Postagem getPostagem() {
		return Postagem;
	}

	/**
	 * Método que atribue a postagem ao blog
	 * @param postagem Postagem - parâmetro passado ao método setPostagem
	 * @author Douglas
	 */
	public void setPostagem(Postagem postagem) {
		Postagem = postagem;
	}

	/**
	 * Retorna comentário pai, caso este (this) seja um comtário filho.
	 * @return ComentarioComposite comentarioPai - comentário pai
	 */
	public ComentarioComposite getComentarioPai() {
		return comentarioPai;
	}

	/**
	 *Método para atribuer um comentário pai, caso este (this) seja um comentário filho
	 * @param ComentarioComposite comentarioPai
	 */
	public void setComentarioPai(ComentarioComposite comentarioPai) {
		this.comentarioPai = comentarioPai;
	}

	/**
	 * Método que retorna um usuário que assina o blog
	 * @return Usuario usuario - retorna um usuário assinante do blog
	 * @author Douglas
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * O método atribue um usuário ao blog
	 * @param usuario Usuario - parâmetro passado ao método setUsuario
	 * @author Douglas
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método que retorna o tipo do comentário
	 * @return String tipo - retorna o tipo do comentário
	 * @author Douglas
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método que atribue um tipo ao comentário
	 * @param String tipo - atribue um tipo ao comentário
	 * @author Douglas
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método que adiciona um comentário a uma lista de comentários
	 * @param comentario Comentario - adiciona um comentário a uma lista de comentários
	 * @author Douglas
	 */
	public void addComentario(ComentarioComposite comentario) {
		this.listaComentarios.add(comentario);
	}

	/**
	 * Método que exclui um comentário de uma lista de comentários filhos
	 * @param comentario Comentario - exclui um comentário
	 * @author Douglas
	 */
	public void removeComentario(ComentarioComposite comentario) {
		this.listaComentarios.remove(comentario);
	}

	/**
	 * O método verifica se este comentário (this) é igual ao comentário passado como parâmetro
	 * @param ComentarioComposite comentario - comentário a ser comparado.
	 * @return Boolean equals - se o comentário estiver com todos os campos preenchidos corretamente ele retorna um true, caso contrário ele retorna um false
	 * @author Douglas
	 */
	public boolean equals(ComentarioComposite comentario) {
		 
		if (comentario.getCodigo() == this.getCodigo())
				return true;
		
		return false;
	}
}
