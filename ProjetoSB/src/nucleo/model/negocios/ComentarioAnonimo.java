package nucleo.model.negocios;

public class ComentarioAnonimo extends ComentarioComposite {
	
	public ComentarioAnonimo() {
		Usuario usuario = new Usuario();
		usuario.setLogin("Anônimo");
		usuario.setNome("Anônimo");
	}
}
