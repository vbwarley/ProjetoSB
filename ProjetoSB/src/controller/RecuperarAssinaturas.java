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
 * Servlet implementation class RecuperarAssinaturas
 */
@WebServlet("/web/recuperar_assinaturas.jsp")
public class RecuperarAssinaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperarAssinaturas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		String login = request.getParameter("login");

		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");

		// ids dos blogs
		List<Integer> idsAssinaturas = null;

		int index;

		try {
			index = 0;
			idsAssinaturas = new ArrayList<Integer>();

			while (true)
				idsAssinaturas.add(facade.getAnnouncement(String.valueOf(sessionId), index++));

		} catch (Exception e) {
			e.printStackTrace();

			request.getSession(true).setAttribute("assinaturas", idsAssinaturas);
//			response.sendRedirect("recuperar_blogs_criados.jsp");
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
