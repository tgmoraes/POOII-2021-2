package aula2.exercicios;

import java.util.ArrayList;
public class TestaLista {

	public static void main(String[] args) {
		ListaVetor<String> l = new ListaVetor<>();
		System.out.println(l);
		l.add("4");
		l.add("6");
		System.out.println(l);		
		l.add("8");
		System.out.println(l);
		l.add("10");
		System.out.println(l);
		l.add("12");
		System.out.println(l);
		l.add("14");
		System.out.println(l);
		l.add("14");
		System.out.println(l);
		l.add("5");
		System.out.println(l);
		l.add("24");
		System.out.println(l);
		l.add("9");
		System.out.println(l);
		l.add("13");
		System.out.println(l);
		l.add("11");
		System.out.println(l);
		l.remove(0);
		l.remove(0);
		l.remove(0);
		l.remove(0);
		System.out.println(l);
		System.out.println("valor 5:"+l.get(5));
		System.out.println("valor tam:"+l.size());
		
		
	}

}
