package arqs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private int id;
	private String nome;
	private static Path dados;
	public Pessoa(String nome, int id) {
		this.nome = nome;
		this.id = id;
		dados = Paths.get("pasta1", "dados.csv");
	}
	@Override
	public String toString() {
		return this.id+";"+this.nome;
	}
	
	public void save() throws IOException {
		Files.writeString(dados,this.toString()+"\n",StandardOpenOption.APPEND);
	}
	public static List<Pessoa> listar() throws IOException {
		var linhas = Files.readAllLines(dados);
		List<Pessoa> retorno = new ArrayList<Pessoa>();
		for(String linha: linhas) {
			String[] campos = linha.split(";");
			Pessoa p = new Pessoa(campos[1], Integer.parseInt(campos[0]));
			retorno.add(p);
		}
		return retorno;
	}
}
