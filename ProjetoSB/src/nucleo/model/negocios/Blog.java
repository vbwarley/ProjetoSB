package nucleo.model.negocios;

import java.util.ArrayList;

public class Blog implements Subject {

	private int codigo;
	private String titulo;
	private String descricao;
	private String imagemFundo;
	private boolean autorizaComentario;
	private boolean autorizaComentarioAnonimo;
	private Usuario usuario;

	private ArrayList<Observer> observers;


	/**
	 * Construtor da classe Blog
	 * 
	 * @author raiane
	 */
	public Blog() {
		observers = new ArrayList<Observer>();
	}

	/**
	 * Método que recupera o código do Blog
	 * 
	 * @return Int codigo - retorna o codigo do Blog
	 * @author raiane
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método que atribui um código ao Blog
	 * 
	 * @param codigo
	 *            Int - parâmetro passado ao método setCodigo
	 * @author raiane
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que recupera o título do Blog
	 * 
	 * @return String titulo - retorna o titulo do blog
	 * @author raiane
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * O método setTitulo é o que será usado para atribuir um valor para título.
	 * 
	 * @param titulo
	 *            String - parâmetro passado ao método setTitulo
	 * @author raiane
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * O método getDescricao serve para recuperar uma descrição.
	 * 
	 * @return String descricao - retorna a descrição do blog
	 * @author raiane
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * O método setDescricao é o que será usado para atribuir uma descrição ao
	 * blog
	 * 
	 * @param descricao
	 *            String - parâmetro passado ao método setDescricao
	 * @author raiane
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return String ImagemFundo - retorna a imagem de fundo do blog
	 * @author raiane
	 */
	public String getImagemFundo() {
		return imagemFundo;
	}

	/**
	 * @param
	 */
	public void setImagemFundo(String imagemFundo) {
		this.imagemFundo = imagemFundo;
	}

	/**
	 * Método que retorna uma autorização de comentário ao assinante
	 * 
	 * @return Boolean isAutorizaComentario - retorna uma autorização do
	 *         comentário
	 * @author raiane
	 */
	public boolean isAutorizaComentario() {
		return autorizaComentario;
	}

	/**
	 * Método que atribui um coméntario ao blog
	 * 
	 * @param autorizaComentario
	 *            Boolean - parâmetro passado ao método setAutorizaComentario
	 * @author raiane
	 */
	public void setAutorizaComentario(boolean autorizaComentario) {
		this.autorizaComentario = autorizaComentario;
	}

	/**
	 * Método que retorna uma autorização de comentário anônimo ao assinante
	 * 
	 * @return Boolean isAutorizaComentarioAnonimo - retorna uma autorização
	 *         para comentário anônimo.
	 * @author raiane
	 */
	public boolean isAutorizaComentarioAnonimo() {
		return autorizaComentarioAnonimo;
	}

	/**
	 * O método setAutorizaComentarioAnonimo atribui comentário anônimo ao Blog
	 * 
	 * @param Boolean
	 *            autorizaComentarioAnonimo - parâmetro passado ao método
	 *            setAutorizaComentarioAnonimo
	 * @author raiane
	 */
	public void setAutorizaComentarioAnonimo(boolean autorizaComentarioAnonimo) {
		this.autorizaComentarioAnonimo = autorizaComentarioAnonimo;
	}

	/**
	 * Método que retorna um usuário que assina o blog
	 * 
	 * @return Usuario Usuario - retorna um usuário assinante do blog
	 * @author raiane
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * O método atribui um usuario
	 * 
	 * @param usuario
	 *            Usuario - parâmetro passado ao método setUsuario
	 * @author raiane
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	@Override
	public void unregister(Observer deleteObserver) {
		observers.remove(deleteObserver);
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
	
	/**
	 * O método verifica se o blog que foi passado como parâmetro é igual a este
	 * blog.
	 * 
	 * @param blog
	 *            Blog - parâmetro passado ao método equals
	 * @return Boolean equals - retorna uma condição verdadeira se todos os
	 *         campos para a criação de um blog estiver preenchidos
	 *         corretamente, casso contrário retorna um false
	 * @author raiane
	 */
	public boolean equals(Blog blog) {

		if (blog.getCodigo() == this.codigo)
			return true;
		return false;
	}
}
