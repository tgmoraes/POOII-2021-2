package aula5.assincrono;



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
		var edao = new EmpregadoDAO();
		var emp = new Empregado("CArlo via DAo", "carlocomdao.@gmail.com");
		emp.setDataNascimento(LocalDate.of(1970, 10, 20));
		emp.setSalario(5000);
		
		//edao.insert(emp);
		//edao.delete(8);
		//edao.delete(9);
		
		var empBd = edao.get(7);
		System.out.println("teste get:");
		
		System.out.println(empBd);
		
		var emps = edao.list(2, 3);
		System.out.println("teste list:");
		emps.stream().forEach(System.out::println);
	}

}
