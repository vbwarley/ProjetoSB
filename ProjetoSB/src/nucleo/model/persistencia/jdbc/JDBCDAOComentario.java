package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public void criar(ComentarioComposite objeto) {		
				
		String sql = "INSERT INTO comentario (codigo, titulo, conteudo, login, comentarioPai, postagem) VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			stmt.setString(4, objeto.getUsuario().getLogin());
			
			if (objeto.getComentarioPai().getCodigo() != -1){
				stmt.setInt(5, objeto.getComentarioPai().getCodigo());
			} else { 
				stmt.setString(5, "null");	
			}
			
			stmt.setString(6, String.valueOf(objeto.getPostagem().getCodigo()));
			
			stmt.execute();
			
			
		} catch (Exception e) {
			System.out.println("Erro ao persistir Comentário");
		}
		
		this.fecharConexao();
		
	}

	@Override
	public ComentarioComposite consultar(Integer id) {

		abrirConexao();
		
		String sql = "SELECT * FROM comentario WHERE codigo = " + id +";";
		
		ComentarioComposite comentario = new ComentarioComposite();
		//codigo, titulo, conteudo, login, comentarioPai, Postagem
		
		comentario.setCodigo(id);
		
		try {
		
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));
				comentario.setListaComentarios(getListaComentarios(comentario.getCodigo())); 
				comentario.setPostagem(new JDBCDAOPostagem().consultar(rs.getInt("postagem")));
			}

		} catch (Exception e){
			
		}
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
	
	public List<ComentarioComposite> getListaComentarios(Integer codigo){
		
		String sql = "SELECT * FROM comentario WHERE comentarioPai = " + codigo;
		
		List<ComentarioComposite> lista = new ArrayList<ComentarioComposite>();
		
		//codigo, titulo, conteudo, login, comentarioPai, Postagem
		try {
			
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				ComentarioComposite comentario = new ComentarioComposite();
				
				comentario.setCodigo(rs.getInt("codigo"));
				comentario.setTitulo(rs.getString("titulo"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setUsuario(new JDBCDAOUsuario().consultar(rs.getString("login")));
				comentario.setComentarioPai( new JDBCDAOComentario().consultar(codigo));
				comentario.setPostagem( new JDBCDAOPostagem().consultar(rs.getInt("postagem")));
				
				lista.add(comentario);
								
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar comentarios filhos");
		}
		
		
		return lista;
	}

}
	
