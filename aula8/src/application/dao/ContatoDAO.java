package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.models.Contato;
import application.models.Grupo;

public class ContatoDAO implements DAO<Contato> {

	@Override
	public void insert(Contato obj) {
		String sql = "INSERT INTO contato (nome, telefone, email, datanasc, idgrupo) "
				+ "VALUES (?,?,?,?, ?) RETURNING id";
		try(Connection con = ConnectionFactory.getConnection()){
			var pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getNome());
			pstm.setString(2, obj.getTelefone());
			pstm.setString(3, obj.getEmail());
			pstm.setObject(4, obj.getDataNascimento());
			if(obj.getGrupo()!= null) {
				pstm.setInt(5, obj.getGrupo().getId());
			} else pstm.setNull(5, Types.INTEGER);
			
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
		String sql = "UPDATE contato SET nome=?, email=?, telefone=?, datanasc=?, idgrupo= ? "
				+ "WHERE id =?";
		try(Connection con = ConnectionFactory.getConnection()){
			var pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getNome());
			pstm.setString(2, obj.getEmail());
			pstm.setString(3,  obj.getTelefone());
			pstm.setObject(4, obj.getDataNascimento());
			if(obj.getGrupo()!= null) {
				pstm.setInt(5, obj.getGrupo().getId());
			} else pstm.setNull(5, Types.INTEGER);
			pstm.setInt(6, obj.getId());
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
			while(rs.next()) contatos.add(montaObjeto(rs));
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return contatos;
	}
	
	@Override
	public Contato get(int id) {
		String sql = "select c.id, c.nome, email, telefone, datanasc, idgrupo, g.nome as nomegrupo" + 
				" from contato c LEFT JOIN grupo g ON c.idgrupo = g.id" + 
				" WHERE c.id = ?";
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql)){
			pstm.setInt(1, id);
			var rs = pstm.executeQuery();
			if(rs.next()) {
				var contato =  montaObjeto(rs);
				if(rs.getString("nomegrupo")!=null) {
					var grupo = new Grupo(rs.getString("nomegrupo"),rs.getInt("idgrupo"));
					contato.setGrupo(grupo);
				}
				return contato;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	private Contato montaObjeto(ResultSet rs) throws SQLException {
		Contato c = new Contato();
		c.setId(rs.getInt("id"));
		c.setNome(rs.getString("nome"));
		c.setEmail(rs.getString("email"));
		c.setTelefone(rs.getString("telefone"));
		c.setDataNascimento(rs.getObject("datanasc", LocalDate.class));
		return c;
	}
	
	public List<Contato> listByGrupo(int idgrupo){
		List<Contato> contatos = new ArrayList<>();
		String sql = "SELECT id, nome, email, telefone, datanasc FROM contato "
				+ "WHERE idgrupo=?";
		
		try(var con = ConnectionFactory.getConnection(); 
			var pstm = con.prepareStatement(sql)){
			pstm.setInt(1, idgrupo);
			var rs = pstm.executeQuery();
			while(rs.next()) contatos.add(montaObjeto(rs));
			rs.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return contatos;
	}
}
