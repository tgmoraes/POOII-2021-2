package arqs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public class TesteArq {
	public static void main(String[] args) throws IOException {
		Path arq = Paths.get("pasta1","dados.csv");
		
		boolean existe = Files.exists(arq);
		
		System.out.println(arq);
		
		Files.writeString(arq, "te\n", StandardOpenOption.WRITE);
		
		List<String> linhas = Files.readAllLines(arq);
		
		System.out.println(linhas);
	}
}
