<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    Integer id = (Integer) request.getSession().getAttribute("id");
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Alterar informa��es do blog </title>
</head>
<body>

	<form action="/web/alterarBlog" method="post">
	<fieldset>
		<legend>Nova informa��o</legend>
		Valor: <input type="text" name="valor" placeholder="Digite aqui a nova informa��o"><br>
		<hr>
		Op��es de altera��o: <br>
		<fieldset>
		<legend>Atributo a ser modificado</legend>
			<input type="radio" name="atributo" value="titulo"> Titulo <br>
			<input type="radio" name="atributo" value="descricao"> Descri��o <br>
			<input type="radio" name="atributo" value="background"> Imagem de Background <input type="file" name="valorImagem"><br>
			<fieldset>
			<input type="radio" name="atributo" value="autz_comment"> Autorizar coment�rios? <br>
			<input type="radio" name="autz_comment" value="true"> Autorizar 
			<input type="radio" name="autz_comment" value="false"> N�o autorizar <br>
			</fieldset>
			<fieldset>
			<input type="radio" name="atributo" value="autz_comment_annon"> Autorizar coment�rios an�nimos?<br>
			<input type="radio" name="autz_comment_annon" value="true"> Autorizar 
			<input type="radio" name="autz_comment_annon" value="false"> N�o autorizar <br>
			</fieldset>			
		</fieldset>
		<input type="hidden" name="id" value="${id}"><br>
		<input type="submit" value="Alterar"><br>

	</fieldset>
</form>
	

</body>
</html>