package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Facade;

/**
 * Servlet implementation class AlterarBlog
 */
@WebServlet("/web/alterarBlog")
public class AlterarBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarBlog() {
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
        
		String id = request.getParameter("id");
		String atributo = request.getParameter("atributo");
		String valor; 
		
		if (atributo.equals("background")){
			valor = request.getParameter("valorImagem");
		} else if (atributo.equals("autz_comment")){
			valor = request.getParameter("autz_comment");
		} else if (atributo.equals("autz_comment_annon")){
			valor = request.getParameter("autz_comment_annon");
		} else {
			valor = request.getParameter("valor");	
		}
		
		try {
			facade.changeBlogInformation(String.valueOf(sessionId), id, atributo, valor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
