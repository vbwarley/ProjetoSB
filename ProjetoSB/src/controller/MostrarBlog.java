package controller;

import java.io.IOException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String blogId = request.getParameter("id");
		int intBlogId = Integer.parseInt(blogId);
		Usuario u = null;

		try {
			Blog b = new Blog();
			b.setCodigo(intBlogId);
			b.setTitulo(facade.getBlogInformation(intBlogId, "titulo"));
			b.setImagemFundo(facade.getBlogInformation(intBlogId, "background"));
			b.setDescricao(facade.getBlogInformation(intBlogId, "descricao"));
			b.setAutorizaComentario(Boolean.getBoolean(facade.getBlogInformation(intBlogId, "autz_comment")));
			b.setAutorizaComentarioAnonimo(Boolean.getBoolean(facade
					.getBlogInformation(intBlogId, "autz_comment_annon")));

			u = new Usuario();
			u.setLogin(facade.getBlogInformation(intBlogId, "dono"));

			b.setUsuario(u);

			System.out.println("perto de sair do mb");
			request.getSession(true).setAttribute("blogMostrar", b);
			request.getRequestDispatcher("mostrar_post.jsp").include(request, response);
			response.sendRedirect("blog_mostrar.jsp");
			// request.getRequestDispatcher("mostrar_comentario.jsp").include(request,
			// response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			response.sendRedirect("erro-mostrar_blog.jsp");
		}

	}

}
