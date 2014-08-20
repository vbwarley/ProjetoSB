package nucleo.model.negocios;

import java.sql.Date;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Usuario implements Observer {

	private String login;
	private String nome;
	private String senha;
	private String email;
	private Date dataNascimento;
	private String endereco;
	private String interesses;
	private String quemSouEu;
	private String filmes;
	private String musicas;
	private String livro;
	private Set<Blog> assinatura;

	public Usuario() {
		assinatura = new HashSet<>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInteresses() {
		return interesses;
	}

	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}

	public String getQuemSouEu() {
		return quemSouEu;
	}

	public void setQuemSouEu(String quemSouEu) {
		this.quemSouEu = quemSouEu;
	}

	public String getFilmes() {
		return filmes;
	}

	public void setFilmes(String filmes) {
		this.filmes = filmes;
	}

	public String getMusicas() {
		return musicas;
	}

	public void setMusicas(String musicas) {
		this.musicas = musicas;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public Set<Blog> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Set<Blog> assinatura) {
		this.assinatura = assinatura;
	}

	public void criarAssinatura(Blog blog) {
		this.assinatura.add(blog);
		blog.addObserver(this);
	}
	
	public void excluirAssinatura(Blog blog) {
		this.assinatura.remove(blog);
		blog.deleteObserver(this);
	}
	
	public boolean login(String login, String senha) {
		// ...
		return true;
	}
	
	public void logout() {
		// ...
	}
	@Override
	public void update(Observable o, Object arg) {
		// aqui avisa o usuario das notificaoes
	}

}
