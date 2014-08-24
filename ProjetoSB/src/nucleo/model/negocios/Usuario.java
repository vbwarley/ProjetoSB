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
	private char sexo;
	private String email;
	private Date dataNascimento;
	private String endereco;
	private String interesses;
	private String quemSouEu;
	private String filmes;
	private String musicas;
	private String livros;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
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

	public String getLivros() {
		return livros;
	}

	public void setLivros(String livros) {
		this.livros = livros;
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

	public boolean equals(Usuario usuario) {
		int iguais = 0;
		if (usuario.getLogin().equals(this.login)
				&& usuario.getSenha().equals(this.senha)
				&& usuario.getNome().equals(this.nome)
				&& usuario.getSexo() == this.sexo
				&& usuario.getDataNascimento().equals(this.dataNascimento)
				&& usuario.getEmail().equals(this.email)
				&& usuario.getQuemSouEu().equals(this.quemSouEu)
				&& usuario.getInteresses().equals(this.interesses)
				&& usuario.getEndereco().equals(this.endereco)
				&& usuario.getFilmes().equals(this.filmes)
				&& usuario.getLivros().equals(this.livros)
				&& usuario.getMusicas().equals(this.musicas)) {
			for (Blog blogP : usuario.getAssinatura())
				for (Blog blog : this.getAssinatura())
					if (blogP.getCodigo() == blog.getCodigo())
						iguais++;
			if (iguais == this.getAssinatura().size() && this.getAssinatura().size() == usuario.getAssinatura().size())
				return true;
		}
		return false;
	}

}
