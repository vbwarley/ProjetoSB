<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuario" class="nucleo.model.negocios.Usuario" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configurações do ${usuario.nome}</title>
</head>
<body>
	<header>
		<h1>Configurações do ${usuario.nome}</h1>
	</header>
	<hr>
	<section>
		<h3>Configurações de conta</h3>
		<div>
			X coisas.
		</div>
	</section>
	<hr>
	<section>
		Configurações de assinaturas
	</section>
</body>
</html>