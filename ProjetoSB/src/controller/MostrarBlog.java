package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class MostrarBlog
 */
@WebServlet("/web/mostrar_blog.jsp")
public class MostrarBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Blog blog = new Blog();
				
		try {
//			int intId = Integer.parseInt(id);
			
//			blog.setDescricao(facade.getBlogInformation(intId, "descricao"));
//			blog.setTitulo(facade.getBlogInformation(intId, "titulo"));
//			
//			Usuario user = new Usuario();
//			user.setLogin(facade.getBlogInformation(intId, "dono"));
//			
//			blog.setUsuario(user);
//			blog.setImagemFundo(facade.getBlogInformation(intId, "background"));
//			blog.setAutorizaComentario(Boolean.getBoolean(facade.getBlogInformation(intId, "autz_comment")));
//			blog.setAutorizaComentarioAnonimo(Boolean.getBoolean(facade.getBlogInformation(intId, "autz_comment_annon")));	
//			blog.setCodigo(intId);
			
			// teste
			Blog b = new Blog();
			b.setCodigo(1212);
			b.setTitulo("xxx");
			b.setDescricao("blablabla");
			b.setImagemFundo("imagens/imagem1.jpg");
			b.setAutorizaComentario(true);
			b.setAutorizaComentarioAnonimo(false);
			
			Usuario user = new Usuario();
			user.setLogin("thew");
			
			b.setUsuario(user);	
					
			request.setAttribute("blog", b);
			request.getRequestDispatcher("/WEB-INF/mostrar_blog.jsp").include(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// criar página de erro mostrar blog
		}
		
		
	}

}
