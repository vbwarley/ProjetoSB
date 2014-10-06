package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.negocios.Midia;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOComentario;
import nucleo.model.persistencia.jdbc.JDBCDAOMidia;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOMidia {

	private JDBCDAOMidia jdm;
	private JDBCDAOUsuario jdu;
	private JDBCDAOBlog jdb;
	private JDBCDAOPostagem jdp;
	private JDBCDAOComentario jdc;

	@Before
	public void before() {
		jdm = new JDBCDAOMidia();
		jdu = new JDBCDAOUsuario();
		jdb = new JDBCDAOBlog();
		jdp = new JDBCDAOPostagem();
		jdc = new JDBCDAOComentario();
	}
	
	@Test
	public void criar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();
		Midia midia = new Midia();

		usuario.setLogin("warlyos");
		usuario.setSenha("222");
		usuario.setNome("Vital");
		usuario.setSexo("Masculino");
		usuario.setEmail("aaaav@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2114-01-14"));
		usuario.setEndereco("Terra dp nunca");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 2000100");
		usuarioA.setNome("Anônimo 2000100");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

		blog.setTitulo("Blá Blu Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("P.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("XXX Blá Blá");
		postagem.setTitulo("Definido.");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("O marxismo cultural é um perigo.");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Marximos Cultural");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo sem");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem OK");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentario);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo D");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem NOK");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentario.addComentario(comentarioN);		

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		midia.setComentario(comentario);
		midia.setPostagem(null);

		jdm.criar(midia);

		assertEquals(true, midia.equals(jdm.consultar(midia.getCodigo())));
	}
	
	@Test
	public void consultar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();
		Midia midia = new Midia();

		usuario.setLogin("warly");
		usuario.setSenha("222");
		usuario.setNome("Vital");
		usuario.setSexo("Masculino");
		usuario.setEmail("aaaav@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2114-01-14"));
		usuario.setEndereco("Terra dp nunca");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 1001");
		usuarioA.setNome("Anônimo 1001");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

		blog.setTitulo("Blá Blu Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("P.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("XXX Blá Blá");
		postagem.setTitulo("Definido.");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("O marxismo cultural é um perigo.");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Marximos Cultural");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo sem");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem OK");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentario);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo D");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem NOK");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentario.addComentario(comentarioN);

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);
		
		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		midia.setComentario(comentarioA);
		midia.setPostagem(null);

		jdm.criar(midia);

		assertEquals(true, midia.equals(jdm.consultar(midia.getCodigo())));
	}
	
	@Test
	public void alterar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Midia midia = new Midia();

		usuario.setLogin("varly");
		usuario.setSenha("222");
		usuario.setNome("Vital");
		usuario.setSexo("Masculino");
		usuario.setEmail("aaaav@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2114-01-14"));
		usuario.setEndereco("Terra dp nunca");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 12001");
		usuarioA.setNome("Anônimo 12001");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

		blog.setTitulo("Blá Blu Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("P.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("XXX Blá Blá");
		postagem.setTitulo("Definido.");
		postagem.setBlog(blog);

		jdp.criar(postagem);
		
		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		midia.setComentario(null);
		midia.setPostagem(postagem);

		jdm.criar(midia);

		midia.setNomeArquivo("Arquivo morto");

		jdm.alterar(midia);

		assertEquals(true, midia.equals(jdm.consultar(midia.getCodigo())));
	}
	
	
	@Test
	public void deletar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Midia midia = new Midia();

		usuario.setLogin("warlzy");
		usuario.setSenha("222");
		usuario.setNome("Vital");
		usuario.setSexo("Masculino");
		usuario.setEmail("aaaav@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2114-01-14"));
		usuario.setEndereco("Terra dp nunca");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 2001");
		usuarioA.setNome("Anônimo 2001");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

		blog.setTitulo("Blá Blu Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("P.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("XXX Blá Blá");
		postagem.setTitulo("Definido.");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		midia.setComentario(null);
		midia.setPostagem(postagem);

		jdm.criar(midia);
		jdm.deletar(midia);

		assertEquals(null, jdm.consultar(midia.getCodigo()));
	}
	
	@Test
	public void getList() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();
		Midia midia = new Midia();
		Midia midia2 = new Midia();
		Midia midia3 = new Midia();
		List<Midia> listMidia = new ArrayList<Midia>();

		usuario.setLogin("warlys");
		usuario.setSenha("222");
		usuario.setNome("Vital");
		usuario.setSexo("Masculino");
		usuario.setEmail("aaaav@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2114-01-14"));
		usuario.setEndereco("Terra dp nunca");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 10021");
		usuarioA.setNome("Anônimo 10021");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

		blog.setTitulo("Blá Blu Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("P.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("XXX Blá Blá");
		postagem.setTitulo("Definido.");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("O marxismo cultural é um perigo.");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Marximos Cultural");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo sem");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem OK");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentario);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo D");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem NOK");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentario.addComentario(comentarioN);

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo W");
		midia.setComentario(comentario);
		midia.setPostagem(null);

		midia2.setTipo(TipoMidia.IMAGEM);
		midia2.setNomeArquivo("Arquivo W");
		midia2.setComentario(null);
		midia2.setPostagem(postagem);

		midia3.setTipo(TipoMidia.VIDEO);
		midia3.setNomeArquivo("Arquivo X");
		midia3.setComentario(null);
		midia3.setPostagem(postagem);

		jdm.criar(midia);
		jdm.criar(midia2);
		jdm.criar(midia3);

		listMidia.add(midia);
		listMidia.add(midia2);
		listMidia.add(midia3);

		List<Midia> listaMidiaRetornada = jdm.getList();
		int iguais = 0;

		for (Midia m : listMidia)
			for (Midia mRetornado : listaMidiaRetornada)
				if (m.equals(mRetornado))
					iguais++;

		assertEquals(3, iguais);
	}
}
