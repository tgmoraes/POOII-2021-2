package aula5.sincrono;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection(){
		String url = String.format("jdbc:%s://%s:%s/%s", "postgresql","localhost","5432","teste");
		String login = "postgres";
		String senha = "postgres";
		try{
			return  DriverManager.getConnection(url, login, senha);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
