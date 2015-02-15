package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioNormal;
import nucleo.model.negocios.Postagem;
import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class MostrarComentario
 */
@WebServlet("/web/mostrar_comentario.jsp")
public class MostrarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarComentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Blog b = (Blog) request.getSession(true).getAttribute("blogMostrar");
		List<Postagem> posts = (List<Postagem>) request.getSession(true).getAttribute("postsMostrar");

		List<Integer> idsComentarios = null;
		List<ComentarioNormal> comentarios = null;

		int index;

		for (Postagem p : posts) {

			try {
				index = 0;
				idsComentarios = new ArrayList<Integer>();

				while (true)
					idsComentarios.add(facade.getComment(p.getCodigo(), index++));

			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
				
				comentarios = new ArrayList<ComentarioNormal>();

				for (Integer idComentario : idsComentarios) {

					String autor = "";
					String texto = "";

					try {

						autor = facade.getCommentAuthor(idComentario);
						texto = facade.getCommentText(idComentario);
						
						Usuario u = new Usuario();
						u.setLogin(autor);
						u.setNome(facade.getProfileInformation(u.getLogin(), "nome_exibicao"));

						ComentarioNormal c = new ComentarioNormal();
						c.setCodigo(idComentario);
						c.setConteudo(texto);
						c.setPostagem(p);
						c.setUsuario(u);

						comentarios.add(c);

					} catch (Exception e1) {
						System.out.println(e.getMessage());
					}

				}
			}

			request.getSession(true).setAttribute("comentariosMostrar", comentarios);
//			request.setAttribute("totalComentariosPost", facade.getTotalNumberOfCommentsByPost(varios));
//			request.getRequestDispatcher("blog_mostrar.jsp").forward(request, response);

		}

	}

}
