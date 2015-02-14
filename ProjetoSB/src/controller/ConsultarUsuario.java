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
import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class ConsultarUsuario
 */
@WebServlet("/web/consultar_usuario.jsp")
public class ConsultarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarUsuario() {
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

		System.out.println("Chegou!");
		String atributo = request.getParameter("atributo");

		String match = "";
		String order = "";
		String offSet = "";
		String maxEntries = "";

		String loginsAux = "";

		if (atributo.equals("nome")){

			match = request.getParameter("match");
			order = request.getParameter("order");
			offSet = request.getParameter("offSet");
			maxEntries = request.getParameter("maxEntries");

			try {
				loginsAux = facade.findProfileByName(match, order, offSet, maxEntries);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (atributo.equals("login")){
			match = request.getParameter("match");
			order = request.getParameter("order");
			offSet = request.getParameter("offSet");
			maxEntries = request.getParameter("maxEntries");

			try {
				loginsAux = facade.findProfileByLogin(match, order, offSet, maxEntries);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (atributo.equals("e-mail")){
			match = request.getParameter("match");

			try {
				loginsAux = facade.findProfileByEmail(match);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (atributo.equals("data")){
			String from = request.getParameter("from");
			String to = request.getParameter("from");
			order = request.getParameter("orderData");
			offSet = request.getParameter("offSetData");
			maxEntries = request.getParameter("maxEntriesData");

			try {
				loginsAux = facade.findProfileByDateInterval(from, to, order, offSet, maxEntries);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		String loginsAux2 = loginsAux.substring(1, loginsAux.length()-1);

		String[] loginsV = loginsAux2.split(",");

		List<String> logins = new ArrayList<String>();

		for (int i = 0; i < loginsV.length;i ++){
			if (i == 0 ){
				logins.add(loginsV[i]);
			} else {
				logins.add(String.valueOf(loginsV[i].charAt(1)));
			}
		}

		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = null;

		for (int i = 0; i < logins.size(); i++) {
			usuario = new Usuario();
			usuario.setLogin((logins.get(i)));
			try {
				usuario.setNome(facade.getProfileInformation(usuario.getLogin(), "nome_exibicao"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usuarios.add(usuario);
		}

		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/web/mostrarVariosUsuarios.jsp").include(request, response);


	}

}
