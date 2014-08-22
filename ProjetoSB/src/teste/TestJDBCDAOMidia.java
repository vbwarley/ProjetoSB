package teste;

import static org.junit.Assert.assertEquals;
import nucleo.model.negocios.Midia;
import nucleo.model.negocios.TipoMidia;
import nucleo.model.persistencia.jdbc.JDBCDAOMidia;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOMidia {

	private JDBCDAOMidia jdm;

	@Before
	public void before() {
		jdm = new JDBCDAOMidia();
	}

	@Test
	public void criar() {
		Midia midia = new Midia();

		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		// comentario e postagem

		jdm.criar(midia);

		assertEquals(true, jdm.consultar(midia.getCodigo()).equals(midia));
	}

	@Test
	public void consultar() {
		Midia midia = new Midia();

		midia.setTipo(TipoMidia.AUDIO);
		midia.setNomeArquivo("Arquivo X");
		// comentario e postagem

		jdm.criar(midia);

		assertEquals(true, jdm.consultar(midia.getCodigo()).equals(midia));

	}
}
