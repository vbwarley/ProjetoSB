<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>

<%
	List<Integer> assinaturas = (List<Integer>) request.getAttribute("assinaturas");
%>

<jsp:useBean id="usuario_logado" class="nucleo.model.negocios.Usuario" scope="session" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configurações do ${usuario_logado.nome}</title>
</head>
<body>
	<header>
		<h2>Configurações do ${usuario_logado.nome}</h2>
		<a href="/ProjetoSB/home">Home</a>
	</header>
	<hr>
	<section>
		<h3>Configurações de conta</h3>
		<div>X coisas.</div>
	</section>
	<hr>
	<section>
		<h3>Configurações de assinaturas</h3>
		<c:choose>
			<c:when test="${ empty assinaturas }">
				Você não tem nenhuma assinatura.
			</c:when>
			<c:otherwise>
				<c:forEach items="${assinaturas}" var="ass">
					<a href="web/mostrar_blog.jsp?id=${blog.codigo}">${blog.titulo }</a> -> <a href="web/excluir_assinatura.jsp?blogId=${ass.blog.codigo}">Excluir</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</section>
</body>
</html>