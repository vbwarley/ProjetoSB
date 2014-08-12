package nucleo.model.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				
		String sql = "INSERT INTO comentario (codigo, titulo, conteudo, login, codigoPai, codigoPostagem) VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			stmt.setString(4, objeto.getLogin());
			
			if (objeto.getCodigoPai() != -1){
				stmt.setInt(5, objeto.getCodigoPai());
			} else { 
				stmt.setString(5, "null");	
			}
			
			stmt.setString(6, String.valueOf(objeto.getCodigoPostagem()));
			
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
		
		try {
		
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				rs.getInt("codigo");
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

}
	
