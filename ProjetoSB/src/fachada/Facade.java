package fachada;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nucleo.model.negocios.Assinatura;
import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.negocios.Midia;
import nucleo.model.negocios.MidiaPostagem;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.negocios.Usuario;
import nucleo.model.negocios.busca.BuscaBlogDescricao;
import nucleo.model.negocios.busca.BuscaBlogNome;
import nucleo.model.negocios.busca.BuscaStrategy;
import nucleo.model.negocios.busca.BuscaUsuarioEmail;
import nucleo.model.negocios.busca.BuscaUsuarioLogin;
import nucleo.model.negocios.busca.BuscaUsuarioNome;
import nucleo.model.negocios.busca.BuscaUsuarioPorIntervaloData;
import nucleo.model.persistencia.dao.DAOAssinatura;
import nucleo.model.persistencia.dao.DAOBlog;
import nucleo.model.persistencia.dao.DAOComentario;
import nucleo.model.persistencia.dao.DAOMidia;
import nucleo.model.persistencia.dao.DAOPostagem;
import nucleo.model.persistencia.dao.DAOUsuario;
import nucleo.model.persistencia.factory.DAOFactory;
import nucleo.model.persistencia.jdbc.JDBCDAO;
import nucleo.model.persistencia.jdbc.JDBCDAOMidia;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import sessao.GerenciadorSessao;
import easyaccept.EasyAccept;

public class Facade {

	private DAOFactory factory = DAOFactory.getDAOFactory();
	private DAOUsuario daoUsuario = factory.getDAOUsuario();
	private DAOBlog daoBlog = factory.getDAOBlog();
	private DAOMidia daoMidia = factory.getDAOMidia();
	private DAOPostagem daoPostagem = factory.getDAOPostagem();
	private DAOComentario daoComentario = factory.getDAOComentario();
	private DAOAssinatura daoAssinatura = factory.getDAOAssinatura();

	private Pattern padrao;
	private Matcher verificaPadrao;

	private static final String PADRAO_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static void main(String[] args) {
		args = new String[] { "fachada.Facade", "testes-aceitacao/us01.txt", "testes-aceitacao/us02.txt",
				"testes-aceitacao/us03.txt", "testes-aceitacao/us04.txt", "testes-aceitacao/us05.txt",
				"testes-aceitacao/us06.txt", "testes-aceitacao/us07.txt", "testes-aceitacao/us08.txt",
				"testes-aceitacao/us09.txt", "testes-aceitacao/us10.txt", "testes-aceitacao/us11.txt",
				"testes-aceitacao/us12.txt", "testes-aceitacao/us13.txt", "testes-aceitacao/us14.txt" };
		EasyAccept.main(args);
	}

	public void createProfile(String login, String senha, String nome_exibicao, String email, String sexo,
			String data_nasc, String endereco, String interesses, String quem_sou_eu, String filmes, String musicas,
			String livros) throws Exception {

		if (login == null || login.equals("") || login.length() < 5 || login.length() > 15) {
			throw new Exception("Campo invÃ¡lido: login");
		}

		if (senha == null || senha.equals("") || senha.length() < 8 || senha.length() > 16) {
			throw new Exception("Campo invÃ¡lido: senha");
		}

		if (email == null) {
			throw new Exception("Campo invÃ¡lido: email");
		} else {
			padrao = Pattern.compile(PADRAO_EMAIL);
			verificaPadrao = padrao.matcher(email);

			if (!verificaPadrao.matches()) {
				throw new Exception("Campo invÃ¡lido: email");
			}

		}

		if (sexo == null || (!sexo.equals("Feminino") && !sexo.equals("Masculino")) || sexo.equals(""))
			throw new Exception("Campo invÃ¡lido: sexo");

		Calendar data = Calendar.getInstance();

		try {
			SimpleDateFormat sData = new SimpleDateFormat("dd/MM/yyyy");
			data.setTime(sData.parse(data_nasc));
		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: data de nascimento");
		}

		int idade = Calendar.getInstance().get(Calendar.YEAR) - data.get(Calendar.YEAR);

		if (idade < 18 || idade > 100) {
			throw new Exception("Campo invÃ¡lido: data de nascimento");
		}

		if (daoUsuario.consultar(login) != null) {
			throw new Exception("Login existente");
		}

		Usuario usuario = new Usuario();
		usuario.setDataNascimento(new Date(data.getTimeInMillis()));
		usuario.setEmail(email);
		usuario.setEndereco(endereco);
		usuario.setFilmes(filmes);
		usuario.setInteresses(interesses);
		usuario.setLivros(livros);
		usuario.setLogin(login);
		usuario.setMusicas(musicas);
		usuario.setNome(nome_exibicao);
		usuario.setQuemSouEu(quem_sou_eu);
		usuario.setSenha(senha);
		usuario.setSexo(sexo);

		daoUsuario.criar(usuario);

	}

	public Integer doLogin(String login, String senha) throws Exception {

		if (GerenciadorSessao.getInstance().verificaSessaoAtiva(login)) {
			throw new Exception("UsuÃ¡rio jÃ¡ logado");
		} else if (daoUsuario.validacaoLogin(login, senha)) {
			Integer id = GerenciadorSessao.getInstance().login(login);
			return id;
		} else {
			throw new Exception("Login ou senha invÃ¡lido");
		}
	}

	public boolean isUserLogged(String login) throws Exception {

		if (daoUsuario.consultar(login) == null) {
			throw new Exception("UsuÃ¡rio inexistente");
		} else {

			return GerenciadorSessao.getInstance().verificaSessaoAtiva(login);
		}
	}

	public void logoff(Integer sessionId) throws Exception {
		boolean resultado = GerenciadorSessao.getInstance().logoff(sessionId);
		if (!resultado) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}
	}

	public void changeProfileInformation(String sessionId, String atributo, String valor) throws Exception {

		if (sessionId == null || sessionId.equals("")) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		try {
			Integer.parseInt(sessionId);
		} catch (NumberFormatException e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String login = GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId));
		Usuario usuario = null;

		if (GerenciadorSessao.getInstance().verificaSessaoAtiva(login)) {
			usuario = daoUsuario.consultar(login);

			if (atributo == null)
				throw new Exception("Atributo InvÃ¡lido");
			else if (atributo.equals("senha"))
				if (valor == null || valor.equals("") || valor.length() < 8 || valor.length() > 16)
					throw new Exception("Campo invÃ¡lido: senha");
				else {
					usuario.setSenha(valor);
					daoUsuario.alterar(usuario);
				}
			else if (atributo.equals("sexo"))
				if (valor == null || (!valor.equals("Feminino") && !valor.equals("Masculino")))
					throw new Exception("Campo invÃ¡lido: sexo");
				else {
					usuario.setSexo(valor);
					daoUsuario.alterar(usuario);
				}
			else if (atributo.equals("email")) {
				if (valor == null)
					throw new Exception("Campo invÃ¡lido: email");

				padrao = Pattern.compile(PADRAO_EMAIL);
				verificaPadrao = padrao.matcher(valor);

				if (!verificaPadrao.matches()) {
					throw new Exception("Campo invÃ¡lido: email");
				}

				for (Usuario u : daoUsuario.getList())
					if (u.getEmail().equals(valor))
						throw new Exception("Email existente");

				usuario.setEmail(valor);
				daoUsuario.alterar(usuario);

			} else if (atributo.equals("nome_exibicao")) {

				usuario.setNome(valor);
				daoUsuario.alterar(usuario);

			} else if (atributo.equals("dataNasc")) {

				if (valor == null || valor.equals("")) {
					throw new Exception("Campo invÃ¡lido: data de nascimento");
				}

				Calendar data = null;

				try {
					data = Calendar.getInstance();
					SimpleDateFormat sData = new SimpleDateFormat("dd/MM/yyyy");
					sData.setLenient(false);
					data.setTime(sData.parse(valor));

					if (data.get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR)
							|| (Calendar.getInstance().get(Calendar.YEAR) - data.get(Calendar.YEAR)) > 100) {
						throw new Exception("Campo invÃ¡lido: data de nascimento");
					}
				} catch (Exception e) {
					throw new Exception("Campo invÃ¡lido: data de nascimento");
				}

				usuario.setDataNascimento(new Date(data.getTimeInMillis()));
				daoUsuario.alterar(usuario);

			} else if (atributo.equals("endereco")) {
				usuario.setEndereco(valor);
				daoUsuario.alterar(usuario);
			} else if (atributo.equals("interesses")) {
				usuario.setInteresses(valor);
				daoUsuario.alterar(usuario);
			} else if (atributo.equals("quem_sou_eu")) {
				usuario.setQuemSouEu(valor);
				daoUsuario.alterar(usuario);
			} else if (atributo.equals("filmes")) {
				usuario.setFilmes(valor);
				daoUsuario.alterar(usuario);
			} else if (atributo.equals("musicas")) {
				usuario.setMusicas(valor);
				daoUsuario.alterar(usuario);
			} else if (atributo.equals("livros")) {
				usuario.setLivros(valor);
				daoUsuario.alterar(usuario);
			} else {
				throw new Exception("Atributo InvÃ¡lido");
			}

		} else {
			throw new Exception("SessÃ£o invÃ¡lida");
		}
	}

	public void resetDatabase() {
		GerenciadorSessao.getInstance().resetSessions();
		JDBCDAO.resetarBD();
	}

	public String getProfileInformation(String login, String atributo) throws Exception {
		Usuario usuario = daoUsuario.consultar(login);

		if (atributo != null) {
			if (atributo.equals("login"))
				return usuario.getLogin();
			else if (atributo.equals("nome_exibicao")) {
				if (usuario.getNome() != null) {
					return usuario.getNome();
				} else if (usuario.getNome() == null || usuario.getNome().equals(""))
					return usuario.getLogin();

			} else if (atributo.equals("email")) {
				return usuario.getEmail();
			} else if (atributo.equals("sexo")) {
				return usuario.getSexo();
			} else if (atributo.equals("endereco")) {
				return usuario.getEndereco();
			} else if (atributo.equals("interesses")) {
				return usuario.getInteresses();
			} else if (atributo.equals("quem_sou_eu")) {
				return usuario.getQuemSouEu();
			} else if (atributo.equals("filmes")) {
				return usuario.getFilmes();
			} else if (atributo.equals("musicas")) {
				return usuario.getMusicas();
			} else if (atributo.equals("livros")) {
				return usuario.getLivros();
			} else if (atributo.equals("data_nasc")) {
				SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
				String data = s.format(usuario.getDataNascimento());
				return data;
			}
		}

		throw new Exception("Atributo InvÃ¡lido");
	}

	public String getProfileInformationBySessionId(Integer sessionId, String atributo) throws Exception {
		Usuario usuario = null;

		if (GerenciadorSessao.getInstance().getLoginById(sessionId) != null) {
			String login = GerenciadorSessao.getInstance().getLoginById(sessionId);
			usuario = daoUsuario.consultar(login);

			if (usuario == null)
				throw new Exception("UsuÃ¡rio invÃ¡lido");
		} else
			throw new Exception("SessÃ£o invÃ¡ilida");

		if (atributo != null) {
			if (atributo.equals("login"))
				return usuario.getLogin();
			else if (atributo.equals("nome_exibicao")) {
				if (usuario.getNome() != null) {
					return usuario.getNome();
				} else if (usuario.getNome() == null || usuario.getNome().equals(""))
					return usuario.getLogin();

			} else if (atributo.equals("email")) {
				return usuario.getEmail();
			} else if (atributo.equals("sexo")) {
				return usuario.getSexo();
			} else if (atributo.equals("endereco")) {
				return usuario.getEndereco();
			} else if (atributo.equals("interesses")) {
				return usuario.getInteresses();
			} else if (atributo.equals("quem_sou_eu")) {
				return usuario.getQuemSouEu();
			} else if (atributo.equals("filmes")) {
				return usuario.getFilmes();
			} else if (atributo.equals("musicas")) {
				return usuario.getMusicas();
			} else if (atributo.equals("livros")) {
				return usuario.getLivros();
			} else if (atributo.equals("data_nasc")) {
				SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
				String data = s.format(usuario.getDataNascimento());
				return data;
			}
		}

		throw new Exception("Atributo InvÃ¡lido");

	}

	public int createBlog(String sessionId, String titulo, String descricao, String background, String autz_comment,
			String autz_comment_annon) throws Exception {

		int id;

		try {
			id = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		Usuario usuario = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(id));

		if (usuario.getBlogsPossuidos().size() == 3)
			throw new Exception("NÃºmero mÃ¡ximo de blogs por usuÃ¡rio excedido");

		boolean autorizaComentario;

		if (autz_comment != null) {
			if (autz_comment.equals("true"))
				autorizaComentario = true;
			else if (autz_comment.equals("false"))
				autorizaComentario = false;
			else
				throw new Exception("Campo invÃ¡lido: autorizaÃ§Ã£o de comentÃ¡rios");
		} else
			throw new Exception("Campo invÃ¡lido: autorizaÃ§Ã£o de comentÃ¡rios");

		boolean autorizaAnonimo;

		if (autz_comment_annon != null) {
			if (autz_comment_annon.equals("true"))
				autorizaAnonimo = true;
			else if (autz_comment_annon.equals("false"))
				autorizaAnonimo = false;
			else
				throw new Exception("Campo invÃ¡lido: autorizaÃ§Ã£o de comentÃ¡rios anÃ´nimos");
		} else
			throw new Exception("Campo invÃ¡lido: autorizaÃ§Ã£o de comentÃ¡rios anÃ´nimos");

		if (titulo == null || titulo.equals(""))
			throw new Exception("Campo invÃ¡lido: tÃ­tulo");

		if (background == null || background.equals(""))
			throw new Exception("Campo invÃ¡lido: background");

		if (autorizaComentario == false && autorizaAnonimo == true)
			throw new Exception("Campo invÃ¡lido: comentÃ¡rios devem ser habilitados");

		Blog blog = new Blog();
		blog.setUsuario(usuario);
		blog.setTitulo(titulo);
		blog.setDescricao(descricao);
		blog.setImagemFundo(background);
		blog.setAutorizaComentario(autorizaComentario);
		blog.setAutorizaComentarioAnonimo(autorizaAnonimo);

		daoBlog.criar(blog);
		return daoBlog.getMaxId();

	}

	public String getBlogInformation(int id, String atributo) throws Exception {

		Blog blog = daoBlog.consultar(id);

		if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo invÃ¡lido");
		}

		if (atributo.equals("descricao")) {
			return blog.getDescricao();
		} else if (atributo.equals("dono")) {
			return blog.getUsuario().getLogin();
		} else if (atributo.equals("titulo")) {
			return blog.getTitulo();
		} else if (atributo.equals("background")) {
			return blog.getImagemFundo();
		} else if (atributo.equals("autz_comment")) {
			return Boolean.toString(blog.isAutorizaComentario());
		} else if (atributo.equals("autz_comment_annon")) {
			return Boolean.toString(blog.isAutorizaComentarioAnonimo());
		} else {
			throw new Exception("Atributo invÃ¡lido");
		}
	}

	public void changeBlogInformation(String sessionId, String id, String atributo, String valor) throws Exception {

		int idBlog;
		int idSessao;

		try {
			idBlog = Integer.parseInt(id);
		} catch (Exception e) {
			throw new Exception("Blog invÃ¡lido");
		}
		try {
			idSessao = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String login = GerenciadorSessao.getInstance().getLoginById(idSessao);
		Blog blog = daoBlog.consultar(idBlog);

		if (!blog.getUsuario().getLogin().equals(login)) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}
		if (atributo == null) {
			throw new Exception("Atributo invÃ¡lido");
		}

		if (atributo.equals("descricao")) {
			blog.setDescricao(valor);
			daoBlog.alterar(blog);
		} else if (atributo.equals("titulo")) {
			if (valor == null || valor.equals("") || valor.replaceAll(" ", "").equals("")) {
				throw new Exception("Campo invÃ¡lido: tÃ­tulo");
			}

			blog.setTitulo(valor);
			daoBlog.alterar(blog);
		} else if (atributo.equals("background")) {
			if (valor == null || valor.equals("") || valor.replaceAll(" ", "").equals("")) {
				throw new Exception("Campo invÃ¡lido: background");
			}

			blog.setImagemFundo(valor);
			daoBlog.alterar(blog);
		} else if (atributo.equals("autz_comment")) {
			if (Boolean.parseBoolean(valor) == false) {
				blog.setAutorizaComentario(false);
				blog.setAutorizaComentarioAnonimo(false);
			} else if (Boolean.parseBoolean(valor) == true) {
				blog.setAutorizaComentario(true);
			}

			daoBlog.alterar(blog);
		} else if (atributo.equals("autz_comment_annon")) {
			if (Boolean.parseBoolean(valor) == false) {
				blog.setAutorizaComentarioAnonimo(false);
			}
			if (Boolean.parseBoolean(valor) == true) {
				if (blog.isAutorizaComentario() == false) {
					throw new Exception("ComentÃƒÂ¡rios nÃƒÂ£o sÃƒÂ£o permitidos");
				}
			}

			daoBlog.alterar(blog);
		} else {
			throw new Exception("Atributo invÃ¡lido");
		}

	}

	public String todaysDate() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(System.currentTimeMillis());
		String data = sdf.format(date);
		return data;
	}

	public int createPost(String sessionId, String blogId, String titulo, String texto) throws Exception {

		if (sessionId == null || sessionId.equals(""))
			throw new Exception("SessÃ£o invÃ¡lida");

		int idSessao;

		try {
			idSessao = Integer.parseInt(sessionId);
		} catch (NumberFormatException e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String login = GerenciadorSessao.getInstance().getLoginById(idSessao);

		Usuario usuario = daoUsuario.consultar(login);

		if (blogId == null || blogId.equals(""))
			throw new Exception("Blog invÃ¡lido");

		int idBlog;

		try {
			idBlog = Integer.parseInt(blogId);
		} catch (NumberFormatException e) {
			throw new Exception("Blog invÃ¡lido");
		}

		if (titulo == null || titulo.equals(""))
			throw new Exception("TÃ­tulo obrigatÃ³rio");

		Blog blog = daoBlog.consultar(idBlog);

		boolean existe = false;

		for (Blog b : usuario.getBlogsPossuidos())
			if (b.getCodigo() == blog.getCodigo())
				existe = true;

		if (!existe)
			throw new Exception("SessÃ£o invÃ¡lida");

		Postagem p = new Postagem();
		p.setTitulo(titulo);
		p.setConteudo(texto);
		p.setBlog(blog);
		p.setData(new Date(System.currentTimeMillis()));

		daoPostagem.criar(p);
		
		p.getBlog().notifyObserver();

		return daoPostagem.getMaxId();
	}

	public int attachSound(String sessidonId, String postId, String descricao, String dado) throws Exception {

		if (dado == null || dado.equals(""))
			throw new Exception("Campo invÃ¡lido: arquivo de audio");

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		if (descricao == null)
			descricao = "";

		MidiaPostagem midiaP = new MidiaPostagem();
		midiaP.setNomeArquivo(dado);
		midiaP.setTipo(TipoMidia.AUDIO);
		midiaP.setPostagem(post);
		midiaP.setDescricaoArquivo(descricao);

		daoMidia.criar(midiaP);
		return daoMidia.getMaxId();

	}

	public int attachMovie(String sessionId, String postId, String descricao, String dado) throws Exception {

		if (dado == null || dado.equals(""))
			throw new Exception("Campo invÃ¡lido: arquivo de vÃ­deo");

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (NumberFormatException e) {
			throw new Exception("Post invÃ¡lido");
		}
		if (descricao == null) {
			descricao = "";
		}

		MidiaPostagem midiaP = new MidiaPostagem();
		midiaP.setNomeArquivo(dado);
		midiaP.setTipo(TipoMidia.VIDEO);
		midiaP.setPostagem(post);
		midiaP.setDescricaoArquivo(descricao);

		daoMidia.criar(midiaP);
		return daoMidia.getMaxId();
	}

	public int attachPicture(String sessionId, String postId, String descricao, String dado) throws Exception {

		if (dado == null || dado.equals(""))
			throw new Exception("Dado invÃ¡lido");

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (NumberFormatException e) {
			throw new Exception("Post invÃ¡lido");
		}
		if (descricao == null) {
			descricao = "";
		}

		MidiaPostagem midiaP = new MidiaPostagem();
		midiaP.setNomeArquivo(dado);
		midiaP.setTipo(TipoMidia.IMAGEM);
		midiaP.setPostagem(post);
		midiaP.setDescricaoArquivo(descricao);

		daoMidia.criar(midiaP);
		return daoMidia.getMaxId();
	}

	public String getPostInformation(int postId, String atributo) throws Exception {

		Postagem post = daoPostagem.consultar(postId);

		if (post == null) {
			throw new Exception("Post invÃ¡lido");
		}

		if (atributo.equals("titulo"))
			return post.getTitulo();
		else if (atributo.equals("texto"))
			return post.getConteudo();
		else if (atributo.equals("data_criacao"))
			return new SimpleDateFormat("dd/MM/yyyy").format(post.getData());

		return null;
	}

	public int getNumberOfSounds(String postId) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		List<Midia> listaMidia = daoMidia.getList();
		MidiaPostagem mP = null;

		int qtd = 0;

		for (Midia m : listaMidia)
			if (m.getTipo().equals(TipoMidia.AUDIO))
				if (m.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) m;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						qtd++;
				}
		return qtd;

	}

	public int getNumberOfMovies(String postId) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		List<Midia> listaMidia = daoMidia.getList();
		MidiaPostagem mP = null;

		int qtd = 0;

		for (Midia m : listaMidia)
			if (m.getTipo().equals(TipoMidia.VIDEO))
				if (m.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) m;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						qtd++;
				}

		return qtd;
	}

	public int getNumberOfPictures(String postId) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		List<Midia> listaMidia = daoMidia.getList();
		MidiaPostagem mP = null;

		int qtd = 0;

		for (Midia m : listaMidia)
			if (m.getTipo().equals(TipoMidia.IMAGEM))
				if (m.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) m;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						qtd++;
				}
		return qtd;
	}

	public int getSound(String postId, String index) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		int indexMidia;

		try {
			indexMidia = Integer.parseInt(index);
		} catch (Exception e) {
			throw new Exception("Index invÃ¡lido");
		}

		List<Midia> midias = daoMidia.getList();
		MidiaPostagem mP = null;

		List<Midia> audios = new ArrayList<Midia>();

		for (Midia m : midias) {
			if (m.getTipo().equals(TipoMidia.AUDIO)) {
				if (m.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) m;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						audios.add(m);
				}
			}
		}

		if (audios.size() <= indexMidia || audios.get(indexMidia) == null) {
			throw new Exception("Ã�ndice incorreto");
		}

		return audios.get(indexMidia).getCodigo();

	}

	public String getSoundDescription(String audioId) throws Exception {
		Midia sound = null;

		try {
			sound = daoMidia.consultar(Integer.parseInt(audioId));
		} catch (Exception e) {
			throw new Exception("ID de Ã�udio invÃ¡lido");
		}

		return sound.getDescricaoArquivo();

	}

	public String getSoundData(String audioId) throws Exception {

		Midia m = null;

		try {
			m = daoMidia.consultar(Integer.parseInt(audioId));
		} catch (Exception e) {
			throw new Exception("ID de Ã�udio invÃ¡lido");
		}

		return m.getNomeArquivo();

	}

	public int getMovie(String postId, String index) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		int indexMidia;

		try {
			indexMidia = Integer.parseInt(index);
		} catch (Exception e) {
			throw new Exception("Index invÃ¡lido");
		}

		List<Midia> videos = new ArrayList<Midia>();
		List<Midia> midias = new JDBCDAOMidia().getList();
		MidiaPostagem mP = new MidiaPostagem();

		for (Midia midia : midias)
			if (midia.getTipo().equals(TipoMidia.VIDEO))
				if (midia.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) midia;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						videos.add(midia);
				}

		if (videos.size() <= indexMidia || videos.get(indexMidia) == null)
			throw new Exception("Ã�ndice incorreto");

		return videos.get(indexMidia).getCodigo();
	}

	public String getMovieDescription(String videoId) throws Exception {
		Midia m = null;

		try {
			m = daoMidia.consultar(Integer.parseInt(videoId));
		} catch (Exception e) {
			throw new Exception("ID de vÃ­deo invÃ¡lido");
		}

		return m.getDescricaoArquivo();
	}

	public String getMovieData(String videoId) throws Exception {
		Midia m = null;

		try {
			m = daoMidia.consultar(Integer.parseInt(videoId));
		} catch (Exception e) {
			throw new Exception("ID de vÃ­deo invÃ¡lido");
		}

		return m.getNomeArquivo();
	}

	public int getPicture(String postId, String index) throws Exception {

		Postagem post = null;

		try {
			post = daoPostagem.consultar(Integer.parseInt(postId));
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		int indexMidia;

		try {
			indexMidia = Integer.parseInt(index);
		} catch (Exception e) {
			throw new Exception("Index invÃ¡lido");
		}

		List<Midia> imagens = new ArrayList<Midia>();
		List<Midia> midias = new JDBCDAOMidia().getList();
		MidiaPostagem mP = new MidiaPostagem();

		for (Midia midia : midias)
			if (midia.getTipo().equals(TipoMidia.IMAGEM))
				if (midia.getTipoEsp().equals(MidiaPostagem.class.getSimpleName())) {
					mP = (MidiaPostagem) midia;
					if (mP.getPostagem().getCodigo() == post.getCodigo())
						imagens.add(midia);
				}

		if (imagens.size() <= indexMidia || imagens.get(indexMidia) == null)
			throw new Exception("Ã�ndice incorreto");

		return imagens.get(indexMidia).getCodigo();
	}

	public String getPictureDescription(String imagemId) throws Exception {

		Midia m = null;

		try {
			m = daoMidia.consultar(Integer.parseInt(imagemId));
		} catch (Exception e) {
			throw new Exception("ID de vÃ­deo invÃ¡lido");
		}

		return m.getDescricaoArquivo();
	}

	public String getPictureData(String imagemId) throws Exception {

		Midia m = null;

		try {
			m = daoMidia.consultar(Integer.parseInt(imagemId));
		} catch (Exception e) {
			throw new Exception("ID de vÃ­deo invÃ¡lido");
		}

		return m.getNomeArquivo();
	}

	public int getNumberOfBlogsByLogin(String login) {
		Usuario u = daoUsuario.consultar(login);

		return u.getBlogsPossuidos().size();
	}

	public int getNumberOfBlogsBySessionId(String sessionId) {
		Usuario u = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));
		return u.getBlogsPossuidos().size();
	}

	public int getBlogBySessionId(String sessionId, String index) throws Exception {

		Usuario u = null;

		try {
			u = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));

		} catch (Exception e) {
			throw new Exception("ID invÃ¡lido");
		}

		int indexBlog;

		try {
			indexBlog = Integer.parseInt(index);
		} catch (Exception e) {
			throw new Exception("Index nÃ£o vai o tornar menos gordo");
		}

		List<Blog> blogs = new ArrayList<Blog>();
		blogs.addAll(u.getBlogsPossuidos());

		return blogs.get(indexBlog).getCodigo();
	}

	public int getNumberOfPosts(String blogId) throws Exception {
		Blog b = daoBlog.consultar(Integer.parseInt(blogId));

		if (b == null)
			throw new Exception("Blog invÃ¡lido");

		List<Postagem> postagens = new JDBCDAOPostagem().getList();
		List<Postagem> postsBlog = new ArrayList<Postagem>();

		for (Postagem postagem : postagens)
			if (postagem.getBlog().getCodigo() == Integer.parseInt(blogId))
				postsBlog.add(postagem);

		return postsBlog.size();

	}

	public int getPost(int blogId, int index) {
		Blog blog = daoBlog.consultar(blogId);

		List<Postagem> posts = new ArrayList<Postagem>();

		posts.addAll(daoPostagem.getPostagensPorBlog(blog));

		return posts.get(index).getCodigo();

	}

	public void changePostInformation(String sessionId, String postId, String atributo, String valor) throws Exception {
		int ssId;

		try {
			ssId = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		int idPost;

		try {
			idPost = Integer.parseInt(postId);
		} catch (Exception e) {
			throw new Exception("Post invÃ¡lido");
		}

		if (daoPostagem.consultar(idPost) == null)
			throw new Exception("Post invÃ¡lido");

		Postagem post = daoPostagem.consultar(idPost);

		Usuario u = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(ssId));

		List<Blog> blogs = new ArrayList<Blog>();
		blogs.addAll(u.getBlogsPossuidos());

		boolean existe = false;

		for (Blog blog : blogs)
			if (blog.getCodigo() == post.getBlog().getCodigo())
				existe = true;

		if (!existe)
			throw new Exception("SessÃ£o invÃ¡lida");

		if (atributo == null)
			throw new Exception("Atributo invÃ¡lido");

		if (atributo.equals("titulo")) {
			if (valor == null || valor.equals("")) {
				throw new Exception("TÃ­tulo obrigatÃ³rio");
			}
			post.setTitulo(valor);
			daoPostagem.alterar(post);
		} else if (atributo.equals("texto")) {
			post.setConteudo(valor);
			daoPostagem.alterar(post);
		} else {
			throw new Exception("Atributo invÃ¡lido");
		}
	}

	public void deleteMovie(String sessionId, int id) throws Exception {
		int ssId;

		try {
			ssId = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String userId = GerenciadorSessao.getInstance().getLoginById(ssId);
		Midia m = null;

		if (userId != null) {
			m = daoMidia.consultar(id);
			daoMidia.deletar(m);
		}
	}

	public void deleteSound(String sessionId, int id) throws Exception {
		int ssId;

		try {
			ssId = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String userId = GerenciadorSessao.getInstance().getLoginById(ssId);
		Midia m = null;

		if (userId != null) {
			m = daoMidia.consultar(id);
			daoMidia.deletar(m);
		}
	}

	public void deletePicture(String sessionId, int id) throws Exception {
		int ssId;

		try {
			ssId = Integer.parseInt(sessionId);
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		String userId = GerenciadorSessao.getInstance().getLoginById(ssId);
		Midia m = null;

		if (userId != null) {
			m = daoMidia.consultar(id);
			daoMidia.deletar(m);
		}
	}

	public int getBlogByLogin(String login, int index) {
		Usuario usuario = daoUsuario.consultar(login);

		List<Blog> listBlogs = new ArrayList<Blog>(usuario.getBlogsPossuidos());

		return listBlogs.get(index).getCodigo();
	}

	public int addComment(String sessionid, int postId, String texto) throws Exception {

		Usuario user = null;
		int ssId;
		try {
			ssId = Integer.parseInt(sessionid);
			user = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(ssId));
		} catch (Exception e) {
			throw new Exception("SessÃ£o invÃ¡lida");
		}

		Postagem post = daoPostagem.consultar(postId);

		ComentarioComposite cN = new ComentarioNormal();

		cN.setConteudo(texto);
		cN.setUsuario(user);
		cN.setPostagem(post);

		daoComentario.criar(cN);

		return daoComentario.getMaxId();

	}

	public int getNumberOfCommentsByPost(String postId) throws Exception {

		int numT = 0;

		if (postId == null || postId.equals(""))
			throw new Exception("Campo invÃ¡lido: postId");

		int postIdInt = 0;

		try {
			postIdInt = Integer.parseInt(postId);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Campo invÃ¡lido: postId");
		}

		List<ComentarioComposite> comments = daoComentario.getList();

		for (ComentarioComposite comentarioComposite : comments)
			if (comentarioComposite.getPostagem().getCodigo() == postIdInt
					&& comentarioComposite.getComentarioPai() == null)
				numT++;

		return numT;
	}

	public int getComment(int postId, int index) throws Exception {

		List<ComentarioComposite> listC = daoComentario.getList();
		List<ComentarioComposite> listCommentsPost = new ArrayList<ComentarioComposite>();

		for (ComentarioComposite comentarioComposite : listC) {
			if (comentarioComposite.getPostagem().getCodigo() == postId)
				listCommentsPost.add(comentarioComposite);
		}

		return listCommentsPost.get(index).getCodigo();
	}

	public String getCommentText(int idComentario) throws Exception {
		ComentarioComposite c = daoComentario.consultar(idComentario);

		if (c == null)
			throw new Exception("ComentÃ¡rio invÃ¡lido");

		return c.getConteudo();
	}

	public String getCommentAuthor(int idComentario) throws Exception {
		ComentarioComposite c = daoComentario.consultar(idComentario);

		if (c == null)
			throw new Exception("ComentÃ¡rio invÃ¡lido");

		Usuario user = c.getUsuario();

		return user == null ? "anonimo" : user.getLogin();
	}

	public int addCommentAnno(int postId, String texto) throws Exception {

		Postagem post = daoPostagem.consultar(postId);

		ComentarioComposite cA = new ComentarioAnonimo();

		if (post.getBlog().isAutorizaComentarioAnonimo()) {
			cA.setConteudo(texto);
			cA.setPostagem(post);
			daoComentario.criar(cA);
			return daoComentario.getMaxId();
		} else {
			throw new Exception("ComentÃ¡rio anÃ´nimo desabilitado");
		}
	}

	public void deletePost(String sessionId1, int postId1) throws Exception {

		if (sessionId1 == null || sessionId1.equals(""))
			throw new Exception("SessÃ£o invÃ¡lida");

		Postagem post = daoPostagem.consultar(postId1);

		if (post == null)
			throw new Exception("Post invÃ¡lido");

		daoPostagem.deletar(post);
	}

	public void deleteBlog(String sessionId1, int blogId1) throws Exception {

		if (sessionId1 == null || sessionId1.equals(""))
			throw new Exception("SessÃ£o invÃ¡lida");

		Blog blog = daoBlog.consultar(blogId1);

		if (blog == null)
			throw new Exception("Blog invÃ¡lido");

		daoBlog.deletar(blog);
	}

	public void deleteProfile(String sessionId) throws Exception {

		if (sessionId == null || sessionId.equals(""))
			throw new Exception("SessÃ£o invÃ¡lida");

		Usuario user = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));

		if (user == null)
			throw new Exception("UsuÃ¡rio invÃ¡lido");

		daoUsuario.deletar(user);
	}

	public String findProfileByName(String match, String order, String offSet, String maxEntries) throws Exception {

		if (match == null || match.equals(""))
			throw new Exception("Campo invÃ¡lido: match");

		int offSetInt;

		try {
			offSetInt = Integer.parseInt(offSet);

			if (offSetInt < 0)
				throw new Exception("Campo invÃ¡lido: offset");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: offset");
		}

		int maxEntriesInt;

		try {
			maxEntriesInt = Integer.parseInt(maxEntries);

			if (maxEntriesInt < 0)
				throw new Exception("Campo invÃ¡lido: maxentries");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: maxentries");
		}

		if (order == null || !order.equals("asc") && !order.equals("desc"))
			throw new Exception("Campo invÃ¡lido: order");

		BuscaStrategy<Usuario> buscaUsuario = new BuscaUsuarioNome();

		List<Usuario> usuarios = buscaUsuario.buscar(match, order, offSetInt, maxEntriesInt);
		List<String> logins = new ArrayList<String>();

		for (Usuario user : usuarios)
			logins.add(user.getLogin());

		return logins.toString();
	}

	public String findProfileByLogin(String match, String order, String offSet, String maxEntries) throws Exception {

		if (match == null || match.equals(""))
			throw new Exception("Campo invÃ¡lido: match");

		int offSetInt;

		try {
			offSetInt = Integer.parseInt(offSet);

			if (offSetInt < 0)
				throw new Exception("Campo invÃ¡lido: offset");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: offset");
		}

		int maxEntriesInt;

		try {
			maxEntriesInt = Integer.parseInt(maxEntries);

			if (maxEntriesInt < 0)
				throw new Exception("Campo invÃ¡lido: maxentries");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: maxentries");
		}

		if (order == null || !order.equals("asc") && !order.equals("desc"))
			throw new Exception("Campo invÃ¡lido: order");

		BuscaStrategy<Usuario> buscaUsuario = new BuscaUsuarioLogin();

		List<Usuario> usuarios = buscaUsuario.buscar(match, order, offSetInt, maxEntriesInt);
		List<String> logins = new ArrayList<String>();

		for (Usuario user : usuarios)
			logins.add(user.getLogin());

		return logins.toString();
	}

	public String findProfileByEmail(String match) throws Exception {

		if (match == null) {
			throw new Exception("Campo invÃ¡lido: match");
		} else {
			padrao = Pattern.compile(PADRAO_EMAIL);
			verificaPadrao = padrao.matcher(match);

			if (!verificaPadrao.matches()) {
				throw new Exception("Campo invÃ¡lido: match");
			}

		}

		BuscaStrategy<Usuario> buscaUsuario = new BuscaUsuarioEmail();

		List<Usuario> usuarios = buscaUsuario.buscar(match, "", 0, 0);

		if (usuarios.size() == 0)
			return "";
		return usuarios.get(0).getLogin();
	}

	public String findProfileByDateInterval(String from, String to, String order, String offset, String maxentries)
			throws Exception {

		if (from == null || from.equals(""))
			throw new Exception("Campo invÃ¡lido: from");

		if (to == null || to.equals(""))
			throw new Exception("Campo invÃ¡lido: to");

		SimpleDateFormat sdfCima = new SimpleDateFormat("dd/MM/yyy");
		Date fromDate;

		// formato a ser enviado para o banco
		SimpleDateFormat sdfBaixo = new SimpleDateFormat("yyyy-MM-dd");

		// tentativa de conversÃ£o de 'from' para funfar e ir ao banco
		try {
			fromDate = new Date(sdfCima.parse(from).getTime());

			fromDate = Date.valueOf((sdfBaixo.format(fromDate)));

		} catch (ParseException e) {
			throw new Exception("Campo invÃ¡lido: from");
		} catch (IllegalArgumentException e) {
			throw new Exception("Campo invÃ¡lido: from");
		}

		Date toDate;

		// tentativa de conversÃ£o de 'to' para funfar e ir ao banco
		try {
			toDate = new Date(sdfCima.parse(to).getTime());

			toDate = Date.valueOf((sdfBaixo.format(toDate)));
		} catch (ParseException e) {
			throw new Exception("Campo invÃ¡lido: to");
		} catch (IllegalArgumentException e) {
			throw new Exception("Campo invÃ¡lido: to");
		}

		int offSetInt;

		try {
			offSetInt = Integer.parseInt(offset);

			if (offSetInt < 0)
				throw new Exception("Campo invÃ¡lido: offset");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: offset");
		}

		int maxEntriesInt;

		try {
			maxEntriesInt = Integer.parseInt(maxentries);

			if (maxEntriesInt < 0)
				throw new Exception("Campo invÃ¡lido: maxentries");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: maxentries");
		}

		if (order == null || !order.equals("asc") && !order.equals("desc"))
			throw new Exception("Campo invÃ¡lido: order");

		if (fromDate.compareTo(toDate) > 0)
			throw new Exception("Campo invÃ¡lido: intervalo inconsistente");

		BuscaStrategy<Usuario> buscaUsuario = new BuscaUsuarioPorIntervaloData();

		List<Usuario> usuarios = buscaUsuario.buscar(fromDate.toString() + ";" + toDate.toString(), order, offSetInt,
				maxEntriesInt);
		List<String> logins = new ArrayList<String>();

		for (Usuario user : usuarios)
			logins.add(user.getLogin());

		return logins.toString();
	}

	public String findBlogByName(String match, String order, String offSet, String maxEntries) throws Exception {

		if (match == null || match.equals(""))
			throw new Exception("Campo invÃ¡lido: match");

		int offSetInt;

		try {
			offSetInt = Integer.parseInt(offSet);

			if (offSetInt < 0)
				throw new Exception("Campo invÃ¡lido: offset");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: offset");
		}

		int maxEntriesInt;

		try {
			maxEntriesInt = Integer.parseInt(maxEntries);

			if (maxEntriesInt < 0)
				throw new Exception("Campo invÃ¡lido: maxentries");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: maxentries");
		}

		if (order == null || !order.equals("asc") && !order.equals("desc"))
			throw new Exception("Campo invÃ¡lido: order");

		BuscaStrategy<Blog> buscaBlog = new BuscaBlogNome();

		List<Blog> blogs = buscaBlog.buscar(match, order, offSetInt, maxEntriesInt);
		List<Integer> ids = new ArrayList<Integer>();

		for (Blog b : blogs)
			ids.add(b.getCodigo());

		return ids.toString();
	}

	public String findBlogByDescription(String match, String order, String offSet, String maxEntries) throws Exception {

		if (match == null || match.equals(""))
			throw new Exception("Campo invÃ¡lido: match");

		int offSetInt;

		try {
			offSetInt = Integer.parseInt(offSet);

			if (offSetInt < 0)
				throw new Exception("Campo invÃ¡lido: offset");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: offset");
		}

		int maxEntriesInt;

		try {
			maxEntriesInt = Integer.parseInt(maxEntries);

			if (maxEntriesInt < 0)
				throw new Exception("Campo invÃ¡lido: maxentries");

		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: maxentries");
		}

		if (order == null || !order.equals("asc") && !order.equals("desc"))
			throw new Exception("Campo invÃ¡lido: order");

		BuscaStrategy<Blog> buscaBlog = new BuscaBlogDescricao();

		List<Blog> blogs = buscaBlog.buscar(match, order, offSetInt, maxEntriesInt);

		List<Integer> ids = new ArrayList<Integer>();

		for (Blog b : blogs)
			ids.add(b.getCodigo());

		return ids.toString();
	}

	public int getNumberOfAnnouncements(String sessionId) throws Exception {

		if (sessionId == null || sessionId.equals(""))
			throw new Exception("SessÃ£o invÃ¡lida");

		Usuario user = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));
		List<Assinatura> assinaturas = daoAssinatura.getList();
		int numberOA = 0;

		if (user == null)
			throw new Exception("UsuÃ¡rio invÃ¡lido");

		for (Assinatura a : assinaturas)
			if (a.getUsuario().equals(user))
				numberOA++;

		return numberOA;

	}

	public void addPostAnnouncements(String sessionId, String blogId) {
		Usuario assinante = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(
				Integer.parseInt(sessionId)));
		Blog b = daoBlog.consultar(Integer.parseInt(blogId));

		daoAssinatura.criar(assinante.criarAssinatura(b));

	}

	public int getAnnouncement(String sessionId, int index) throws Exception {

		if (sessionId == null || sessionId.equals(""))
			throw new Exception("Campo invÃ¡lido: sessionId");

		if (index < 0)
			throw new Exception("Campo invÃ¡lido: indice");

		Usuario user = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));

		List<Blog> blogs = daoUsuario.getBlogsSeguidos(user);
		Blog blog = daoBlog.consultar(blogs.get(index).getCodigo());

		return blog.getCodigo();

	}

	public int getPostJustCreated(String announcementId) {

		Blog blog = daoBlog.consultar(Integer.parseInt(announcementId));

		List<Postagem> posts = daoPostagem.getPostagensPorBlog(blog);

		return posts.get(posts.size() - 1).getCodigo();
	}

	public void deleteAnnouncement(String sessionId, String announcementId) throws Exception {
		Usuario assinante = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(
				Integer.parseInt(sessionId)));

		Blog blog = daoBlog.consultar(Integer.parseInt(announcementId));

		daoAssinatura.deletar(assinante.excluirAssinatura(blog));

	}

	public int addSubComment(String sessionId, String parentCommentId, String texto) throws Exception {

		Usuario user = null;
		ComentarioComposite comment = null;
		int nivel = 0;

		if (sessionId == null || sessionId.equals(""))
			throw new Exception("Campo invÃ¡lido: sessionId");

		try {
			user = daoUsuario.consultar(GerenciadorSessao.getInstance().getLoginById(Integer.parseInt(sessionId)));
		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: sessionId");
		}

		if (parentCommentId == null || parentCommentId.equals(""))
			throw new Exception("Campo invÃ¡lido: parentCommentId");

		try {
			comment = daoComentario.consultar(Integer.parseInt(parentCommentId));
		} catch (Exception e) {
			throw new Exception("Campo invÃ¡lido: parentCommentId");
		}

		while (comment != null) {
			comment = comment.getComentarioPai();
			nivel++;
		}

		if (nivel < 3) {
			comment = daoComentario.consultar(Integer.parseInt(parentCommentId));

			ComentarioComposite cN = new ComentarioNormal();
			cN.setConteudo(texto);
			cN.setUsuario(user);
			cN.setComentarioPai(comment);
			cN.setPostagem(comment.getPostagem());
			daoComentario.criar(cN);
		} else {
			throw new Exception("Cadeia mÃ¡xima de subcomentÃ¡rios excedida");
		}

		return daoComentario.getMaxId();
	}

	public String getParentComment(String commentId) throws Exception {

		if (commentId == null || commentId.equals(""))
			throw new Exception("Campo invÃ¡lido: commentId");

		ComentarioComposite comment = daoComentario.consultar(Integer.parseInt(commentId));

		if (comment.getComentarioPai() == null)
			return "";

		return String.valueOf(comment.getComentarioPai().getCodigo());
	}

	public int getParentPost(String commentId) throws Exception {

		if (commentId == null || commentId.equals(""))
			throw new Exception("Campo invÃ¡lido: commentId");

		ComentarioComposite comment = daoComentario.consultar(Integer.parseInt(commentId));

		return comment.getPostagem().getCodigo();
	}

	public int getNumberOfCommentsByBlog(String blogId) throws Exception {

		int numT = 0;

		if (blogId == null || blogId.equals(""))
			throw new Exception("Campo invÃ¡lido: blogId");

		int blogIdInt = 0;

		try {
			blogIdInt = Integer.parseInt(blogId);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Campo invÃ¡lido: blogId");
		}

		Blog blog = daoBlog.consultar(blogIdInt);

		List<Postagem> posts = daoPostagem.getList();
		List<ComentarioComposite> comments = daoComentario.getList();

		for (Postagem p : posts)
			for (ComentarioComposite c : comments)
				if (p.getBlog().equals(blog) && c.getPostagem().getCodigo() == p.getCodigo())
					numT++;

		return numT;

	}

	public int getTotalNumberOfCommentsByPost(String postId) throws Exception {

		if (postId == null || postId.equals(""))
			throw new Exception("Campo invÃ¡lido: postId");

		int numT = 0;
		int postIdInt = 0;

		try {
			postIdInt = Integer.parseInt(postId);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Campo invÃ¡lido: postId");
		}

		List<ComentarioComposite> comments = daoComentario.getList();

		for (ComentarioComposite comentarioComposite : comments)
			if (comentarioComposite.getPostagem().getCodigo() == postIdInt)
				numT++;

		return numT;

	}

	public int getNumberOfCommentsByComment(String commentId) throws Exception {

		if (commentId == null || commentId.equals(""))
			throw new Exception("Campo invÃ¡lido: commentId");

		int commentIdInt = 0;

		try {
			commentIdInt = Integer.parseInt(commentId);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Campo invÃ¡lido: commentId");
		}

		ComentarioComposite comment = daoComentario.consultar(commentIdInt);

		return comment.getListaComentarios().size();
	}

	public int getTotalNumberOfCommentsByComment(String commentId) throws Exception {

		if (commentId == null || commentId.equals(""))
			throw new Exception("Campo invÃ¡lido: commentId");

		int commentIdInt = 0;

		try {
			commentIdInt = Integer.parseInt(commentId);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Campo invÃ¡lido: commentId");
		}

		ComentarioComposite comment = daoComentario.consultar(commentIdInt);

		List<ComentarioComposite> subC = comment.getListaComentarios();
		int numT = subC.size();

		for (ComentarioComposite comentarioComposite : subC)
			numT += daoComentario.consultaSubComentarios(comentarioComposite).size();

		return numT;

	}

	public int getSubComment(String commentId, String indice) throws Exception {

		if (commentId == null || commentId.equals(""))
			throw new Exception("Campo invÃ¡lido: commentId");

		if (indice == null || indice.equals(""))
			throw new Exception("Campo invÃ¡lido: Ã­ndice");

		ComentarioComposite cC = daoComentario.consultar(Integer.parseInt(commentId));

		int index = Integer.parseInt(indice);

		if (index < 0 || index > cC.getListaComentarios().size())
			throw new Exception("Ã�ndice de subcomentÃ¡rio invÃ¡lido");

		return cC.getListaComentarios().get(index).getCodigo();

	}
	
	public void getBlogs(){
		
		List<Blog> lista  = DAOFactory.getDAOFactory().getDAOBlog().getList();
		
		
		//return lista
		
	}
	
}
