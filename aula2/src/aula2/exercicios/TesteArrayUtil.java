package aula2.exercicios;

public class TesteArrayUtil {
	public static void main(String[] args) {
		Integer[] vet = {5,9,5,6,3};
		
		System.out.println(ArrayUtil.max(vet));
		System.out.println(ArrayUtil.count(vet,5));
	}
}
