package nucleo.model.negocios;

public class MidiaComentario extends Midia {
	
	private ComentarioComposite comentario;

	public ComentarioComposite getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioComposite comentario) {
		this.comentario = comentario;
	}
}
