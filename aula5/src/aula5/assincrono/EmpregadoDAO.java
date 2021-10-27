package aula5.assincrono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpregadoDAO {
	public void insert(Empregado e) {
		String query = "INSERT INTO empregado (nome, email, datanasc, salario) VALUES (?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection()) {
			var pstm = con.prepareStatement(query);
			pstm.setString(1, e.getNome());
			pstm.setString(2, e.getEmail());
			pstm.setObject(3, e.getDataNascimento());
			pstm.setObject(4, e.getSalario());
			pstm.execute();
			pstm.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	public void delete(int id) {
		String query = "DELETE FROM empregado WHERE id = ?";
		try (Connection con = ConnectionFactory.getConnection()) {
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
			pstm.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	public Empregado get(int id) {
		String query = "SELECT * FROM empregado WHERE id = ?";
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet resposta = pstm.executeQuery();
			resposta.next();
			var emp = new Empregado(resposta.getString("nome"), resposta.getString("email"));
			emp.setId(resposta.getInt("id"));
			emp.setSalario(resposta.getDouble("salario"));
			emp.setDataNascimento(resposta.getObject("datanasc", LocalDate.class));
			resposta.close();
			pstm.close();
			return emp;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Empregado> list(int offset, int limit) {
		String query = "SELECT * FROM empregado LIMIT ? OFFSET ?";
		var emps = new ArrayList<Empregado>();
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, limit);
			pstm.setInt(2, offset);
			ResultSet resposta = pstm.executeQuery();
			// loop
			while (resposta.next()) {
				var emp = new Empregado(resposta.getString("nome"), resposta.getString("email"));
				emp.setId(resposta.getInt("id"));
				emp.setSalario(resposta.getDouble("salario"));
				emp.setDataNascimento(resposta.getObject("datanasc", LocalDate.class));
				emps.add(emp);
			}
			resposta.close();
			pstm.close();
			return emps;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
