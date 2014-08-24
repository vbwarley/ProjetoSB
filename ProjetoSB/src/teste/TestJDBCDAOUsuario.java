package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJDBCDAOUsuario {

	private JDBCDAOUsuario jdu;
	private JDBCDAOBlog jdb;

	@Before
	public void before() {
		jdu = new JDBCDAOUsuario();
		jdb = new JDBCDAOBlog();
	}
	
	@Test
	public void criar() {
		Usuario usuarioCriador = new Usuario();
		Usuario usuarioAssinante = new Usuario();
		Blog blog = new Blog();
		
		usuarioCriador.setLogin("warley criador");
		usuarioCriador.setSenha("9010");
		usuarioCriador.setNome("Warley Vital");
		usuarioCriador.setSexo('M');
		usuarioCriador.setEmail("v@gmail.com");
		usuarioCriador.setDataNascimento(Date.valueOf("2014-01-14"));
		usuarioCriador.setEndereco("Povoado Novo Rio");
		usuarioCriador.setInteresses("Fazer nada");
		usuarioCriador.setQuemSouEu("Warley, o Vital");
		usuarioCriador.setFilmes("The Godfather");
		usuarioCriador.setLivros("O Mínimo");
		usuarioCriador.setMusicas("Poxa...");

		jdu.criar(usuarioCriador);
		
		blog.setTitulo("Aquele");
		blog.setDescricao("lá");
		blog.setImagemFundo("sabe.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		blog.setUsuario(usuarioCriador);

		jdb.criar(blog);
		
		usuarioAssinante.setLogin("warley assinante");
		usuarioAssinante.setSenha("9010");
		usuarioAssinante.setNome("Warley Vital");
		usuarioAssinante.setSexo('M');
		usuarioAssinante.setEmail("v@gmail.com");
		usuarioAssinante.setDataNascimento(Date.valueOf("2014-01-14"));
		usuarioAssinante.setEndereco("Povoado Novo Rio");
		usuarioAssinante.setInteresses("Fazer nada");
		usuarioAssinante.setQuemSouEu("Warley, o Vital");
		usuarioAssinante.setFilmes("The Godfather");
		usuarioAssinante.setLivros("O Mínimo");
		usuarioAssinante.setMusicas("Poxa...");
		usuarioAssinante.criarAssinatura(blog);

		jdu.criar(usuarioAssinante);

		assertEquals(true, usuarioAssinante.equals(jdu.consultar(usuarioAssinante.getLogin())));
	}
	
	@Test
	public void consultar() {
		Usuario usuario = new Usuario();

		usuario.setLogin("vbwarley2");
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
		
		// se já não estiver criado, executa o jdu.criar()
		jdu.criar(usuario);
		
		assertEquals(true, usuario.equals(jdu.consultar(usuario.getLogin())));
	}
	
	@Test
	public void alterar() {
		Usuario usuarioCriador1 = new Usuario();
		Usuario usuarioCriador2 = new Usuario();
		Usuario usuarioAssinante = new Usuario();
		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		
		usuarioCriador1.setLogin("warley criador 1");
		usuarioCriador1.setSenha("9010");
		usuarioCriador1.setNome("Warley Vital");
		usuarioCriador1.setSexo('M');
		usuarioCriador1.setEmail("v@gmail.com");
		usuarioCriador1.setDataNascimento(Date.valueOf("2014-01-14"));
		usuarioCriador1.setEndereco("Povoado Novo Rio");
		usuarioCriador1.setInteresses("Fazer nada");
		usuarioCriador1.setQuemSouEu("Warley, o Vital");
		usuarioCriador1.setFilmes("The Godfather");
		usuarioCriador1.setLivros("O Mínimo");
		usuarioCriador1.setMusicas("Poxa...");

		jdu.criar(usuarioCriador1);
		
		blog1.setTitulo("Aquele");
		blog1.setDescricao("lá");
		blog1.setImagemFundo("sabe.jpg");
		blog1.setAutorizaComentario(true);
		blog1.setAutorizaComentarioAnonimo(true);
		blog1.setUsuario(usuarioCriador1);

		jdb.criar(blog1);
		
		usuarioCriador2.setLogin("warley criador 2");
		usuarioCriador2.setSenha("9010");
		usuarioCriador2.setNome("Warley Vital");
		usuarioCriador2.setSexo('M');
		usuarioCriador2.setEmail("v@gmail.com");
		usuarioCriador2.setDataNascimento(Date.valueOf("2014-01-14"));
		usuarioCriador2.setEndereco("Povoado Novo Rio");
		usuarioCriador2.setInteresses("Fazer nada");
		usuarioCriador2.setQuemSouEu("Warley, o Vital");
		usuarioCriador2.setFilmes("The Godfather");
		usuarioCriador2.setLivros("O Mínimo");
		usuarioCriador2.setMusicas("Poxa...");
		
		jdu.criar(usuarioCriador2);

		blog2.setTitulo("esse");
		blog2.setDescricao("aqui");
		blog2.setImagemFundo("entende.jpg");
		blog2.setAutorizaComentario(true);
		blog2.setAutorizaComentarioAnonimo(false);
		blog2.setUsuario(usuarioCriador2);

		jdb.criar(blog2);		

		usuarioAssinante.setLogin("assinante vip");
		usuarioAssinante.setSenha("9010");
		usuarioAssinante.setNome("Warley Vital");
		usuarioAssinante.setSexo('M');
		usuarioAssinante.setEmail("v@gmail.com");
		usuarioAssinante.setDataNascimento(Date.valueOf("2014-01-14"));
		usuarioAssinante.setEndereco("Povoado Novo Rio");
		usuarioAssinante.setInteresses("Fazer nada");
		usuarioAssinante.setQuemSouEu("Warley, o Vital");
		usuarioAssinante.setFilmes("The Godfather");
		usuarioAssinante.setLivros("O Mínimo");
		usuarioAssinante.setMusicas("Poxa...");
		usuarioAssinante.criarAssinatura(blog1);
		usuarioAssinante.criarAssinatura(blog2);
		
		jdu.criar(usuarioAssinante);
		usuarioAssinante.excluirAssinatura(blog2);
		jdu.alterar(usuarioAssinante);

		assertEquals(true, usuarioAssinante.equals(jdu.consultar(usuarioAssinante.getLogin())));
	}
	
	@Test
	public void deletar() {
		Usuario usuario = new Usuario();

		usuario.setLogin("vbwarley4");
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
		jdu.deletar(usuario);

		assertEquals(null, jdu.consultar(usuario.getLogin()));
	}
	
	@Test
	public void getList() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		Usuario usuario4 = new Usuario();
		Blog blog2 = new Blog();
		Blog blog1 = new Blog();
		
		usuario1.setLogin("Ciclano criador");
		usuario1.setSenha("2210");
		usuario1.setNome("Babao");
		usuario1.setSexo('M');
		usuario1.setEmail("babb@gmail.com");
		usuario1.setDataNascimento(Date.valueOf("2013-01-14"));
		usuario1.setEndereco("Povoado Velho Rio");
		usuario1.setInteresses("X");
		usuario1.setQuemSouEu("XX");
		usuario1.setFilmes("XXX");
		usuario1.setLivros("OAKOA");
		usuario1.setMusicas("Malucao");
		
		
		jdu.criar(usuario1);
		
		blog1.setTitulo("Aquele");
		blog1.setDescricao("lá");
		blog1.setImagemFundo("sabe.jpg");
		blog1.setAutorizaComentario(true);
		blog1.setAutorizaComentarioAnonimo(true);
		blog1.setUsuario(usuario1);
		
		jdb.criar(blog1);
		
		usuario2.setLogin("Fulano");
		usuario2.setSenha("9010");
		usuario2.setNome("Viral");
		usuario2.setSexo('F');
		usuario2.setEmail("fica@gmail.com");
		usuario2.setDataNascimento(Date.valueOf("2014-04-14"));
		usuario2.setEndereco("Povoado Rio ALi");
		usuario2.setInteresses("nada");
		usuario2.setQuemSouEu("Wital");
		usuario2.setFilmes("Their");
		usuario2.setLivros("Oimo");
		usuario2.setMusicas("Aoxa...");
		usuario2.criarAssinatura(blog1);
		
		jdu.criar(usuario2);
		
		usuario3.setLogin("Coxalano 3");
		usuario3.setSenha("91010");
		usuario3.setNome("Virsacal");
		usuario3.setSexo('M');
		usuario3.setEmail("MAMA@gmail.com");
		usuario3.setDataNascimento(Date.valueOf("2114-04-14"));
		usuario3.setEndereco("ALi");
		usuario3.setInteresses("zada");
		usuario3.setQuemSouEu("bital");
		usuario3.setFilmes("Thair");
		usuario3.setLivros("Oymo");
		usuario3.setMusicas("Aboxa...");
		usuario3.criarAssinatura(blog1);
		
				
		jdu.criar(usuario3);
		
		usuario4.setLogin("Plano");
		usuario4.setSenha("9110");
		usuario4.setNome("Viralata");
		usuario4.setSexo('F');
		usuario4.setEmail("fic@gmail.com");
		usuario4.setDataNascimento(Date.valueOf("2014-01-14"));
		usuario4.setEndereco("Cai");
		usuario4.setInteresses("zznada");
		usuario4.setQuemSouEu("Witsal");
		usuario4.setFilmes("Theira");
		usuario4.setLivros("Oimos");
		usuario4.setMusicas("Aoxas...");
		usuario4.criarAssinatura(blog1);
		
		jdu.criar(usuario4);
		
		blog2.setTitulo("Aquele");
		blog2.setDescricao("lá");
		blog2.setImagemFundo("sabe.jpg");
		blog2.setAutorizaComentario(true);
		blog2.setAutorizaComentarioAnonimo(true);
		blog2.setUsuario(usuario4);
		
		jdb.criar(blog2);
		usuario1.criarAssinatura(blog2);
		usuario3.criarAssinatura(blog2);
		jdu.alterar(usuario1);
		jdu.alterar(usuario3);
			
		listaUsuario.add(usuario1);
		listaUsuario.add(usuario2);
		listaUsuario.add(usuario3);
		listaUsuario.add(usuario4);
		
		List<Usuario> listaUsuarioRetornada = jdu.getList();
		int iguais = 0;
		
		for (Usuario u : listaUsuario)
			for (Usuario uRetornado : listaUsuarioRetornada)
				if (u.equals(uRetornado))
					iguais++;
		
		
		/* Ao rodar o teste completo, teremos 6 registros no banco. 
		 * No entanto, precisamos igualar apenas quatro para que seja possível 
		 * confirmar que o método funciona corretamente. 
		 */
		 assertEquals(4, iguais);
	}
}
