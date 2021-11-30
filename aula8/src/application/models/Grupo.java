package application.models;

import java.util.List;

public class Grupo {
	private int id;
	private String nome;
//	private List<Contato> contatos;
	
	
	public Grupo(String nome) {
		this.setNome(nome);
	}
	public Grupo(String nome, int id) {
		this(nome);
		this.setId(id);
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
	@Override
	public String toString() {
		return nome;
	}
}
