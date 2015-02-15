package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Facade;

/**
 * Servlet implementation class CriarPost
 */
@WebServlet("/web/criarPost")
public class CriarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarPost() {
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

		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");
		
		String blogId = request.getParameter("blogId");
		String titulo = request.getParameter("titulo");
		String texto = request.getParameter("texto");
		
		try {
			facade.createPost(String.valueOf(sessionId), blogId, titulo, texto);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
