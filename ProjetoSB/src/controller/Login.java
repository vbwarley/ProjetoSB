package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.model.negocios.Usuario;
import fachada.Facade;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.jsp")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Facade facade = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("pass");
		
		try {
			Integer sessionId = facade.doLogin(login, senha);
			
			Usuario u = new Usuario();
			u.setLogin(login);
			u.setSenha(senha);
			u.setNome(facade.getProfileInformation(login, "nome_exibicao"));
			
			String[] st = (facade.getProfileInformation(login, "data_nasc")).split("/");
			u.setDataNascimento(Date.valueOf(st[2] + "-" + st[1] + "-"+ st[0]));
					
			
			// mais
//			u.getBlogsPossuidos().add(arg0);
			
			request.getSession(true).setAttribute("usuario_logado", u);
			request.getSession(true).setAttribute("sessionId", sessionId);
			
//			request.getSession().setAttribute("login", u.getLogin());
//			response.sendRedirect(request.getContextPath() + "/web/recuperar_assinaturas.jsp");
			request.getRequestDispatcher("web/recuperar_assinaturas.jsp").include(request, response);
//			request.getRequestDispatcher("web/recuperar_blogs_criados.jsp").include(request, response);
			response.sendRedirect("home");

//			List<Integer> idsAssinaturas = (List<Integer>) request.getSession().getAttribute("assinaturas");
//			
//			request.getSession().setAttribute("assinaturas", idsAssinaturas);
//			
//			
//			response.sendRedirect(request.getContextPath() + "/home"); // pensar melhor
		} catch (Exception e) {
			response.sendRedirect("web/erro-login.jsp");
		}
		
	}

}
