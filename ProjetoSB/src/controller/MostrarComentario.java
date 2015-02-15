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
@WebServlet("/mostrarComentario")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Blog b = (Blog) request.getSession(true).getAttribute("blog");
		Postagem p = (Postagem) request.getSession(true).getAttribute("posts");
		
		List<Integer> idsComentarios = null;
		List<ComentarioNormal> comentarios = null;
		
		int index;
		
		try {
			index = 0;
			idsComentarios = new ArrayList<Integer>();

			while (true)
				idsComentarios.add(facade.getComment(p.getCodigo(), index++));

		} catch (Exception e) {
			e.printStackTrace();
			
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			request.getSession(true).setAttribute("blog", b);
			request.getSession(true).setAttribute("posts", p);
			request.getSession(true).setAttribute("comentarios", comentarios);
			response.sendRedirect(request.getContextPath() + "/mostrar_blog.jsp");
		}
		
	}

}
