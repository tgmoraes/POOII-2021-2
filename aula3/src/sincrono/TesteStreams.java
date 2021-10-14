package sincrono;

import java.util.Arrays;
import java.util.List;

public class TesteStreams {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(2,7,8,9,5);
		
		//CRiar -> processando (n op)-> operacao final
		
		System.out.println(nums);
		nums.stream()			//criar o stream
			.skip(2)					//pular os dois primeiros elementos
			.filter( y-> y%2==0)		// todo pares
			.forEach( System.out::println);//transforma o stream em lista
		//System.out.println(nums);
	}
	
	
}
