package nucleo.model.negocios;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta classe descreve os atributos e ações de um usuário.
 * 
 * @author Warley Vital
 */
public class Usuario implements Observer {

	private String login;
	private String nome;
	private String senha;
	private String sexo;
	private String email;
	private Date dataNascimento;
	private String endereco;
	private String interesses;
	private String quemSouEu;
	private String filmes;
	private String musicas;
	private String livros;
	private Set<Blog> blogsPossuidos;

	private Subject state;

	/**
	 * Construtor da classe.
	 */
	public Usuario() {
		this.blogsPossuidos = new HashSet<Blog>();
	}

	/**
	 * Retorna o login do usuário.
	 * 
	 * @return login do usuário
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Seta login do usuário.
	 * 
	 * @param login
	 *            a ser setado, não nulo
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Retorna o nome do usuário.
	 * 
	 * @return nome do usuário
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o nome do usuário.
	 * 
	 * @param nome
	 *            a ser setado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a senha do usuário.
	 * 
	 * @return senha do usuário
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Seta a senha do usuário.
	 * 
	 * @param senha
	 *            a ser setada, não nula
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Retorna o sexo do usuário.
	 * 
	 * @return M se masculino, F se feminino
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Seta o sexo do usuário.
	 * 
	 * @param sexo
	 *            a ser setado: M se masculino, F se feminino
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Retorna o email do usuário.
	 * 
	 * @return email no formato example@example.com[.br]
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Seta o email do usuário
	 * 
	 * @param email
	 *            a ser setado no formato example@example.com[.br]
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna a data de nascimento do usuário.
	 * 
	 * @return data no formato de {@link Date}
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Seta a data de nascimento do usuário.
	 * 
	 * @param dataNascimento
	 *            a ser setada
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o endereço do usuário.
	 * 
	 * @return endereco do usuário
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Seta o endereço do usuário.
	 * 
	 * @param endereco
	 *            a ser setado
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Retorna os interesses do usuário.
	 * 
	 * @return interesses do usuário
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * Seta os interesses do usuário.
	 * 
	 * @param interesses
	 *            a serem setados.
	 */
	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}

	/**
	 * Retorna o quem sou eu do usuário.
	 * 
	 * @return quem sou eu do usuário
	 */
	public String getQuemSouEu() {
		return quemSouEu;
	}

	/**
	 * Seta o quem sou eu do usuário.
	 * 
	 * @param quemSouEu
	 *            do usuário
	 */
	public void setQuemSouEu(String quemSouEu) {
		this.quemSouEu = quemSouEu;
	}

	/**
	 * Retorna os filmes favoritos do usuário.
	 * 
	 * @return filmes do usuário
	 */
	public String getFilmes() {
		return filmes;
	}

	/**
	 * Seta os filmes favoritos do usuário.
	 * 
	 * @param filmes
	 *            do usuário
	 */
	public void setFilmes(String filmes) {
		this.filmes = filmes;
	}

	/**
	 * Retorna as músicas favoritas do usuário.
	 * 
	 * @return musicas favoritas do usuario
	 */
	public String getMusicas() {
		return musicas;
	}

	/**
	 * Seta as músicas favoritas do usuário.
	 * 
	 * @param musicas
	 *            do usuário
	 */
	public void setMusicas(String musicas) {
		this.musicas = musicas;
	}

	/**
	 * Retorna os livros favoritos do usuário.
	 * 
	 * @return livros do usuário
	 */
	public String getLivros() {
		return livros;
	}

	/**
	 * Seta os livros favoritos do usuário.
	 * 
	 * @param livros
	 *            do usuário
	 */
	public void setLivros(String livros) {
		this.livros = livros;
	}

	/**
	 * Cria uma assinatura de um blog.
	 * 
	 * @param blog
	 *            a ser assinado
	 */
	public Assinatura criarAssinatura(Blog blog) {
		blog.register(this);

		Assinatura a = new Assinatura();
		a.setBlog(blog);
		a.setUsuario(this);

		return a;
	}

	/**
	 * Exclui a assinatura de um blog.
	 * 
	 * @param blog
	 *            a ser excluido das assinaturas
	 */
	public Assinatura excluirAssinatura(Blog blog) {
		blog.unregister(this);

		Assinatura a = new Assinatura();
		a.setBlog(blog);
		a.setUsuario(this);

		return a;
	}

	@Override
	public void update(Subject state) {
		this.state = state;
		
		// aqui será enviado uma notificação pro usuário, em view
	}

	public Set<Blog> getBlogsPossuidos() {
		return blogsPossuidos;
	}

	/**
	 * Este método verifica se o usuário passado como parâmetro é igual a este
	 * objeto.
	 * 
	 * @param usuario
	 *            a ser comparadado
	 * @return true se o usuário é igual, false caso contrário
	 */
	public boolean equals(Usuario usuario) {
		if (usuario.getLogin().equals(this.login))
			return true;
		return false;
	}

}
