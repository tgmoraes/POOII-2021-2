package arqs;

import java.io.IOException;
import java.util.List;


public class TestaPessoa {
	public static void main(String[] args) throws IOException {
		Pessoa p = new Pessoa("Tiago",1);
		Pessoa p2 = new Pessoa("Junior", 2);
		
		System.out.println(p);
//		try {
//			p.save();
//			p2.save();
//		} catch (IOException e) {
//			System.out.println("eu chabum ao abrir o arquivo!");
//		}
		
		List<Pessoa> pessoinhas = Pessoa.listar();
		
		System.out.println(pessoinhas);
	}
}
