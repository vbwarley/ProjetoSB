package nucleo.model.persistencia.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import nucleo.model.negocios.Postagem;
import nucleo.model.persistencia.dao.DAOPostagem;

public class JDBCDAOPostagem extends JDBCDAO implements
		DAOPostagem<Postagem, Integer> {

	@Override
	public void criar(Postagem objeto) {
		String sql = "INSERT INTO postagem VALUES (?,?,?,?,?)";

		try {

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setInt(1, objeto.getCodigo());
			stmt.setString(2, objeto.getTitulo());
			stmt.setString(3, objeto.getConteudo());
			stmt.setInt(4, objeto.getBlog().getCodigo());

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException();

		}

	}

	@Override
	public Postagem consultar(Integer id) {
		String PostagemSQL = "Select * from postagem where codigo = ?";
		Postagem p = null;

		JDBCDAOBlog b = new JDBCDAOBlog();

		try {
			abrirConexao();

			PreparedStatement stmt = getConnection().prepareStatement(
					PostagemSQL);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Postagem();

				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setConteudo(rs.getString(3));
				p.setBlog(b.consultar(rs.getInt(4)));

			}

		} catch (SQLException e) {
			throw new RuntimeException();

		}

		return p;
	}

	@Override
	public void alterar(Postagem objeto) {

	}

	@Override
	public void deletar(Postagem objeto) {

	}

	@Override
	public List<Postagem> getList() {

		return null;
	}
}
