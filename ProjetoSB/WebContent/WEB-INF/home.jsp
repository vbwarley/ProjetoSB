<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List"%>
<%
	List<nucleo.model.negocios.Blog> list = (List<nucleo.model.negocios.Blog>) request.getAttribute("blogs");
	Integer sessionId = (Integer) request.getSession().getAttribute("sessionId");
	List<nucleo.model.negocios.Blog> blogsAssinados = (List<nucleo.model.negocios.Blog>) request.getSession(true).getAttribute("blogsAssinados");
	List<nucleo.model.negocios.Blog> blogsCriados = (List<nucleo.model.negocios.Blog>) request.getSession(true).getAttribute("blogsCriados");

%>
<jsp:useBean id="usuario_logado" class="nucleo.model.negocios.Usuario"
	scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página inicial</title>
</head>
<body>

	<c:choose>

		<c:when test="${ empty usuario_logado.login  }">
			<header>
				<form action="login.jsp" method="post">
					<fieldset>
						Login: <input type="text" name="login"> Senha: <input
							type="text" name="pass"> <input type="submit"
							value="Logar">
					</fieldset>
				</form>
				<fieldset>
					Não tem uma conta? <a href="web/registrar.jsp">Registre-se!</a>
				</fieldset>
			</header>
		</c:when>
		<c:otherwise>
			<header>
				teste usuario ${usuario_logado.login }

				<h2>Bem vindo ao SuperBlogs, ${usuario_logado.nome}!</h2>
				<a href="mostrar_configuracoes.jsp">Ir para as configurações</a>
				<br> <a href="web/logout.jsp?sessionId=${sessionId}">Log
					out</a>
			</header>
		</c:otherwise>
	</c:choose>
	<hr>

	<c:choose>
		<c:when test="${ empty usuario_logado.login }">
			<section>
				<c:forEach items="${blogs}" var="b">
					<a href="web/mostrar_blog.jsp?id=${b.codigo}">${b.titulo}</a>
				</c:forEach>
			</section>

		</c:when>
		<c:otherwise>
			<section>
				<c:import url="/WEB-INF/_consultar-blogs.jsp" />
			</section>
			<hr>
			<section>
				<c:choose>
					<c:when test="${fn:length(blogsAssinados) > 0}">
						<h4>Assinaturas</h4>
						<c:forEach items="${blogsAssinados}" var="blogAssinado">
							<a href="web/mostrar_blog.jsp?id=${blogAssinado.codigo}">${blogAssinado.titulo}</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						Você não tem assinaturas. teste ${fn:length(blogsAssinados)}
					</c:otherwise>
				</c:choose>
			</section>
			<hr>
			<section>
				<h4>Meus blogs</h4>
				<c:forEach items="${blogsCriados}" var="blogCriado">
					<a href="web/mostrar_blog.jsp?id=${blogCriado.codigo}">${blogCriado.titulo}</a><br>
				</c:forEach>
			</section>

		</c:otherwise>
	</c:choose>
	<hr>

</body>
</html>