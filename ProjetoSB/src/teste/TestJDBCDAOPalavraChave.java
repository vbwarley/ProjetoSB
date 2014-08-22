package teste;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PalavraChave;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.jdbc.JDBCDAOPalavraChave;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJDBCDAOPalavraChave {

	private JDBCDAOPalavraChave jdpc;

	@Before
	public void before() {
		jdpc = new JDBCDAOPalavraChave();
	}

	@Test
	public void criar() {
		PalavraChave pc = new PalavraChave();

		pc.setNome("revolucao");

		jdpc.criar(pc);

		assertEquals(true, jdpc.consultar(pc.getCodigo()).equals(pc));
	}

	@Test
	public void consultar() {
		PalavraChave pc = new PalavraChave();

		pc.setNome("revolucao");

		jdpc.criar(pc);

		assertEquals(true, jdpc.consultar(pc.getCodigo()).equals(pc));
	}

	@Test
	public void alterar() {
		PalavraChave pc = new PalavraChave();

		pc.setNome("revolucao");

		jdpc.criar(pc);

		pc.setNome("contrarevolucao");

		jdpc.alterar(pc);

		assertEquals(true, jdpc.consultar(pc.getCodigo()).equals(pc));
	}

	@Test
	public void deletar() {
		PalavraChave pc = new PalavraChave();

		pc.setNome("revolucao");

		jdpc.criar(pc);
		jdpc.deletar(pc);

		assertEquals(null, jdpc.consultar(pc.getCodigo()));
	}

	@Ignore
	@Test
	public void getList() {
		
	}
}
