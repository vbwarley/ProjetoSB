package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Facade;
import nucleo.model.negocios.Usuario;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/registrar_usuario")
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
		
		
		try {
			facade.createProfile(login, senha, nome, email, sexo, data, endereco, interesses, quem, filmes, musicas, livros);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("alguma_pag.jsp");
	}

}
