package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

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

		comentario.setComentarioPai(comentario);
		comentario.setTipo(comentario.getClass().getSimpleName());
		comentario.setConteudo("Conteudo");
		comentario.setListaComentarios(null);
		comentario.setPostagem(postagem);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);
		
		comentarioA.setComentarioPai(comentario);
		comentarioA.setTipo(comentario.getClass().getSimpleName());
		comentarioA.setConteudo("Conteudo");
		comentarioA.setListaComentarios(null);
		comentarioA.setPostagem(postagem);
		comentarioA.setTitulo("Postagem linda2");
		comentarioA.setUsuario(comentarioA.getUsuario());
		
		comentarioN.setComentarioPai(comentario);
		comentarioN.setTipo(comentario.getClass().getSimpleName());
		comentarioN.setConteudo("Conteudo2");
		comentarioN.setListaComentarios(null);
		comentarioN.setPostagem(postagem);
		comentarioN.setTitulo("Postagem linda3");
		comentarioN.setUsuario(usuario);

		jdc.criar(comentario);

		assertEquals(true,
				jdc.consultar(comentario.getCodigo()).equals(comentario));
	}

}
