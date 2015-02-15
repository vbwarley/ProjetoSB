<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   Integer blogId = (Integer) request.getSession().getAttribute("blogId");
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Criar postagem </title>
</head>
<body>

<form action="/web/criarPost" method="post">
<fieldset>
<legend>Criar postagem</legend>
Titulo: <input type="text" name="titulo" placeholder="Digite o titulo aqui"><br>
Texto: <input type="text" name="texto" placeholder="Digite o o texto aqui"><br>
<input type="hidden" name="blogId" value="${blogId}"><br>
<input type="submit" value="Publicar"><br>
</fieldset>
</form>

</body>
</html>