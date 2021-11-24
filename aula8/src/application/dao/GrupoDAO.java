package application.dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.models.Contato;
import application.models.Grupo;

public class GrupoDAO implements DAO<Grupo> {

	@Override
	public void insert(Grupo obj) {
		String sql = "INSERT INTO grupo (nome) " + "VALUES (?) RETURNING id";
		
		try (var con = ConnectionFactory.getConnection();
			 var pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, obj.getNome());
			pstm.execute();
			var rs = pstm.getGeneratedKeys();
			rs.next();
			obj.setId(rs.getInt(1));
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM grupo WHERE id=?";
		try(var con = ConnectionFactory.getConnection();
			var pstm = con.prepareStatement(sql)){
			pstm.setInt(1, id);
			pstm.execute();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Grupo obj) {
		String sql = "UPDATE grupo SET nome=? WHERE id =?";
		try(var con = ConnectionFactory.getConnection();
			var pstm = con.prepareStatement(sql)){
			pstm.setString(1, obj.getNome());
			pstm.setInt(2, obj.getId());
			pstm.execute();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Grupo> list() {
		var grupos = new ArrayList<Grupo>();
		String sql = "SELECT id, nome FROM grupo";
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql);
			var rs = pstm.executeQuery()){
			while(rs.next()) {
				Grupo c = new Grupo(rs.getString("nome"), rs.getInt("id"));
				grupos.add(c);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return grupos;
	}

	@Override
	public Grupo get(int id) {
		Grupo grupo = null;
		String sql = "SELECT id, nome FROM grupo WHERE id =?";
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql)){
			pstm.setInt(1,id);
			var rs = pstm.executeQuery();
			if(rs.next()) grupo = new Grupo(rs.getString("nome"), rs.getInt("id"));
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return grupo;
	}
	
}
