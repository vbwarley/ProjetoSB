package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class MostrarConfiguracoes
 */
@WebServlet("/web/mostrar_configuracoes.jsp")
public class MostrarConfiguracoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarConfiguracoes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuario_logado");
		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");
		
		List<Integer> idsAssinaturas = null;
		
		try {
			int index = 0;
			idsAssinaturas = new ArrayList<Integer>();
			
			while (true)
				idsAssinaturas.add(facade.getAnnouncement(String.valueOf(sessionId), ++index));
		} catch (Exception e) {
			e.printStackTrace();
			request.getAttribute("assinaturas");
			request.setAttribute("assinaturas", idsAssinaturas);
			request.getRequestDispatcher("/WEB-INF/_mostrar-configuracoes.jsp").include(request, response);
		}
		
		
	}

}
