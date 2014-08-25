package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOComentario;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOComentario {

	private JDBCDAOComentario jdc;
	private JDBCDAOUsuario jdu;
	private JDBCDAOPostagem jdp;
	private JDBCDAOBlog jdb;

	@Before
	public void before() {
		jdc = new JDBCDAOComentario();
		jdu = new JDBCDAOUsuario();
		jdp = new JDBCDAOPostagem();
		jdb = new JDBCDAOBlog();
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

		usuario.setLogin("doug42");
		usuario.setSenha("9010");
		usuario.setNome("Warley Vital");
		usuario.setSexo('M');
		usuario.setEmail("v@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario.setEndereco("Povoado Novo Rio");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 101");
		usuarioA.setNome("Anônimo 101");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

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

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo 1");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo 2");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentario);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo 3");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentario.addComentario(comentarioN);

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		assertEquals(true,
				comentario.equals(jdc.consultar(comentario.getCodigo())));
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

		usuario.setLogin("doug4");
		usuario.setSenha("9010");
		usuario.setNome("Warley Vital");
		usuario.setSexo('M');
		usuario.setEmail("v@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario.setEndereco("Povoado Novo Rio");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo");
		usuarioA.setNome("Anônimo");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

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

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentarioA);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo2");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentarioA.addComentario(comentarioN);
		
		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		assertEquals(true,
				comentario.equals(jdc.consultar(comentario.getCodigo())));
	}
	
	@Test
	public void alterar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();

		usuario.setLogin("doug44");
		usuario.setSenha("9010");
		usuario.setNome("Warley Vital");
		usuario.setSexo('M');
		usuario.setEmail("v@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario.setEndereco("Povoado Novo Rio");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 2");
		usuarioA.setNome("Anônimo 2");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

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

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentarioA);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo2");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentarioA.addComentario(comentarioN);

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		comentario.setConteudo("Bla blax");
		comentarioA.setTitulo("Conteudo alterado");
		
		jdc.alterar(comentario);
		jdc.alterar(comentarioA);
		
		assertEquals(true,
				(comentario.equals(jdc.consultar(comentario.getCodigo())) && comentarioA.equals(jdc.consultar(comentarioA.getCodigo()))));
	}
	
	@Test
	public void deletar() {

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();

		usuario.setLogin("doug45");
		usuario.setSenha("9010");
		usuario.setNome("Warley Vital");
		usuario.setSexo('M');
		usuario.setEmail("v@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario.setEndereco("Povoado Novo Rio");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 3");
		usuarioA.setNome("Anônimo 3");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

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

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentarioA);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo2");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentarioA.addComentario(comentarioN);
		
		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);
		
		jdc.deletar(comentarioA);

		assertEquals(true, (jdc.consultar(comentarioA.getCodigo()) == null && jdc.consultar(comentarioN.getCodigo()) == null));
	}
	
	@Test
	public void getList() {

		List<ComentarioComposite> listaComentario = new ArrayList<ComentarioComposite>(); //

		Usuario usuario = new Usuario();
		Usuario usuarioA = new Usuario();
		Postagem postagem = new Postagem();
		Blog blog = new Blog();
		ComentarioComposite comentario = new ComentarioNormal();
		ComentarioComposite comentarioN = new ComentarioNormal();
		ComentarioComposite comentarioA = new ComentarioAnonimo();

		usuario.setLogin("doug50");
		usuario.setSenha("9010");
		usuario.setNome("Warley Vital");
		usuario.setSexo('M');
		usuario.setEmail("v@gmail.com");
		usuario.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario.setEndereco("Povoado Novo Rio");
		usuario.setInteresses("Fazer nada");
		usuario.setQuemSouEu("Warley, o Vital");
		usuario.setFilmes("The Godfather");
		usuario.setLivros("O Mínimo");
		usuario.setMusicas("Poxa...");

		usuarioA.setLogin("Anônimo 100");
		usuarioA.setNome("Anônimo 100");

		jdu.criar(usuario);
		jdu.criar(usuarioA);

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

		comentario.setComentarioPai(null);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo");
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);

		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo");
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(usuarioA);

		comentarioN.setComentarioPai(comentarioA);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo2");
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		comentario.addComentario(comentarioA);
		comentarioA.addComentario(comentarioN);

		jdc.criar(comentario);
		jdc.criar(comentarioA);
		jdc.criar(comentarioN);

		listaComentario.add(comentario);
		listaComentario.add(comentarioA);
		listaComentario.add(comentarioN);

		List<ComentarioComposite> listaComentarioRetornada = jdc.getList();
		int iguais = 0;

		for (ComentarioComposite c : listaComentario)
			for (ComentarioComposite cRetornado : listaComentarioRetornada)
				if (c.equals(cRetornado))
					iguais++;

		assertEquals(3, iguais);
	}
}
