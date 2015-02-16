package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Postagem;
import fachada.Facade;

/**
 * Servlet implementation class MostrarPost
 */
@WebServlet("/web/mostrar_post.jsp")
public class MostrarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Blog b = (Blog) request.getSession(true).getAttribute("blogMostrar");

		List<Integer> idsPosts = null;
		List<Postagem> posts = null;

		int index;
		int id;

		try {
			index = 0;
			idsPosts = new ArrayList<Integer>();

/*			while (true)
				idsPosts.add(facade.getPost(b.getCodigo(), index++));*/

			while (true){

				id = facade.getPost(b.getCodigo(), index);

				if (!idsPosts.contains(id)) {
					idsPosts.add(id);
					index++;
				} 
			}
			
		} catch (Exception e) {
			posts = new ArrayList<Postagem>();

			for (Integer idPost : idsPosts) {

				String titulo = "";
				String texto = "";
				String data_criacao = "";

				try {

					titulo = facade.getPostInformation(idPost, "titulo");
					texto = facade.getPostInformation(idPost, "texto");
					data_criacao = facade.getPostInformation(idPost, "data_criacao");

					Postagem p = new Postagem();
					p.setBlog(b);
					p.setCodigo(idPost);
					p.setConteudo(texto);
					p.setTitulo(titulo);

					String[] st = data_criacao.split("/");
					p.setData(Date.valueOf(st[2] + "-" + st[1] + "-"+ st[0]));
					
					posts.add(p);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			// request.getSession(true).setAttribute("blog", b); n precisa, pq é
			// de session
			request.getSession(true).setAttribute("postsMostrar", posts);
			// request.getRequestDispatcher("mostrar_comentario.jsp").include(request,
			// response);
			// response.sendRedirect("blog_mostrar.jsp");
//			request.getRequestDispatcher("mostrar_comentario.jsp").include(request, response);
		}

	}

}
