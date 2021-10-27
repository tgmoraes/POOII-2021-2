package aula5.assincrono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpregadoAR {

	private Double salario;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private int id;

	public EmpregadoAR(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
		this.salario = null;
	}

	public EmpregadoAR(double salario, String nome, String email, LocalDate dataNascimento, int id) {
		super();
		this.salario = salario;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.id = id;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s - %s - R$ %f (nascimento:%s)", this.id, this.nome, this.email, this.salario,
				this.dataNascimento);
	}
	// metodos de acesso ao BD

	public void save() {
		String query = "INSERT INTO empregado (nome, email, datanasc, salario) VALUES (?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection()) {
			var pstm = con.prepareStatement(query);
			pstm.setString(1, this.nome);
			pstm.setString(2, this.email);
			pstm.setObject(3, this.dataNascimento);
			pstm.setObject(4, this.salario);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
