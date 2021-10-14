package assincrono.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TesteStreams {

	//3.3 - Exercício:  por fim, crie um Stream que processe o arquivo da seguinte maneira: 
	//	retorne uma List<Pessoa> com as pessoas com nomes compostos por ao menos 
	//	2 palavras e do sexo masculino. Essa lista deve estar com os nomes em maiúsculo.
	public static List<Pessoa> exercicio(Path arq) throws IOException{
		return Files.lines(arq)								    //stram de Strings
		    .map( l -> {
		    	String[] vet = l.split(";");
				vet[1] = vet[1].toUpperCase();
				return Pessoa.fromLine(vet);
		    })      										 //stream de Pessoas
		    .filter(p -> p.getNome().split(" ").length>=2)  //stream de pessoas com nome composto
		    .filter(p -> p.getSexo()==Sexo.MASCULINO)
		    .collect(Collectors.toList());
	}
	public static void main(String[] args) throws IOException {
		// implementando uma classe anônima:
		

		Path arq = Paths.get("src", "assincrono","streams", "dados.txt");
		
		//printando as linhas
		Files.lines(arq).forEach(System.out::println);
		System.out.println("--------------------");
		
		//transforma para Pessoa
		//filtra mulheres:
		//filtra nomes com "of"
		//transforma para Lista<Pessoa>
		List<Pessoa> lista = Files.lines(arq)
			.map( l -> Pessoa.fromLine(l.split(";")))
			.filter( p -> p.getSexo()==Sexo.FEMININO)
			.filter( p -> p.getNome().contains("of"))
			.collect(Collectors.toList());
		
		lista.stream().forEach(System.out::println);
			
		
		System.out.println("--------------------");
		//pessoa com o máximo ID
		Optional<Pessoa> maximo = Files.lines(arq)
			.map( l -> Pessoa.fromLine(l.split(";")))
			.max((p1, p2)-> Integer.compare(p1.getId(), p2.getId()));
		
		if(maximo.isPresent())
			System.out.println(maximo.get());
		System.out.println("--------------------");
		
		//get pelo id
		int id =2;
		
		Optional<Pessoa> pes = Files.lines(arq)
			.filter( l -> Integer.parseInt(l.split(";")[0]) == id)
			.map( l -> Pessoa.fromLine(l.split(";")))
			.findFirst();
		if(pes.isPresent())
			System.out.println(pes.get());
		
		System.out.println("------------");
		exercicio(arq).stream().forEach(System.out::println);
		
		Map<Character, Integer> mapa = new HashMap<>();
		
	}
}
