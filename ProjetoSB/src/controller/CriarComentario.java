package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Blog;
import fachada.Facade;

/**
 * Servlet implementation class CriarComentario
 */
@WebServlet("/web/criarComentario")
public class CriarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");
		String sPostId = request.getParameter("postId");
		String texto = request.getParameter("texto");
		
		Blog b = (Blog) request.getSession(true).getAttribute("blogMostrar");
		String blogId = String.valueOf(b.getCodigo());
		
		int postId = Integer.parseInt(sPostId);
		
		try {
			facade.addComment(String.valueOf(sessionId), postId, texto);
			response.sendRedirect("mostrar_blog.jsp?id="+blogId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
