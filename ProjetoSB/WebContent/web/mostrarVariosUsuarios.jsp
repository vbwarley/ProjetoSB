<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<% List<nucleo.model.negocios.Usuario> list = (List<nucleo.model.negocios.Usuario>) request.getAttribute("usuarios"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exibição de usuários pesquisados</title>
</head>
<body>
<h1>Resultados da consulta: </h1><br>
	<hr>
	<section>		
		<c:forEach items="${usuarios}" var="usuario">
			<a href="mostrar_usuario.jsp?id=${usuarios.login}"><c:out value="${usuario.nome }"/></a>			
		</c:forEach>
	</section>
	<hr>	

</body>
</html>