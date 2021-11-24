package application.dao;

import application.models.Grupo;

public class TesteGrupoDao {

	public static void main(String[] args) {
		Grupo g1 = new Grupo ("grupo novo 1");
		Grupo g2 = new Grupo ("grupo novo 2");
		var gdao = new GrupoDAO();
		
		gdao.insert(g1);
		gdao.insert(g2);
		int id = g2.getId();
		
		var grupos = gdao.list();
		System.out.println(grupos.get(0).getNome().equals(g1.getNome()));
		System.out.println(grupos.get(1).getId() == id);
		
		String novoNome = "grupo novo 2 alterado";
		g2.setNome(novoNome);
		gdao.update(g2);
		System.out.println(gdao.get(id).getNome().equals(novoNome));
		gdao.delete(id);
		System.out.println(gdao.get(id)==null);
		gdao.delete(g1.getId());
		System.out.println(gdao.list().size()==0);
	}

}
