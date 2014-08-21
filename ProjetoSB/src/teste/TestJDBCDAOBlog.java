package teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.persistencia.jdbc.JDBCDAOBlog;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJDBCDAOBlog {
	
	private JDBCDAOBlog jdb;
	
	@Before
	public void before() {
		jdb = new JDBCDAOBlog();
	}
	
	
	@Test
	public void criar() {
		Blog blog = new Blog();

		blog.setTitulo("PLL");
		blog.setDescricao("Melhor série");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		

		jdb.criar(blog);

		assertEquals(true, jdb.consultar(blog.getCodigo()).equals(blog));
	}
	
	@Ignore
	@Test
	public void Consultar(){
		Blog blog = new Blog();
		
		blog.setTitulo("Medicina");
		blog.setDescricao("Saúde");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		
        jdb.criar(blog);
		
		assertEquals(true, jdb.consultar(blog.getCodigo())
				.equals(blog));
	}
	
	@Ignore
	@Test
	public void alterar() {
		Blog blog = new Blog();

		blog.setTitulo("Arrow");
		blog.setDescricao("Série de ação");
		blog.setImagemFundo("img.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);

		jdb.criar(blog);
		
		blog.setTitulo("A culpa é das estrelas");
		blog.setDescricao("Livro de romance");
		blog.setImagemFundo("imgg.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		
		jdb.alterar(blog);

		assertEquals(true, jdb.consultar(blog.getCodigo()).equals(blog));
}
	@Ignore
	@Test
	public void deletar() {
	      
		Blog blog = new Blog();

		blog.setTitulo("Saúde");
		blog.setDescricao("Ficar em forma");
		blog.setImagemFundo("img1.jpg");
		blog.setAutorizaComentario(true);
		blog.setAutorizaComentarioAnonimo(true);
		
		jdb.criar(blog);
		jdb.deletar(blog);

		assertEquals(null, jdb.consultar(blog.getCodigo()));

}
	@Ignore
	@Test
	public void getList() {
		List<Blog> listaBlog = new ArrayList<Blog>();

		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		Blog blog3 = new Blog();
		Blog blog4 = new Blog();

		blog1.setTitulo("ABC");
		blog1.setDescricao("Alfabto");
		blog1.setImagemFundo("img1.jpg");
		blog1.setAutorizaComentario(true);
		blog1.setAutorizaComentarioAnonimo(true);
		
		blog2.setTitulo("Facebook");
		blog2.setDescricao("Rede social");
		blog2.setImagemFundo("img2.jpg");
		blog2.setAutorizaComentario(true);
		blog2.setAutorizaComentarioAnonimo(true);

		blog3.setTitulo("Instagram");
		blog3.setDescricao("Rede social");
		blog3.setImagemFundo("img3.jpg");
		blog3.setAutorizaComentario(true);
		blog3.setAutorizaComentarioAnonimo(true);
		
		blog4.setTitulo("WhatsAPP");
		blog4.setDescricao("Rede social");
		blog4.setImagemFundo("img4.jpg");
		blog4.setAutorizaComentario(true);
		blog4.setAutorizaComentarioAnonimo(true);
		
		jdb.criar(blog1);
		jdb.criar(blog2);
		jdb.criar(blog3);
		jdb.criar(blog4);
		
		
		listaBlog.add(blog1);
		listaBlog.add(blog2);
		listaBlog.add(blog3);
		listaBlog.add(blog4);
		
		List<Blog> listaBlogRetornada = jdb.getList();
		int iguais = 0;
		
		for (Blog b : listaBlog)
			for (Blog bRetornado : listaBlogRetornada)
				if (b.equals(bRetornado))
					iguais++;
		
		
		/* Ao rodar o teste completo, teremos 6 registros no banco. 
		 * No entanto, precisamos igualar apenas quatro para que seja possível 
		 * confirmar que o método funciona corretamente. 
		 */
		 assertEquals(4, iguais);
	}
	
}