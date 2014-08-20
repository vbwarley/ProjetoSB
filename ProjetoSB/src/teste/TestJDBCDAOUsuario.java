package teste;

import static org.junit.Assert.*;

import java.sql.Date;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOUsuario;

import org.junit.Before;
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
		
		usuario.setLogin("vbwarley");
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

}
