package nucleo.model.negocios;

import java.sql.Date;

public class Postagem {

	private int codigo;
	private String titulo;
	private String conteudo;
	private Blog blog;
	private Date data;

	/**
	 * Método construtor de Postagem
	 * 
	 * @author nathalia
	 */
	public Postagem() {

	}

	/**
	 * Retorna o código de uma postagem.
	 * 
	 * @return int codigo - retorna o código de uma postagem.
	 * @author nathalia
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método que seta o código de uma postagem.
	 * 
	 * @param codigo
	 *            int
	 * @author nathalia
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o título de uma postagem.
	 * 
	 * @return String titulo
	 * @author nathalia
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Método que seta o título de uma postagem.
	 * 
	 * @param titulo
	 *            String
	 * @author nathalia
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Retorna o conteúdo de uma postagem.
	 * 
	 * @return String conteudo
	 * @author nathalia
	 */
	public String getConteudo() {
		return conteudo;
	}

	/**
	 * Método que seta o conteúdo de uma postagem.
	 * 
	 * @param conteudo
	 *            String
	 * @author nathalia
	 */
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * Método que retorna o blog associado à postagem.
	 * 
	 * @return Blog blog
	 * @author nathalia
	 */
	public Blog getBlog() {
		return blog;
	}

	/**
	 * Método que seta o blog referente à postagem.
	 * 
	 * @param blog
	 *            Blog - Uma instância de blog
	 * @author nathalia
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Método que verifica se a postagem que foi passada como parâmetro é igual
	 * a esta postagem.
	 * 
	 * @param postagem
	 *            Postagem
	 * @return boolean equals - retorna uma condição verdadeira se o código da
	 *         postagem for igual ao código da classe Postagem. Senão, uma
	 *         condição falsa.
	 * @author nathalia
	 */
	public boolean equals(Postagem postagem) {

		if (postagem.getCodigo() == (this.codigo))
			return true;
		return false;
	}
}
