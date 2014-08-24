package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.PalavraChave;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOPalavraChave;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOPalavraChave {

	private JDBCDAOPalavraChave jdpc;
	private JDBCDAOPostagem jdp;
	private JDBCDAOBlog jdb;
	private JDBCDAOUsuario jdu;

	@Before
	public void before() {
		jdpc = new JDBCDAOPalavraChave();
		jdp = new JDBCDAOPostagem();
		jdb = new JDBCDAOBlog();
		jdu = new JDBCDAOUsuario();
	}

	@Test
	public void criar() {

		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();
		PalavraChave palavraChave = new PalavraChave();

		usuario.setLogin("UP1");
		usuario.setSenha("11211");
		usuario.setNome("Zéfa das dores");
		usuario.setSexo('F');
		usuario.setEmail("ze@example.com");
		usuario.setDataNascimento(Date.valueOf("2000-10-19"));
		usuario.setEndereco("RIO");
		usuario.setInteresses("NADA");
		usuario.setQuemSouEu("SEMPRE EU");
		usuario.setFilmes("NS");
		usuario.setLivros("GOT");
		usuario.setMusicas("Preguiça");

		jdu.criar(usuario);

		blog.setTitulo("O mar azul");
		blog.setDescricao("O mar tem a cor azul");
		blog.setImagemFundo("azul.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setConteudo("pq o mar eh azu");
		postagem1.setTitulo("mar = azul");
		postagem1.setBlog(blog);

		postagem2.setTitulo("Prog web");
		postagem2.setConteudo("gerfdte");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);

		palavraChave.setNome("revolucao");
		palavraChave.adicionaPostagem(postagem1);
		palavraChave.adicionaPostagem(postagem2);
		
		jdpc.criar(palavraChave);

		assertEquals(true,
				palavraChave.equals(jdpc.consultar(palavraChave.getCodigo())));
	}

	@Test
	public void consultar() {

		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();
		PalavraChave palavraChave = new PalavraChave();

		usuario.setLogin("UP2");
		usuario.setSenha("112211");
		usuario.setNome("Franscisaca das dores");
		usuario.setSexo('F');
		usuario.setEmail("fran@example.com");
		usuario.setDataNascimento(Date.valueOf("2001-10-19"));
		usuario.setEndereco("RIOS");
		usuario.setInteresses("NADS");
		usuario.setQuemSouEu("SEMPRIS EU");
		usuario.setFilmes("NSS");
		usuario.setLivros("GOAT");
		usuario.setMusicas("Preguiças");

		jdu.criar(usuario);

		blog.setTitulo("O mar vermelho");
		blog.setDescricao("O mar tem a cor vermelho");
		blog.setImagemFundo("mar.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setConteudo("pq o mar eh preto");
		postagem1.setTitulo("mar = vermei");
		postagem1.setBlog(blog);

		postagem2.setTitulo("erro,, mar azulhado");
		postagem2.setConteudo("losls");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);

		palavraChave.setNome("revolucao poxinha nenhuma");
		palavraChave.adicionaPostagem(postagem1);
		palavraChave.adicionaPostagem(postagem2);
		
		jdpc.criar(palavraChave);

		assertEquals(true,
				palavraChave.equals(jdpc.consultar(palavraChave.getCodigo())));
	}

	@Test
	public void alterar() {

		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();
		PalavraChave palavraChave = new PalavraChave();

		usuario.setLogin("UP3");
		usuario.setSenha("112211");
		usuario.setNome("Franscisacos das dores");
		usuario.setSexo('M');
		usuario.setEmail("fran@example.com");
		usuario.setDataNascimento(Date.valueOf("2001-10-19"));
		usuario.setEndereco("RIOS");
		usuario.setInteresses("NADS");
		usuario.setQuemSouEu("SEMPRIS EU");
		usuario.setFilmes("NSS");
		usuario.setLivros("GOAT");
		usuario.setMusicas("Preguiças");

		jdu.criar(usuario);

		blog.setTitulo("O mar amarelo, né");
		blog.setDescricao("O mar tem a cor amarel");
		blog.setImagemFundo("mar.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setConteudo("pq o mar eh braco");
		postagem1.setTitulo("mar = vermei");
		postagem1.setBlog(blog);

		postagem2.setTitulo("certo,, mar azulhado");
		postagem2.setConteudo("losls azuis");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);

		palavraChave.setNome("revolucaoéfurada");	
		palavraChave.adicionaPostagem(postagem1);
		palavraChave.adicionaPostagem(postagem2);
		
		jdpc.criar(palavraChave);

		palavraChave.setNome("contrarevolucaoaisin");

		jdpc.alterar(palavraChave);

		assertEquals(true,
				palavraChave.equals(jdpc.consultar(palavraChave.getCodigo())));
	}

	@Test
	public void deletar() {

		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();
		PalavraChave palavraChave = new PalavraChave();

		usuario.setLogin("UP4");
		usuario.setSenha("112211");
		usuario.setNome("Franscisacos das dores");
		usuario.setSexo('M');
		usuario.setEmail("fran@example.com");
		usuario.setDataNascimento(Date.valueOf("2001-10-19"));
		usuario.setEndereco("RIOS");
		usuario.setInteresses("NADS");
		usuario.setQuemSouEu("SEMPRIS EU");
		usuario.setFilmes("NSS");
		usuario.setLivros("GOAT");
		usuario.setMusicas("Preguiças");

		jdu.criar(usuario);

		blog.setTitulo("O mar amarelo, né");
		blog.setDescricao("O mar tem a cor amarel");
		blog.setImagemFundo("mar.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setConteudo("pq o mar eh braco");
		postagem1.setTitulo("mar = vermei");
		postagem1.setBlog(blog);

		postagem2.setTitulo("certo,, mar azulhado");
		postagem2.setConteudo("losls azuis");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);

		palavraChave.setNome("ahaha");
		palavraChave.adicionaPostagem(postagem1);
		palavraChave.adicionaPostagem(postagem2);

		jdpc.criar(palavraChave);
		jdpc.deletar(palavraChave);

		assertEquals(true,
				jdpc.consultar(palavraChave.getCodigo()));	

	}

	@Test
	public void getList() {
		
		List<PalavraChave> listPC = new ArrayList<PalavraChave>();
		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		Blog blog = new Blog();
		Usuario usuario = new Usuario();
		PalavraChave palavraChave1 = new PalavraChave();
		PalavraChave palavraChave2 = new PalavraChave();
		PalavraChave palavraChave3 = new PalavraChave();
		
		usuario.setLogin("UP5");
		usuario.setSenha("112211");
		usuario.setNome("Franscisacos das dores");
		usuario.setSexo('M');
		usuario.setEmail("fran@example.com");
		usuario.setDataNascimento(Date.valueOf("2001-10-19"));
		usuario.setEndereco("RIOS");
		usuario.setInteresses("NADS");
		usuario.setQuemSouEu("SEMPRIS EU");
		usuario.setFilmes("NSS");
		usuario.setLivros("GOAT");
		usuario.setMusicas("Preguiças");

		jdu.criar(usuario);

		blog.setTitulo("O mar amarelo, né");
		blog.setDescricao("O mar tem a cor amarel");
		blog.setImagemFundo("mar.jpeg");
		blog.setAutorizaComentarioAnonimo(false);
		blog.setAutorizaComentario(true);
		blog.setUsuario(usuario);

		jdb.criar(blog);

		postagem1.setConteudo("pq o mar eh braco");
		postagem1.setTitulo("mar = vermei");
		postagem1.setBlog(blog);

		postagem2.setTitulo("certo,, mar azulhado");
		postagem2.setConteudo("losls azuis");
		postagem2.setBlog(blog);

		jdp.criar(postagem1);
		jdp.criar(postagem2);
		
		palavraChave1.setNome("P1");
		palavraChave1.adicionaPostagem(postagem1);
		palavraChave2.setNome("P2");
		palavraChave2.adicionaPostagem(postagem2);
		palavraChave3.setNome("P3");
		palavraChave3.adicionaPostagem(postagem2);
		palavraChave3.adicionaPostagem(postagem1);

		jdpc.criar(palavraChave1);
		jdpc.criar(palavraChave2);
		jdpc.criar(palavraChave3);

		listPC.add(palavraChave1);
		listPC.add(palavraChave2);
		listPC.add(palavraChave3);

		List<PalavraChave> listaPCRetornada = jdpc.getList();
		int iguais = 0;

		for (PalavraChave pc : listPC)
			for (PalavraChave pcRetornada : listaPCRetornada)
				if (pc.equals(pcRetornada))
					iguais++;

		assertEquals(3, iguais);
	}
}
