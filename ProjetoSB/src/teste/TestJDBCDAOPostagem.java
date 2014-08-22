package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJDBCDAOPostagem {

	private JDBCDAOPostagem jdp;
	private JDBCDAOBlog jdb;
	private JDBCDAOUsuario jdu;

	@Before
	public void before() {
		jdp = new JDBCDAOPostagem();
		jdb = new JDBCDAOBlog();
		jdu = new JDBCDAOUsuario();
	}

	@Test
	public void criar() {

		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("U1");
		usuario.setSenha("1111");
		usuario.setNome("Maria das dores");
		usuario.setSexo('M');
		usuario.setEmail("email@example.com");
		usuario.setDataNascimento(Date.valueOf("2012-10-19"));
		usuario.setEndereco("Arapiraca");
		usuario.setInteresses("Musica");
		usuario.setQuemSouEu("Eu");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("Port.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("Blá Blá Blá Blá");
		postagem.setTitulo("Definindo...");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));

	}

	@Test
	public void consultar() {

		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("U2");
		usuario.setSenha("1121");
		usuario.setNome("Maria das dores");
		usuario.setSexo('M');
		usuario.setEmail("email@example.com");
		usuario.setDataNascimento(Date.valueOf("2012-10-19"));
		usuario.setEndereco("Arapiraca");
		usuario.setInteresses("Musica");
		usuario.setQuemSouEu("Eu");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Blú Bla");
		blog.setDescricao("....XXX......");
		blog.setImagemFundo("Xort.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("Blá Xlá Blá Blá");
		postagem.setTitulo("Definindo...");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));
	}

	@Test
	public void alterar() {
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Blog blog2 = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("U3");
		usuario.setSenha("1112");
		usuario.setNome("Dores das dores");
		usuario.setSexo('M');
		usuario.setEmail("email@example.com");
		usuario.setDataNascimento(Date.valueOf("2012-10-19"));
		usuario.setEndereco("Arapiraca");
		usuario.setInteresses("Musica");
		usuario.setQuemSouEu("Eu");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Zlú Cla");
		blog.setDescricao("....XZXZ......");
		blog.setImagemFundo("Xortx.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		blog2.setTitulo("Zlú Cla");
		blog2.setDescricao("....XZXZ......");
		blog2.setImagemFundo("Xortx.jpeg");
		blog2.setAutorizaComentarioAnonimo(false);
		blog2.setAutorizaComentario(true);
		blog2.setUsuario(usuario);

		jdb.criar(blog);
		jdb.criar(blog2);

		postagem.setTitulo("haXAha");
		postagem.setConteudo("Nada de nada");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		postagem.setTitulo("Programação WEB");
		postagem.setConteudo("Bla bla bla");
		postagem.setBlog(blog2);

		jdp.alterar(postagem);

		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));
	}

	@Test
	public void deletar() {

		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("U5");
		usuario.setSenha("921");
		usuario.setNome("Faria");
		usuario.setSexo('F');
		usuario.setEmail("mail@example.com");
		usuario.setDataNascimento(Date.valueOf("2011-09-09"));
		usuario.setEndereco("Acolá");
		usuario.setInteresses("Musica");
		usuario.setQuemSouEu("Eu");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Blú Bla CA");
		blog.setDescricao("Legalzn");
		blog.setImagemFundo("legal.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem.setConteudo("nossa");
		postagem.setTitulo("Definindo...");
		postagem.setBlog(blog);

		jdp.criar(postagem);
		jdp.deletar(postagem);

		assertEquals(null, jdp.consultar(postagem.getCodigo()));
	}

	@Test
	public void getList() {

		List<Postagem> listaPostagem = new ArrayList<Postagem>();
		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("U6");
		usuario.setSenha("1121");
		usuario.setNome("Maria das dores");
		usuario.setSexo('M');
		usuario.setEmail("email@example.com");
		usuario.setDataNascimento(Date.valueOf("2012-10-19"));
		usuario.setEndereco("Arapiraca");
		usuario.setInteresses("Musica");
		usuario.setQuemSouEu("Eu");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Blú Bla");
		blog.setDescricao("....XXX......");
		blog.setImagemFundo("Xort.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(false);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setTitulo("RIARIA");
		postagem1.setConteudo("abcdefghijk");
		postagem1.setBlog(blog);

		postagem2.setTitulo("Prog web");
		postagem2.setConteudo("gerfdte");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);

		listaPostagem.add(postagem1);
		listaPostagem.add(postagem2);

		List<Postagem> listaPostagemRetornada = jdp.getList();
		int iguais = 0;

		for (Postagem p : listaPostagem)
			for (Postagem pRetornada : listaPostagemRetornada)
				if (p.equals(pRetornada))
					iguais++;

		assertEquals(2, iguais);
	}

}
