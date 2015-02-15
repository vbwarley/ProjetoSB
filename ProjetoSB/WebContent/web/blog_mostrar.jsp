<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
    
<%     
    List<nucleo.model.negocios.Postagem> posts = (List<nucleo.model.negocios.Postagem>) request.getSession(true).getAttribute("postsMostrar");
	List<nucleo.model.negocios.ComentarioComposite> comentarios = (List<nucleo.model.negocios.ComentarioComposite>) request.getSession(true).getAttribute("comentariosMostrar");
%>    
<jsp:useBean id="blog" class="nucleo.model.negocios.Blog" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog ${blog.titulo }</title>
</head>
<body background="${blog.imagemFundo }">

<h1>Blog ${blog.titulo }</h1>
<h2>de ${blog.usuario.login }</h2>
<hr>
<p>${blog.descricao }</p>
<hr>
<p>Aqui ficarão os posts</p>
<hr>
</body>
</html>