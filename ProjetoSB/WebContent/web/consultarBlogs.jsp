<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formul�rio para consulta de blogs</title>
</head>
<body>

<form action="/controller/ConsultarBlog" method="post">
<fieldset>
Busca por blogs
<input type="text" name="match" placeholder="Digite aqui a pesquisa"><br>
<hr>
Op��es de pesquisa: <br>
<fieldset>
<input type="radio" name="atributo" value="titulo"> Titulo <br>
<input type="radio" name="atributo" value="descricao"> Descri��o <br>
</fieldset>
<fieldset>
<input type="radio" name="order" value="asc"> Ordem crescente <br>
<input type="radio" name="order" value="desc"> Ordem decrescente <br>
</fieldset>
<fieldset>
Come�ar a partir de:
<input type="number" name="offset"><br>
N�mero m�ximo de entradas:
<input type="number" name="maxEntries"><br>
</fieldset>
<input type="submit" value="Pesquisar"><br>


</fieldset>
</form>

</body>
</html>