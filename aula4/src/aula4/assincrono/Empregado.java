package aula4.assincrono;

import java.time.LocalDate;

public class Empregado {
	
	private Double salario;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private int id;
	
	public Empregado(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
		this.salario = null;
	}
	public Empregado(double salario, String nome, String email, LocalDate dataNascimento, int id) {
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
		return String.format("[%d] %s - %s - R$ %f (nascimento:%s)", 
				this.id, this.nome, this.email, 
				this.salario, this.dataNascimento);
	}
}
