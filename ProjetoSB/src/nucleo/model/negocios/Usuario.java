package nucleo.model.negocios;

import java.sql.Date;
import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer {

	private String login;
	private String nome;
	private String senha;
	private char sexo;
	private String email;
	private Date dataNascimento;
	private String endereco;
	private String interesses;
	private String quemSouEu;
	private String filmes;
	private String musicas;
	private String livros;

	public Usuario() {
	
	}

	/**
	 * Retorna o login do usuário.
	 * @return login do usuário
	 * @author Warley Vital
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Seta login do usuário.
	 * @param login a ser setado
	 * @author Warley Vital
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Retorna o nome do usuário.
	 * @return nome do usuário
	 * @author Warley Vital
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o nome do usuário. 
	 * @param nome a ser setado
	 * @author Warley Vital
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a senha do usuário.
	 * @return senha do usuário
	 * @author Warley Vital
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Seta a senha do usuário.
	 * @param senha a ser setada
	 * @author Warley Vital
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Retorna o sexo do usuário.
	 * @return sexo do usuário. M se masculino, F se feminino. 
	 * @author Warley Vital
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * @param interesses
	 */
	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}

	/**
	 * @return
	 */
	public String getQuemSouEu() {
		return quemSouEu;
	}

	/**
	 * @param quemSouEu
	 */
	public void setQuemSouEu(String quemSouEu) {
		this.quemSouEu = quemSouEu;
	}

	/**
	 * @return
	 */
	public String getFilmes() {
		return filmes;
	}

	/**
	 * @param filmes
	 */
	public void setFilmes(String filmes) {
		this.filmes = filmes;
	}

	/**
	 * @return
	 */
	public String getMusicas() {
		return musicas;
	}

	/**
	 * @param musicas
	 */
	public void setMusicas(String musicas) {
		this.musicas = musicas;
	}

	/**
	 * @return
	 */
	public String getLivros() {
		return livros;
	}

	/**
	 * @param livros
	 */
	public void setLivros(String livros) {
		this.livros = livros;
	}

	/**
	 * @param blog
	 */
	public void criarAssinatura(Blog blog) {
		blog.addObserver(this);
	}

	/**
	 * @param blog
	 */
	public void excluirAssinatura(Blog blog) {
		blog.deleteObserver(this);
	}

	/**
	 * @param login
	 * @param senha
	 * @return
	 */
	public boolean login(String login, String senha) {
		// ...
		return true;
	}

	/**
	 * 
	 */
	public void logout() {
		// ...
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// aqui avisa o usuario das notificaoes
	}

	/**
	 * @param usuario
	 * @return
	 */
	public boolean equals(Usuario usuario) {
//		int iguais = 0;
		if (usuario.getLogin().equals(this.login)
				&& (usuario.getSenha() == null ? (usuario.getSenha() == this.senha) : usuario.getSenha().equals(this.senha))
				&& (usuario.getNome() == null ? (usuario.getNome() == this.nome) : usuario.getNome().equals(this.nome))
				&& usuario.getSexo() == this.sexo
				&& (usuario.getDataNascimento() == null ? (usuario.getDataNascimento() == this.dataNascimento) : usuario.getDataNascimento().equals(this.dataNascimento))
				&& (usuario.getEmail() == null ? (usuario.getDataNascimento() == this.dataNascimento) : usuario.getEmail().equals(this.email))
				&& (usuario.getQuemSouEu() == null ? (usuario.getQuemSouEu() == this.quemSouEu) : usuario.getQuemSouEu().equals(this.quemSouEu))
				&& (usuario.getInteresses() == null ? (usuario.getInteresses() == this.interesses) :usuario.getInteresses().equals(this.interesses))
				&& (usuario.getEndereco() == null ? (usuario.getEndereco() == this.endereco) : usuario.getEndereco().equals(this.endereco))
				&& (usuario.getFilmes() == null ? (usuario.getFilmes() == this.filmes) : usuario.getFilmes().equals(this.filmes))
				&& (usuario.getLivros() == null ? (usuario.getLivros() == this.livros) : usuario.getLivros().equals(this.livros))
				&& (usuario.getMusicas() == null ? (usuario.getMusicas() == this.livros) : usuario.getMusicas().equals(this.musicas))) {
			return true;
		}
		return false;
	}

}
