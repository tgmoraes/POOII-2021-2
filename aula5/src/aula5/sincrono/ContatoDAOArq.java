package aula5.sincrono;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOArq implements DAO<Contato> {
	private Path arquivo;
	private Charset encode;
	private List<Contato> contatos;
	private Integer seq;

	public ContatoDAOArq() {
		Path pasta = Paths.get("src", "aula5", "sincrono");
		this.arquivo = pasta.resolve("contato.csv");
		this.encode = Charset.forName("UTF-8");
		this.contatos = new ArrayList<>();
		try {
			if (!Files.exists(pasta)) {
				Files.createDirectory(pasta);
			}
			if (!Files.exists(this.arquivo)) {
				Files.createFile(this.arquivo);
			}
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException("Erro: problemas ao abrir o arquivo:");
		}
	}

	private int idNextVal() {
		this.leArq();
		this.seq++;
		this.gravaArq();
		return this.seq;
	}

	private void gravaArq() {
		var dados = new ArrayList<String>();
		dados.add(this.seq.toString());
		this.contatos.stream().forEach(e -> dados.add(e.toLinha()));
		try {
			Files.write(this.arquivo, dados, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException("Erro: problemas ao abrir o arquivo:");
		}
	}

	private void leArq() {
		List<String> dadosArquivo;
		this.contatos = new ArrayList<>();
		try {
			dadosArquivo = Files.readAllLines(this.arquivo, this.encode);
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException("Erro: problemas ao abrir o arquivo:");
		}
		this.seq = Integer.parseInt(dadosArquivo.get(0));
		for (int i = 1; i < dadosArquivo.size(); i++) {
			var linha = dadosArquivo.get(i);
			String[] campos = linha.split(";");
			Contato c = new Contato();
			// formato: id;nome;email;telefone;dataNascimento
			c.setId(Integer.parseInt(campos[0]));
			c.setNome(campos[1]);
			c.setEmail(campos[2]);
			c.setTelefone(campos[3]);
			String[] data = campos[4].split("/");
			c.setDataNascimento(
					LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0])));
			contatos.add(c);
		}
	}

	@Override
	public void insert(Contato obj) {
		obj.setId(this.idNextVal());
		String aux = obj.toLinha();
		try {
			Files.writeString(this.arquivo, aux, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException("Erro: problemas ao abrir o arquivo:");
		}
	}

	@Override
	public void delete(int id) {
		this.leArq();
		for (Contato contato : this.contatos) {
			if (contato.getId() == id) {
				this.contatos.remove(contato);
				break;
			}
		}
		this.gravaArq();
	}

	@Override
	public void update(Contato obj) {
		this.leArq();
		for (Contato contato : this.contatos) {
			if (contato.getId() == obj.getId()) {
				contato = obj;
				break;
			}
		}
		this.gravaArq();
	}

	@Override
	public List<Contato> list() {
		this.leArq();
		return this.contatos;
	}

	@Override
	public Contato get(int id) {
		this.leArq();
		for (Contato contato : this.contatos) {
			if (contato.getId() == id) {
				return contato;
			}
		}
		return null;
	}
}
