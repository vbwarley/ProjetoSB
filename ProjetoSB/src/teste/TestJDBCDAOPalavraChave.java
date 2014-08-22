package teste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.PalavraChave;
import nucleo.model.persistencia.jdbc.JDBCDAOPalavraChave;

import org.junit.Before;
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

	@Test
	public void getList() {
		List<PalavraChave> listPC = new ArrayList<PalavraChave>();
		
		PalavraChave pc1 = new PalavraChave();
		PalavraChave pc2 = new PalavraChave();
		PalavraChave pc3 = new PalavraChave();
		
		pc1.setNome("P1");
		pc2.setNome("P2");
		pc3.setNome("P3");
		
		jdpc.criar(pc1);
		jdpc.criar(pc2);
		jdpc.criar(pc3);
		
		listPC.add(pc1);
		listPC.add(pc2);
		listPC.add(pc3);
		
		List<PalavraChave> listaPCRetornada = jdpc.getList();
		int iguais = 0;
		
		for (PalavraChave pc : listPC)
			for (PalavraChave pcRetornada : listaPCRetornada)
				if (pc.equals(pcRetornada))
					iguais++;

		 assertEquals(3, iguais);		
	}
}
