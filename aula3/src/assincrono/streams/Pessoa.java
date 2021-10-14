package assincrono.streams;

public class Pessoa {
	private final int id;
	private final String nome;
	private final Sexo sexo;
	public Pessoa(int id, String nome, Sexo sexo) {
		this.nome = nome;
		this.id = id;
		this.sexo = sexo;
	}
	public static Pessoa fromLine(String[] linha) {
		return new Pessoa( 
				Integer.parseInt(linha[0]),
				linha[1],
				Sexo.valueOf(linha[2]));
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Sexo getSexo() {
		return this.sexo;
	}
	@Override
	public String toString() {
		return String.format("(%d) - %s - %s", 
				this.id, this.nome, this.sexo);
	}
}
