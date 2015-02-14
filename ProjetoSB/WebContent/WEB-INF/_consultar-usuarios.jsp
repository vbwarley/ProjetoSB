<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ProjetoSB/web/consultar_usuario.jsp" method="post">
	<fieldset>
		<legend>Consulta</legend>
		Busca por blogs <input type="text" name="match"
			placeholder="Digite aqui a pesquisa"><br>
		<hr>
		Opções de pesquisa: <br>
		<fieldset>
			<input type="radio" name="atributo" value="nome"> Nome <br>
			<input type="radio" name="atributo" value="login"> Login<br>
			<input type="radio" name="atributo" value="e-mail"> E-mail <br> 
		<hr>
			<input type="radio" name="order" value="asc"> Ordem crescente
			<br> <input type="radio" name="order" value="desc">
			Ordem decrescente <br>
		<hr>
			Começar a partir de: <input type="number" name="offset"><br>
			Número máximo de entradas: <input type="number" name="maxEntries"><br>
		</fieldset>
		<fieldset>
			<input type="radio" name="atributo" value="data"> Data <br>
			De: <input type="date" name="from"><br>
			A: <input type="date" name="to"><br>
			<hr>
			<input type="radio" name="orderData" value="asc"> Ordem crescente
			<br> <input type="radio" name="orderData" value="desc"> Ordem decrescente <br>
			<hr> 
			Começar a partir de: <input type="number" name="offsetData"><br> 
			Número máximo de entradas: <input type="number" name="maxEntriesData"><br>
		</fieldset>
		<input type="submit" value="Pesquisar"><br>

	</fieldset>
</form>

</body>
</html>
