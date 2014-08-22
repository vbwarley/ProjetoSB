package teste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.jdbc.JDBCDAOPostagem;

import org.junit.Before;
import org.junit.Ignore;
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

	@Ignore
	@Test
	public void consultar() {
		
		Postagem postagem = new Postagem();
		
		JDBCDAOPostagem jdp = new JDBCDAOPostagem();
		
		Blog blog = new Blog();

		postagem.setConteudo("hahahaha");
		postagem.setTitulo("INFERNO");
		postagem.setBlog(blog);
		
		
		jdp.criar(postagem);
		
		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));
	}
	
	@Ignore
	@Test
	public void alterar() {
		Postagem postagem = new Postagem();
		
		Blog blog = new Blog();

		postagem.setTitulo("hahaha");
		postagem.setConteudo("Nada de nada");
		postagem.setBlog(blog);
		

		jdp.criar(postagem);
		
		postagem.setTitulo("Programação WEB");
		postagem.setConteudo("Bla bla bla");
		postagem.setBlog(blog);
		
		
		
		jdp.alterar(postagem);

		assertEquals(true, jdp.consultar(postagem.getCodigo()).equals(postagem));
	}
	
	@Ignore
	@Test
	public void deletar() {
		
		Postagem postagem = new Postagem();
		
		Blog blog = new Blog();

		postagem.setTitulo("HEHEHE");
		postagem.setConteudo("HIHIHI");
		postagem.setBlog(blog);
	

		jdp.criar(postagem);
		jdp.deletar(postagem);

		assertEquals (null, jdp.consultar(postagem.getCodigo()));
	}
	
	@Test
	public void getList() {
		
		List<Postagem> listaPostagem = new ArrayList<Postagem>();
		
		Postagem postagem1 = new Postagem();
		Postagem postagem2 = new Postagem();
		
		Blog blog = new Blog();
		
		postagem1.setTitulo("RIARIA");
		postagem1.setConteudo("abcdefghijk");
		postagem1.setBlog(blog);
		
		
		postagem2.setTitulo("Prog web");;
		postagem2.setConteudo("gerfdte");;
		postagem2.setBlog(blog);;
	
				
		jdp.criar(postagem1);
		jdp.criar(postagem2);
		
		
		listaPostagem.add(postagem1);
		listaPostagem.add(postagem2);
		
		
		
		List<Postagem> listaPostagemRetornada = jdp.getList();
		int ig = 0;
		
		for (Postagem p : listaPostagem)
			for (Postagem pRetornada : listaPostagemRetornada)
				if (p.equals(pRetornada))
					ig++;
		 assertEquals(2, ig);
	}
		
	

}
