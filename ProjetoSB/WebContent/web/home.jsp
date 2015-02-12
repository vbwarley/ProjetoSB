<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<% List<nucleo.model.negocios.Blog> list = (List<nucleo.model.negocios.Blog>) request.getAttribute("blogs"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página inicial</title>
</head>
<body>
	<header>
		<form action="login.jsp" method="post">
			<fieldset>
				Login: <input type="text" name="login">
				Senha: <input type="text" name="pass">
				<input type="submit" value="Logar">
			</fieldset>
		</form>
			<fieldset>
				Não tem uma conta? <a href="registrar.jsp">Registre-se!</a>
			</fieldset>
	</header>
	<hr>
	<section>
		<h1>Blogs vivos</h1><br>
		
		<c:forEach items="${blogs}" var="blog">
			<c:out value="${blog.titulo }"/>			
		</c:forEach>
	</section>
	<hr>	
</body>
</html>