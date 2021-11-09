package aula5.sincrono;

import java.time.LocalDate;
import java.util.List;

public class TestaContato {
	public static void main(String[] args) {
		Contato c1 = new Contato();
		c1.setNome("Lázaro alterado dnovo");
		c1.setEmail("lazialt@hotmail.com");
		c1.setTelefone("934486678");
		c1.setDataNascimento(LocalDate.of(2005, 12, 19));
		c1.setId(3);
		DAO<Contato> cDao = new ContaoDAO();
		
		cDao.update(c1);
		//cDao.insert(c1);
		//cDao.delete(1);
	}
}
