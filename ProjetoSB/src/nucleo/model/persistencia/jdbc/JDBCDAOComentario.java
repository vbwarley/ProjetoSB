package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import nucleo.model.negocios.ComentarioAnonimo;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOComentario;

public class JDBCDAOComentario  extends JDBCDAO implements DAOComentario<ComentarioComposite, Integer>{

	

	public JDBCDAOComentario() {
		abrirConexao();
	}
	
	@Override
	public void criar(ComentarioComposite objeto, Usuario usuario) {		
				
		String sql = "INSERT INTO comentario (codigo, nome, conteudo, Usuario, codigoComentario) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			
			if (usuario != null){
				stmt.setString(4, usuario.getLogin());
			} else { 
				stmt.setString(4, null);
					
			}
			
			if (objeto.getCodigoPai() != -1){
				stmt.setInt(5, objeto.getCodigoPai());
			} else { 
				stmt.setString(5, null);
					
			}
			
			
		} catch (Exception e) {
			
		}
		
		this.fecharConexao();
		
	}

	@Override
	public ComentarioComposite consultar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(ComentarioComposite objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(ComentarioComposite objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ComentarioComposite> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criar(ComentarioComposite objeto) {
		// TODO Auto-generated method stub
		
	}

}
	
