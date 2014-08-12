package nucleo.model.persistencia.jdbc;

import java.util.List;
import java.sql.PreparedStatement;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOUsuario;

public class JDBCDAOUsuario extends JDBCDAO implements DAOUsuario<Usuario, String> {

	public JDBCDAOUsuario() {
		abrirConexao();
	}
	@Override
	public void criar(Usuario objeto) {
		String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlA = "INSERT INTO assinatura VALUES (?,?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			PreparedStatement stmtBlog = getConnection().prepareStatement(sqlA);
					
			stmt.setString(1, objeto.getLogin());
			stmt.setString(2, objeto.getSenha());
			stmt.setString(3, objeto.getNome());
			stmt.setString(4, objeto.getEmail());
			stmt.setDate(5, objeto.getDataNascimento());
			stmt.setString(6, objeto.getEndereco());
			stmt.setString(7, objeto.getInteresses());
			stmt.setString(8, objeto.getQuemSouEu());
			stmt.setString(9, objeto.getFilmes());
			stmt.setString(10, objeto.getLivro());
			stmt.setString(11, objeto.getMusicas());
			
			stmt.execute();
			
			for (Blog blog : objeto.getAssinatura()) {
				stmtBlog.setString(1, objeto.getLogin());
				stmtBlog.setString(2, objeto.getSenha());
				stmtBlog.setInt(3, blog.getCodigo());
				
				stmtBlog.execute();
			}
			
		} catch (Exception e) {
			
		}
	}
	

	
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
