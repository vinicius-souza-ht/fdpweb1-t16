package br.com.fabricadeprogramador.fabricaweb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection criarConexao() {
		
		try {
			
			//Carrega o driver na memória
			Class.forName("org.postgresql.Driver");
			
			//Cria a conexão com o banco de dados
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usuariodb","postgres","postgres");
			
			//retorna a conexão
			return connection;
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
