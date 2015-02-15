<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.util.List"%>

<%
	List<nucleo.model.negocios.Blog> blogsAssinados = (List<nucleo.model.negocios.Blog>) request.getSession(
	true).getAttribute("blogsAssinados");
%>

<jsp:useBean id="usuario_logado" class="nucleo.model.negocios.Usuario"
	scope="session"/>
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
		teste ${fn:length(usuario_logado.blogsPossuidos) }
		<c:forEach items="${usuario_logado.blogsPossuidos }" var="b">
			<a href="mostrar_blog.jsp?id=${b.codigo}">${b.titulo }</a> -> <a href="alterar_blog.jsp?blogId=${b.codigo}">Alterar</a><br>
		</c:forEach>
	</section>
	<hr>
	<section>
		<h3>Configurações de assinaturas</h3>
		<c:choose>
			<c:when test="${fn:length(blogsAssinados) == 0}">
				Você não tem nenhuma assinatura.
			</c:when>
			<c:otherwise>
				<c:forEach items="${blogsAssinados}" var="blog">
					<a href="mostrar_blog.jsp?id=${blog.codigo}">${blog.titulo }</a> -> <a
						href="excluir_assinatura.jsp?blogId=${blog.codigo}">Excluir</a><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<section>
</body>
</html>