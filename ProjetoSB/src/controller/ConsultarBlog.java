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
 * Servlet implementation class ConsultarBlog
 */
@WebServlet("/web/consultar_blog.jsp")
public class ConsultarBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String atributo = request.getParameter("atributo");
		String match = request.getParameter("match");
		String order = request.getParameter("order");
		String offSet = request.getParameter("offSet");
		String maxEntries = request.getParameter("maxEntries");

		String sIds = "";

		if (atributo.equals("titulo")){
			try {
				sIds = facade.findBlogByName(match, order, offSet, maxEntries);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (atributo.equals("descricao")){
			try {
				sIds = facade.findBlogByDescription(match, order, offSet, maxEntries);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String idsS = sIds.substring(1, sIds.length()-1);
		
		String[] idsV = idsS.split(",");
		
		List<String> ids = new ArrayList<String>();
		
		for (int i = 0; i < idsV.length;i ++){
			if (i == 0 ){
				ids.add(idsV[i]);
			} else {
				ids.add(String.valueOf(idsV[i].charAt(1)));
			}
		}
		
		List<Blog> blogs = new ArrayList<Blog>();
		Blog blog = null;
		
		for (int i = 0; i < ids.size(); i++) {
			blog = new Blog();
			blog.setCodigo(Integer.parseInt(ids.get(i)));
			try {
				blog.setTitulo(facade.getBlogInformation(blog.getCodigo(), "titulo"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			blogs.add(blog);
		}
		
		request.setAttribute("blogs", blogs);
		request.getRequestDispatcher("/web/mostrarVariosBlogs.jsp").include(request, response);


	}

}
