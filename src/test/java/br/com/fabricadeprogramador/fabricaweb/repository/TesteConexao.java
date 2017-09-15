package br.com.fabricadeprogramador.fabricaweb.repository;

import java.sql.Connection;

public class TesteConexao {

	public static void main(String[] args) {
		
		Connection conexao = ConexaoFactory.criarConexao();
		
		if(conexao == null) {
			System.out.println("Não Conectou!");
		} else {
			System.out.println("Conectou!");
		}
		
	}
}
