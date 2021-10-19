package aula4.assincrono;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class testeBD {
	public static void insert (Connection con) throws SQLException {
		Empregado emp = new Empregado("Jouse ze Moura","zeszinnho@gmail.com");
		
		String query = "INSERT INTO empregado (nome, email, datanasc, salario)"
				+ " VALUES (?,?,?,?)";
		var pstm = con.prepareStatement(query);
		System.out.println(pstm);
		pstm.setString(1,emp.getNome());
		pstm.setString(2, emp.getEmail());
		pstm.setObject(3, emp.getDataNascimento());
		pstm.setObject(4,  emp.getSalario());
		System.out.println(pstm);
		pstm.execute();
		pstm.close();
		
		
	}
	
	public static void select (Connection con) throws SQLException{
		String query = "SELECT * FROM empregado";
		var lista = new ArrayList<Empregado>();
		
		PreparedStatement pstm = con.prepareStatement(query);
		ResultSet resposta = pstm.executeQuery();
		//loop
		while(resposta.next()) {
			var emp = new Empregado(resposta.getString("nome"), resposta.getString("email"));
			emp.setId(resposta.getInt("id"));
			emp.setSalario(resposta.getDouble("salario"));
			emp.setDataNascimento(resposta.getObject("datanasc", LocalDate.class));
			lista.add(emp);
		}
		pstm.close();
		resposta.close();
		lista.stream().forEach(System.out::println);
				
	}

	public static void main(String[] args) {
		String bd = "teste";
		String host = "localhost";
		String porta = "5432";
		String login = "postgres";
		String senha = "postgres";
		String driver = "postgresql";
		String urlCon = String.format("jdbc:%s://%s:%s/%s", driver,host,porta,bd);
		Connection con;
		try{
			con  = DriverManager.getConnection(urlCon, login, senha);
		//	insert(con);
			select(con);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		//System.out.println(con);
		
	}

}
