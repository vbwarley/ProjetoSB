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
		String pId = request.getParameter("postId");

		List<Integer> idsComentarios = null;
		List<ComentarioNormal> comentarios = null;

		int index;
		int id;
		int postId = Integer.parseInt(pId);

		Postagem p = null;
		
		try {
			index = 0;
			idsComentarios = new ArrayList<Integer>();

			while (true) {

				id = facade.getComment(postId, index);

				if (!idsComentarios.contains(id)) {
					idsComentarios.add(id);
					index++;
				}
			}

		} catch (Exception e) {
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
					
					p = new Postagem();
					p.setCodigo(postId);
					p.setTitulo(facade.getPostInformation(postId, "titulo"));
					p.setConteudo(facade.getPostInformation(postId, "texto"));
					
					String data_criacao = facade.getPostInformation(postId, "data_criacao");
					
					String[] st = data_criacao.split("/");
					p.setData(Date.valueOf(st[2] + "-" + st[1] + "-"+ st[0]));
					p.setBlog(b);
					
					c.setPostagem(p);
					c.setUsuario(u);

					comentarios.add(c);

				} catch (Exception e1) {
					// System.out.println(e.getMessage());
					// erro pag criar comment
				}

			}
		}

		request.getSession(true).setAttribute("comentariosMostrar", comentarios);
		request.getSession().setAttribute("p", p);
		response.sendRedirect("post_mostrar.jsp");
		// request.setAttribute("totalComentariosPost",
		// facade.getTotalNumberOfCommentsByPost(varios));
		// request.getRequestDispatcher("blog_mostrar.jsp").forward(request,
		// response);

	}

}
