<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	List<nucleo.model.negocios.Postagem> postsMostrar = (List<nucleo.model.negocios.Postagem>) request
			.getSession(true).getAttribute("postsMostrar");
	List<nucleo.model.negocios.ComentarioNormal> comentariosMostrar = (List<nucleo.model.negocios.ComentarioNormal>) request
			.getSession(true).getAttribute("comentariosMostrar");

	List<nucleo.model.negocios.Assinatura> assinaturas = (List<nucleo.model.negocios.Assinatura>) request
			.getSession(true).getAttribute("assinaturas");
%>
<jsp:useBean id="blogMostrar" class="nucleo.model.negocios.Blog"
	scope="session" />
<jsp:useBean id="usuario_logado" class="nucleo.model.negocios.Usuario"
	scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog ${blogMostrar.titulo }</title>
</head>
<body background="${blogMostrar.imagemFundo }">

	<h1>Blog ${blogMostrar.titulo}</h1>
	<h2>de ${blogMostrar.usuario.login }</h2>
	<hr>
	<p>${blogMostrar.descricao }</p>
	<c:choose>
		<c:when test="${fn:contains(assinaturas, blogMostrar.codigo)}">
			<a href="criar_assinatura.jsp?id=${blogMostrar.codigo}">Assinar</a>
			<br>
		</c:when>
		<c:otherwise>
			<a href="mostrar_configuracoes.jsp">Ir para configurações de
				conta</a>
			<br>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty usuario_logado.login}">
		<a href="criar_post.jsp">Criar um novo post</a>
	</c:if>
	<hr>
	<c:forEach items="${postsMostrar}" var="p">
		<section>
			<h3>${p.titulo }</h3>
			<h6>${p.data }</h6>
			<hr>
			<p>${p.conteudo }</p>
		</section>
		<hr>
		<section>
			<a href="mostrar_comentario.jsp?postId=${p.codigo}">Ver	comentários</a>
			<hr>
		</section>
	</c:forEach>
	<hr>
</body>
</html>