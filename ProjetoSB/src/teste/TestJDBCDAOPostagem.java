package teste;

import static org.junit.Assert.assertEquals;
import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;

import org.junit.Before;
import org.junit.Test;

public class TestJDBCDAOPostagem {

	private JDBCDAOPostagem jdp;

	@Before
	public void before() {
		jdp = new JDBCDAOPostagem();
	}

	@Test
	public void criar() {
		Postagem postagem = new Postagem();

		Blog blog = new Blog();

		blog.setTitulo("Blá Blu");
		blog.setDescricao("..........");
		blog.setImagemFundo("Port.jpeg");
		blog.setAutorizaComentarioAnonimo(true);
		blog.setAutorizaComentario(true);

	
		postagem.setConteudo("Blá Blá Blá Blá");
		postagem.setTitulo("Definindo...");
		postagem.setBlog(blog);

		jdp.criar(postagem);

		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));

	}

}
