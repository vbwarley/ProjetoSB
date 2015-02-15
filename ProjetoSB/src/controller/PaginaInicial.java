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
 * Servlet implementation class RecuperarBlogServlet
 */
@WebServlet("/home")
public class PaginaInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade fachada;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaginaInicial() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		fachada = new Facade();
		List<Integer> ids = fachada.getBlogs();

		List<Blog> blogs = new ArrayList<Blog>();
		Blog blog = null;

		try {
			for (int i = 0; i < ids.size(); i++) {
				blog = new Blog();
				blog.setCodigo(ids.get(i));
				blog.setTitulo(fachada.getBlogInformation(ids.get(i), "titulo"));
				blogs.add(blog);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Blog> blogsAssinados = null;
		Blog b = null;

		List<Integer> as = (List<Integer>) request.getSession(true).getAttribute("assinaturas");

		try {

			blogsAssinados = new ArrayList<Blog>();
			
			if (as != null)
				for (Integer blogId : as) {
					b = new Blog();
					b.setCodigo(blogId);
					b.setTitulo(fachada.getBlogInformation(blogId, "titulo"));
					blogsAssinados.add(b);
				}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		request.setAttribute("blogs", blogs);
		request.getSession(true).setAttribute("blogsAssinados", blogsAssinados);
		
		if (request.getSession().getAttribute("usuario_logado") != null) {
			request.getRequestDispatcher("web/recuperar_blogs_criados.jsp").include(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/home.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
