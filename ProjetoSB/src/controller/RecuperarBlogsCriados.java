package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class RecuperarBlogsCriados
 */
@WebServlet("/web/recuperar_blogs_criados.jsp")
public class RecuperarBlogsCriados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarBlogsCriados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuario_logado");	
		
		int index = 0;
		List<Integer> idsBlogsCriados = null;
		List<Blog> blogsCriados = null;
		Blog b = null;
		int id;
		
		try {
			idsBlogsCriados = new ArrayList<Integer>();
			
			while (true) {
				id = facade.getBlogByLogin(usuario.getLogin(), index);
				
				if (!idsBlogsCriados.contains(id)) {
					idsBlogsCriados.add(id);
					index++;
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			blogsCriados = new ArrayList<Blog>();
			b = new Blog();

			for (Integer bl : idsBlogsCriados) {
				System.out.println(bl);
			}
			
			if (idsBlogsCriados != null)
				for (Integer blogId : idsBlogsCriados) {
					b = new Blog();
					b.setCodigo(blogId);
					b.setTitulo(facade.getBlogInformation(blogId, "titulo"));
					b.setImagemFundo(facade.getBlogInformation(blogId, "background"));
					b.setDescricao(facade.getBlogInformation(blogId, "descricao"));
					b.setAutorizaComentario(Boolean.getBoolean(facade.getBlogInformation(blogId, "autz_comment")));
					b.setAutorizaComentarioAnonimo(Boolean.getBoolean(facade.getBlogInformation(blogId, "autz_comment_annon")));
					b.setUsuario(usuario);
					blogsCriados.add(b);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		usuario.getBlogsPossuidos().addAll(blogsCriados);
		
//		for (Blog blog : blogsCriados) {
//			System.out.println(blog.getCodigo());
//		}
//		
		request.getSession(true).setAttribute("blogsCriados", blogsCriados);
//		request.getRequestDispatcher("/home").include(request, response);
		
		
	}

}
