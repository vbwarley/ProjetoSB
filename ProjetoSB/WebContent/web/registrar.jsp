<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="erro" class="java.lang.Exception"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrar uma nova conta de usuário</title>
</head>
<body>
	<% erro.getMessage(); %>
	<form action="registrar_usuario" method="post">
		<fieldset>
		<legend>Registrando novo usuário</legend>
				Login: <input type="text" name="login"><br>
				Nome: <input type="text" name="nome"><br>
				Senha: <input type="password" name="senha"><br>
				Sexo: <br> 
					<input type="radio" name="sexo" value="Masculino" checked>Masculino
					<br>
					<input type="radio" name="sexo" value="Feminino">Feminino
					<br>
				Email: <input type="text" name="email"><br>
				Data de nascimento: <input type="date" name="datanasc"><br>
				Endereço: <input type="text" name="endereco"><br>
				Interesses: <input type="text" name="interesses"><br>
				Quem sou eu: <input type="text" name="quem"><br>
				Filmes: <input type="text" name="filmes"><br>
				Músicas: <input type="text" name="musicas"><br>
				Livros: <input type="text" name="livros"><br>
				<input type="submit" value="Registrar">
				<button>Cancelar</button>
				
		</fieldset>
	</form>
</body>
</html>