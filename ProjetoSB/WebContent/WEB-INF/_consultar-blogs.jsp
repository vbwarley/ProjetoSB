<form action="web/consultar_blog.jsp" method="post">
	<fieldset>
		<legend>Consulta</legend>
		Busca por blogs <input type="text" name="match"
			placeholder="Digite aqui a pesquisa"><br>
		<hr>
		Opções de pesquisa: <br>
		<fieldset>
			<input type="radio" name="atributo" value="titulo"> Titulo <br>
			<input type="radio" name="atributo" value="descricao"> Descrição <br>
		</fieldset>
		<fieldset>
			<input type="radio" name="order" value="asc"> Ordem crescente
			<br> <input type="radio" name="order" value="desc">
			Ordem decrescente <br>
		</fieldset>
		<fieldset>
			Começar a partir de: <input type="number" name="offset"><br>
			Número máximo de entradas: <input type="number" name="maxEntries"><br>
		</fieldset>
		<input type="submit" value="Pesquisar"><br>

	</fieldset>
</form>
