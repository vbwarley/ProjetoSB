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
import fachada.Facade;

/**
 * Servlet implementation class RecuperarBlogServlet
 */
@WebServlet("/home")
public class RecuperarBlogs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade fachada;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarBlogs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		fachada = new Facade();
//		int[] ids = fachada.getBlogs();
		
		List<Blog> blogs = new ArrayList<Blog>();
//		Blog blog = null;
		
//		for (int i = 0; i < ids.length; i++) {
//			blog = new Blog();
//			blog.setCodigo(i);
//			blog.setTitulo(fachada.getBlogInformation(i, "titulo"));
//			blogs.add(blog);
//		}
		
		Blog b = new Blog();
		b.setCodigo(1212);
		b.setTitulo("xxx");
		blogs.add(b);
		
		request.setAttribute("blogs", blogs);
		request.getRequestDispatcher("/WEB-INF/home.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
