package aula2.sincrona;

public class TesteLista {
	public static void main(String[] args) {
		//ListaEncadeada<String> nomes = new ListaEncadeada<>();
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		
		lista.add(5);
		lista.add(8);
		lista.add(3);
		lista.add(-54);
		
		for(int i=0; i< lista.getTamanho() ;i++) {
			System.out.println(lista.get(i));
		}
		
	}
}
