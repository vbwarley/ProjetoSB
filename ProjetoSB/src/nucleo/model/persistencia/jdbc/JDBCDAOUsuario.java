package nucleo.model.persistencia.jdbc;

import java.util.List;
import java.sql.PreparedStatement;

import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;

public class JDBCDAOUsuario extends JDBCDAO implements DAOUsuario<Usuario, String> {

	public JDBCDAOUsuario() {
		abrirConexao();
	}
	@Override
	public void criar(Usuario objeto) {
		String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
		} catch (Exception e) {
			
		}
	}
	
	//COMENT�RIO!!!
	
	@Override
	public Usuario consultar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Usuario objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Usuario objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
