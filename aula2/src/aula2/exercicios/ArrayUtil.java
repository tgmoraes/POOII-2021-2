package aula2.exercicios;

public class ArrayUtil {
	public static <T> int count(T[] array, T value) {
		int ret =0;
		for(T elem: array)
			if(elem.equals(value)) ret++;
		return ret;
	}
	public static <T extends Comparable<T>> T max(T[] array) {
		if(array == null || array.length==0) 
			throw new IllegalArgumentException("Array null ou vazio");
		T max = array[0];
		for(T elem: array) {
			if(elem.compareTo(max)>0) max = elem;
		}
		return max;
	}
}
