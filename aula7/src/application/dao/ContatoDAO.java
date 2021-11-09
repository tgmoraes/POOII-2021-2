package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.Contato;

public class ContatoDAO implements DAO<Contato> {

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
			System.out.println(pstm);
			pstm.execute();
			pstm.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Contato> list() {
		List<Contato> contatos = new ArrayList<>();
		String sql = "SELECT id, nome, email, telefone, datanasc FROM contato";
		
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql);
			var rs = pstm.executeQuery()){
			
			while(rs.next()) {
				Contato c = new Contato();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setTelefone(rs.getString("telefone"));
				c.setDataNascimento(rs.getObject("datanasc", LocalDate.class));
				contatos.add(c);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return contatos;
	}

	@Override
	public Contato get(int id) {
		String sql = "SELECT in, nome, email, telefone, datanasc FROM contato";
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql);
			var rs = pstm.executeQuery()){
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
