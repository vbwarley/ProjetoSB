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

%>
<jsp:useBean id="blogMostrar" class="nucleo.model.negocios.Blog"
	scope="session" />
<!DOCTYPE html>
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
	<c:forEach items="${postsMostrar}" var="p">
		<section>
		<h3>${p.titulo }</h3>
		<h6>${p.data }</h6>
		<hr>
		<p>${p.conteudo }</p>
		</section>
		<hr>
		<section>
			<h5>Comentários (número)</h5>
			<div>Criação</div>
			<hr>
			<c:choose>
			<c:when test="${fn:length(comentariosMostrar) > 0}">
			<c:forEach items="comentariosMostrar" var="cmt">
				<div>
					<h6>${cmt.usuario.login}</h6>
					<p>
						${c.conteudo}
					</p>
				</div>	
				<hr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<h6>Não há comentários.</h6>
			</c:otherwise>
			</c:choose>
		</section>
	</c:forEach>
	<hr>
</body>
</html>