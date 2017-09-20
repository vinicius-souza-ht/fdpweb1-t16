package br.com.fabricadeprogramador.fabricaweb.repository;

import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

public class TesteUsuarioRepository {

	public static void main(String[] args) {
		Usuario usuario  = new Usuario("Zé Alterado", "zeAlterado@gmail.com");
		
		UsuarioRepositoryBanco repository = new UsuarioRepositoryBanco();
		
		repository.cadastrar(usuario);
		
//		usuario.setId(3);
		
//		repository.alterar(usuario);
		
//		repository.excluir(3);
		
//		Usuario usuRetorno = repository.buscarPorId(1);
		
//		System.out.println(usuRetorno);
		
		List<Usuario> lista;
		
		lista = repository.buscarTodos();
		
		for (Usuario usuario2 : lista) {
			System.out.println(usuario2);
		}
	}
}
