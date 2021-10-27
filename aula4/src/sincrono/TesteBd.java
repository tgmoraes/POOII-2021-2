package sincrono;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteBd {
	public static void main(String[] args) throws SQLException {
		//estabelecer uma conexão
		String bd = "teste";
		String host = "localhost";
		String porta = "5432";
		String login = "postgres";
		String senha = "postgres";
		String driver = "postgresql";
		String urlCon = String.format("jdbc:%s://%s:%s/%s", driver,host,porta,bd);
		Connection con = 	DriverManager.getConnection(urlCon,login,senha);
		
		//rodar queries
		String nome = "João";
		String sql = "INSERT INTO pessoa (nome) VALUES (?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		System.out.println(pstm);
		pstm.setString(1, nome);
		System.out.println(pstm);
		
		System.out.println(pstm.execute());
		
		con.close();
	}
}


//PostgreSQL
//MySQL
//Oracle
//SQLite