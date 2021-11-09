package aula2.exercicios;

public interface Lista<T> {
	public void add(T elemento);
	public void add(T elemento, int pos);
	public T get(int pos);
	public T remove(int pos);
	public boolean remove(T elemento);
	public int size();
}
