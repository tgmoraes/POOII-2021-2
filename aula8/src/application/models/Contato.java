package application.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Contato {
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private Grupo grupo;
	
	public Contato() {
		super();
	}
	public Contato(int id, String nome, String email, String telefone, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "nome=" + nome + ", email=" + email + 
				", telefone=" + telefone + ", dataNascimento="
				+ dataNascimento.format(formater);
	}
	public String toLinha() {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String aux = this.id + ";" + this.nome + ";" + this.email + ";" + this.telefone + ";"
				+ this.dataNascimento.format(formater) + "\n";
		return aux;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
}
