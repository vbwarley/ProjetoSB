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
//		Integer sessionId = (Integer) request.getSession(true).getAttribute("sessionId");
//		
//		List<Integer> idsAssinaturas = null;
//		
//		List<Blog> blogsAssinados = null;
//		Blog b = null;
//		
//		List<Integer> as = (List<Integer>) request.getSession(true).getAttribute("idsAssinaturas");
//
//		try {
//
//			blogsAssinados = new ArrayList<Blog>();
//			
//			if (as != null)
//				for (Integer blogId : as) {
//					b = new Blog();
//					b.setCodigo(blogId);
//					b.setTitulo(facade.getBlogInformation(blogId, "titulo"));
//					blogsAssinados.add(b);
//				}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("assinaturas", blogsAssinados);
//			request.getRequestDispatcher("/WEB-INF/_mostrar-configuracoes.jsp").include(request, response);
//		}
		
		// alterar blogs
		
		// excluir assinaturas
		Usuario u = (Usuario) request.getSession(true).getAttribute("usuario_logado");
		System.out.println(u.getBlogsPossuidos().size());
		
		request.getRequestDispatcher("/WEB-INF/_mostrar-configuracoes.jsp").forward(request, response);
		
		
	}

}
