package application.dao;

import java.time.LocalDate;

import application.models.Contato;
import application.models.Grupo;

public class TesteContatoDAO {

	public static void main(String[] args) {
		ContatoDAO cdao = new ContatoDAO();
		GrupoDAO gdao = new GrupoDAO();
		Contato c =cdao.get(4);
		Grupo g = new Grupo("Familia");
		gdao.insert(g);
		c.setGrupo(g);
		
		cdao.update(c);
		
		c =cdao.get(4);
		System.out.println(c.getGrupo().getNome().equals("Familia"));
//		var contatos = cdao.list();
//		var contatoInserted = contatos.get(contatos.size()-1);
//		int id = contatoInserted.getId();
//		System.out.println(contatoInserted.getNome().equals(c.getNome()));
//	//	System.out.println(cdao.delete(id)==true);
////		System.out.println(cdao.delete(-9)==false);
//		System.out.println(cdao.get(id)==null);
	}

}
