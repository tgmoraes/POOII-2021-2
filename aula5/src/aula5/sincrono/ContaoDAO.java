package aula5.sincrono;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ContaoDAO implements DAO<Contato> {

	@Override
	public void insert(Contato obj) {
		String sql = "INSERT INTO contato (nome, telefone, email, datanasc) "
				+ "VALUES (?,?,?,?) RETURNING id";
		try(Connection con = ConnectionFactory.getConnection()){
			var pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getNome());
			pstm.setString(2, obj.getTelefone());
			pstm.setString(3, obj.getEmail());
			pstm.setObject(4, obj.getDataNascimento());
			
			var rs = pstm.executeQuery();
			rs.next();
			obj.setId(rs.getInt("id"));
			rs.close();
			pstm.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM contato WHERE id=?";
		try(Connection con = ConnectionFactory.getConnection()){
			var pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
			pstm.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Contato obj) {
		String sql = "UPDATE contato SET nome=?, email=?, telefone=?, datanasc=? "
				+ "WHERE id =?";
		try(Connection con = ConnectionFactory.getConnection()){
			var pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getNome());
			pstm.setString(2, obj.getEmail());
			pstm.setString(3,  obj.getTelefone());
			pstm.setObject(4, obj.getDataNascimento());
			pstm.setInt(5, obj.getId());
			pstm.execute();
			pstm.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Contato> list() {
		try(Connection con = ConnectionFactory.getConnection()){
			//roda query select
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Contato get(int id) {
		try(Connection con = ConnectionFactory.getConnection()){
			//roda query select com where
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
