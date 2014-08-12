package nucleo.model.negocios;

import java.util.ArrayList;

public class ComentarioComposite {
	
	private int codigo;
	private String titulo;
	private String conteudo;
	private int codigoPai = -1;
	private ArrayList<ComentarioComposite> listaComentarios;
	
	public ComentarioComposite() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public ArrayList<ComentarioComposite> getListaComentarios() {
		return listaComentarios;
	}

	public int getCodigoPai() {
		return codigoPai;
	}

	public void setCodigoPai(int codigoPai) {
		this.codigoPai = codigoPai;
	}

	public void setListaComentarios(ArrayList<ComentarioComposite> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	
	public void addComentario(ComentarioComposite comentario) {
		// ...
	}
	
	public void removeComentario(ComentarioComposite comentario) {
		// ...
	}
}
