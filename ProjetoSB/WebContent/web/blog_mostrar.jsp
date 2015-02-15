<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%     
    List<nucleo.model.negocios.Postagem> posts = (List<nucleo.model.negocios.Postagem>) request.getSession(true).getAttribute("postsMostrar");
	List<nucleo.model.negocios.ComentarioComposite> comentarios = (List<nucleo.model.negocios.ComentarioComposite>) request.getSession(true).getAttribute("comentariosMostrar");
%>    
<jsp:useBean id="blogMostrar" class="nucleo.model.negocios.Blog" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog ${blogMostrar.titulo }</title>
</head>
<body background="${blogMostrar.imagemFundo }">

<h1>Blog ${blogMostrar.titulo }</h1>
<h2>de ${blogMostrar.usuario.login }</h2>
<hr>
<p>${blogMostrar.descricao }</p>
<hr>
<c:forEach items="${posts}" var="p">
	<h3>${p.titulo }</h3>
	<h6>${p.data }</h6>
	<hr align="center">
	<p>${p.conteudo }</p>
</c:forEach>
<hr>
</body>
</html>