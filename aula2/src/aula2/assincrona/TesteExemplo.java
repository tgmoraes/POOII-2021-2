package aula2.assincrona;

public class TesteExemplo {
	public static void main(String[] args) {
		Exemplo exe = new Exemplo();
		
		String s1 = "ola";
		
		exe.printa(5.98);
		exe.printa(s1);
		
		String s2 = "hi";
		
		System.out.println(exe.mesmoTipo(s1, s2));
		System.out.println(exe.mesmoTipo(s1, 12));
		
		System.out.println(exe.inverso(4));
		
		var max = exe.maior("aabb", "aaac", "aaaaa");
		System.out.println(max);
		
	}
}
