package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Facade;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/web/registrar_usuario")
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String sexo = request.getParameter("sexo");
		String email = request.getParameter("email");
		String data = request.getParameter("datanasc");
		String endereco = request.getParameter("endereco");
		String interesses = request.getParameter("interesses");
		String quem = request.getParameter("quem");
		String filmes = request.getParameter("filmes");
		String musicas = request.getParameter("musicas");
		String livros = request.getParameter("livros");
	
		System.out.println("Veio");
		
		Date date = null;
		
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e1) {
			throw new RuntimeException();
		}
		
		try {
			facade.createProfile(login, senha, nome, email, sexo, date.toString(), endereco, interesses, quem, filmes, musicas, livros);
			facade.doLogin(login, senha);
			response.sendRedirect("home.jsp");
			// pensar melhor
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e);
			request.getRequestDispatcher("registrar.jsp").include(request, response);
			// ...
		}
		
		
	}

}
