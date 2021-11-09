package application.dao;

import java.util.List;

public interface DAO<T> {
	public void insert(T obj);
	public void delete(int id);
	public void update(T obj);
	public List<T> list();
	public T get(int id);
}
