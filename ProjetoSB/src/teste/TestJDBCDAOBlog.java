package teste;

import static org.junit.Assert.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOBlog {

	private JDBCDAOBlog jdb;
	private JDBCDAOUsuario jdu;

	@Before
	public void before() {
		jdb = new JDBCDAOBlog();
		jdu = new JDBCDAOUsuario();
	}
	
	@Test
	public void criar() {

		Usuario usuario = new Usuario();
		Blog blog = new Blog();

		usuario.setLogin("UB11");
		usuario.setSenha("991");
		usuario.setNome("RayRAy");
		usuario.setSexo("Masculino");
		usuario.setEmail("ra@iane.com");
		usuario.setDataNascimento(Date.valueOf("2009-06-20"));
		usuario.setEndereco("Palmeira");
		usuario.setInteresses("Hmm");
		usuario.setQuemSouEu("Vc");
		usuario.setFilmes("NS");
		usuario.setLivros("NL");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("PLL");
		blog.setDescricao("Melhor série");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		assertEquals(true, blog.equals(jdb.consultar(blog.getCodigo())));
	}
	
	@Test
	public void Consultar() {
		Usuario usuario = new Usuario();
		Blog blog = new Blog();

		usuario.setLogin("UB2");
		usuario.setSenha("991");
		usuario.setNome("RayRAy");
		usuario.setSexo("Masculino");
		usuario.setEmail("ra@iane.com");
		usuario.setDataNascimento(Date.valueOf("2009-06-20"));
		usuario.setEndereco("Palmeira");
		usuario.setInteresses("Hmm");
		usuario.setQuemSouEu("Vc");
		usuario.setFilmes("NS");
		usuario.setLivros("NL");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("Medicina");
		blog.setDescricao("Saúde");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		assertEquals(true, blog.equals(jdb.consultar(blog.getCodigo())));
	}
	
	@Test
	public void alterar() {
		Usuario usuario = new Usuario();
		// Usuario usuario2 = new Usuario();
		Blog blog = new Blog();

		usuario.setLogin("UB4");
		usuario.setSenha("991");
		usuario.setNome("RayRAy");
		usuario.setSexo("Masculino");
		usuario.setEmail("ra@iane.com");
		usuario.setDataNascimento(Date.valueOf("2009-06-20"));
		usuario.setEndereco("Palmeira");
		usuario.setInteresses("Hmm");
		usuario.setQuemSouEu("Vc");
		usuario.setFilmes("NS");
		usuario.setLivros("NL");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("NADA");
		blog.setDescricao("Série de ação");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		blog.setTitulo("ACED");
		blog.setDescricao("Livro de romance");
		blog.setImagemFundo("imgg.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);

		jdb.alterar(blog);

		assertEquals(true, blog.equals(jdb.consultar(blog.getCodigo())));
	}
	
	@Test
	public void deletar() {

		Usuario usuario = new Usuario();
		Blog blog = new Blog();

		usuario.setLogin("UB5");
		usuario.setSenha("991");
		usuario.setNome("RayRAyRAAAA");
		usuario.setSexo("Masculino");
		usuario.setEmail("ra@ianee.com");
		usuario.setDataNascimento(Date.valueOf("2008-06-20"));
		usuario.setEndereco("Palmeiras");
		usuario.setInteresses("Hmms");
		usuario.setQuemSouEu("Vcs");
		usuario.setFilmes("NSs");
		usuario.setLivros("NLs");
		usuario.setMusicas("Preguiças");

		jdu.criar(usuario);

		blog.setTitulo("Saúde");
		blog.setDescricao("Ficar em forma");
		blog.setImagemFundo("img1.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);
		jdb.deletar(blog);

		assertEquals(null, jdb.consultar(blog.getCodigo()));

	}

	@Test
	public void getList() {
		List<Blog> listaBlog = new ArrayList<Blog>();

		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		Blog blog3 = new Blog();
		Usuario usuario = new Usuario();

		usuario.setLogin("UB6");
		usuario.setSenha("999");
		usuario.setNome("RaRAy");
		usuario.setSexo("Feminino");
		usuario.setEmail("ara@iane.com");
		usuario.setDataNascimento(Date.valueOf("1909-06-20"));
		usuario.setEndereco("SAMPA");
		usuario.setInteresses("Correr");
		usuario.setQuemSouEu("Vc");
		usuario.setFilmes("NS");
		usuario.setLivros("NL");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog1.setTitulo("ABC");
		blog1.setDescricao("Alfabto");
		blog1.setImagemFundo("img1.jpg");
		blog1.setAutorizaComentario(true);
		blog1.setAutorizaComentarioAnonimo(true);
		blog1.setUsuario(usuario);

		blog2.setTitulo("Facebook");
		blog2.setDescricao("Rede social");
		blog2.setImagemFundo("img2.jpg");
		blog2.setAutorizaComentario(true);
		blog2.setAutorizaComentarioAnonimo(true);
		blog2.setUsuario(usuario);

		blog3.setTitulo("Instagram");
		blog3.setDescricao("Rede social");
		blog3.setImagemFundo("img3.jpg");
		blog3.setAutorizaComentario(true);
		blog3.setAutorizaComentarioAnonimo(true);
		blog3.setUsuario(usuario);

		jdb.criar(blog1);
		jdb.criar(blog2);
		jdb.criar(blog3);

		listaBlog.add(blog1);
		listaBlog.add(blog2);
		listaBlog.add(blog3);

		List<Blog> listaBlogRetornada = jdb.getList();
		int iguais = 0;

		for (Blog b : listaBlog)
			for (Blog bRetornado : listaBlogRetornada)
				if (b.equals(bRetornado))
					iguais++;

		assertEquals(3, iguais);
	}

}