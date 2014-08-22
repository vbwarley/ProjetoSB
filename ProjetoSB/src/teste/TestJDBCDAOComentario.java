package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOComentario;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOComentario {
	
	private JDBCDAOComentario jdc;
	private JDBCDAOUsuario jdu;
	
	@Before
	public void before() {
		jdc = new JDBCDAOComentario();
		jdu = new JDBCDAOUsuario();
	
	}
	
	@Test
	public void criar() {
		

		Usuario usuario = new Usuario();

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
		
		ComentarioComposite comentario = new ComentarioComposite();
		
		comentario.setCodigo(1);
		comentario.setComentarioPai(null);
		comentario.setConteudo("Conteudo");
		comentario.setListaComentarios(null);
		comentario.setPostagem(null);
		comentario.setTitulo("Postagem linda");
		comentario.setUsuario(usuario);
		
		jdc.criar(comentario);
		
		assertEquals(true, jdc.consultar(comentario.getCodigo()).equals(comentario));
	}

	
	
}
