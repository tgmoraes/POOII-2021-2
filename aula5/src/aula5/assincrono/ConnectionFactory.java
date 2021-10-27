package aula5.assincrono;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		String bd = "teste";
		String host = "localhost";
		String porta = "5432";
		String login = "postgres";
		String senha = "postgres";
		String driver = "postgresql";
		String urlCon = String.format("jdbc:%s://%s:%s/%s", driver,host,porta,bd);
		
		try{
			return DriverManager.getConnection(urlCon, login, senha);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
