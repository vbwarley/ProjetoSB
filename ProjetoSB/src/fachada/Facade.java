package fachada;

import easyaccept.EasyAccept;

public class Facade {
	
	public static void main(String[] args) {
		args = new String[] {"fachada.Facade","testes-aceitacao/us01.txt"};
		EasyAccept.main(args);
	}

	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String data_nasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) {
		

	}
	
	public void resetDatabase() {
		
	}
}
