<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	List<nucleo.model.negocios.ComentarioNormal> comentariosMostrar = (List<nucleo.model.negocios.ComentarioNormal>) request
			.getSession(true).getAttribute("comentariosMostrar");

%>
<jsp:useBean id="blogMostrar" class="nucleo.model.negocios.Blog"
	scope="session" />
<jsp:useBean id="usuario_logado" class="nucleo.model.negocios.Usuario"
	scope="session" />
<jsp:useBean id="p" class="nucleo.model.negocios.Postagem" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${p.titulo }</title>
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
	<hr>
	<section>
		<h3>${p.titulo }</h3>
		<h6>${p.data }</h6>
		<hr>
		<p>${p.conteudo }</p>
	</section>
	<hr>
	<section>
		<h5>Comentários (número)</h5>
		<div>
			<form action="criarComentario">
				<fieldset>
					<legend>Criar comentário</legend>
					Texto: <input type="text" name="texto"
						placeholder="Digite o o texto aqui"><br> <input
						type="hidden" name="postId" value="${p.codigo}"> <input
						type="submit" value="Publicar"><br>
				</fieldset>
			</form>
		</div>
		<hr>
		<c:forEach items="${comentariosMostrar}" var="cmt">
			Usuario: ${cmt.usuario.login}
			Texto: <p>${cmt.conteudo}</p>
			<hr>
		</c:forEach>
	</section>
	<hr>
</body>
</html>