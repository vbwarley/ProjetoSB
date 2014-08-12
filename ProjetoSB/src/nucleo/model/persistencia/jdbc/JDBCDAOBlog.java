package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import nucleo.model.negocios.Blog;
import nucleo.model.negocios.ComentarioComposite;
import nucleo.model.negocios.Usuario;
import nucleo.model.persistencia.dao.DAOBlog;

public class JDBCDAOBlog extends JDBCDAO implements DAOBlog<Blog, Integer>{

	@Override
	public void criar(Blog objeto) {
		String sql = "INSERT INTO blog VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getDescricao());
			stmt.setString(4, objeto.getImagemFundo());
			stmt.setBoolean(5, objeto.isAutorizaComentario());
			stmt.setBoolean(6, objeto.isAutorizaComentarioAnonimo());
			
			stmt.execute();
			
		}catch (Exception e) {
			
		}
}

	@Override
	public Blog consultar(Integer id) {
		return null;
	}

	@Override
	public void alterar(Blog objeto) {
		
	}

	@Override
	public void deletar(Blog objeto) {
		
	}

	@Override
	public List<Blog> getList() {
		return null;
	}

}
