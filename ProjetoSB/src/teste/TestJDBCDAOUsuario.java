package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJDBCDAOUsuario {

	private JDBCDAOUsuario jdu;

	@Before
	public void before() {
		jdu = new JDBCDAOUsuario();
	}
	
	@Test
	public void criar() {
		Usuario usuario = new Usuario();

		usuario.setLogin("vbwarley1");
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

		assertEquals(true, jdu.consultar(usuario.getLogin()).equals(usuario));
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
		
		assertEquals(true, jdu.consultar(usuario.getLogin())
				.equals(usuario));
	}
	
	@Test
	public void alterar() {
		Usuario usuario = new Usuario();

		usuario.setLogin("vbwarley3");
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
		
		usuario.setSenha("2122");
		usuario.setNome("Vitaly");
		usuario.setSexo('F');
		usuario.setEmail("a@gmail.com");
		usuario.setDataNascimento(Date.valueOf("1919-11-24"));
		usuario.setEndereco("Ali");
		usuario.setInteresses("Fazer tudo");
		usuario.setQuemSouEu("Ninguém");
		usuario.setFilmes("Não tenho");
		usuario.setLivros("O máximo");
		usuario.setMusicas("Aquela lá");
		
		jdu.alterar(usuario);

		assertEquals(true, jdu.consultar(usuario.getLogin()).equals(usuario));
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

		usuario1.setLogin("Ciclano");
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
		
		usuario3.setLogin("Coxalano");
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
				
		jdu.criar(usuario1);
		jdu.criar(usuario2);
		jdu.criar(usuario3);
		jdu.criar(usuario4);
		
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
