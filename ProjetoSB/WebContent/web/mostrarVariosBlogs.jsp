<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<% List<nucleo.model.negocios.Blog> list = (List<nucleo.model.negocios.Blog>) request.getAttribute("blogs"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Resultados da consulta: </h1><br>
	<hr>
	<section>		
		<c:forEach items="${blogs}" var="blog">
			<a href="mostrar_blog.jsp?id=${blog.codigo}"><c:out value="${blog.titulo }"/></a>			
		</c:forEach>
	</section>
	<hr>	

</body>
</html>