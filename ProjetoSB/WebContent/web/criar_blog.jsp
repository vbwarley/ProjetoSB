<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar blog</title>
</head>
<body>

<form action="criarBlog" method="post">
<fieldset>
<legend>Crair blog</legend>
Titulo: <input type="text" name="titulo" placeholder="Titulo do blog"><br>
Descri��o: <input type="text" name="descricao" placeholder="Descri��o do blog"><br>
Imagem de backgoround: <input type="file" name="background"><br>
Autorizar coment�rios<br>
<input type="radio" name="autz_comment" value="true"> Autorizar 
<input type="radio" name="autz_comment" value="false"> N�o autorizar <br>
Autorizar coment�rios an�nimos<br>
<input type="radio" name="autz_comment_annon" value="true"> Autorizar 
<input type="radio" name="autz_comment_annon" value="false"> N�o autorizar <br>
<input type="submit" value="Criar">
</fieldset>
</form>

</body>
</html>