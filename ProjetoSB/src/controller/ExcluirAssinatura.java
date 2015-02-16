package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Facade;

/**
 * Servlet implementation class Excluir
 */
@WebServlet("/web/excluir_assinatura.jsp")
public class ExcluirAssinatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirAssinatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");
		String blogId = request.getParameter("blogId");
		
		try {
			facade.deleteAnnouncement(String.valueOf(sessionId), blogId);
			response.sendRedirect("mostrar_configuracoes.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("erro-excluir_assinatura.jsp");
		}
		
	}


}
