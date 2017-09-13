package br.com.fabricadeprogramador.fabricaweb.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

public class UsuarioRepository {

	private int id = 1;
	private List<Usuario> lista = new ArrayList<>();
	
	public List<Usuario> buscarTodos(){
		return lista;
	}
	
	public void cadastrar (Usuario usuario) {
		usuario.setId(id++);
		lista.add(usuario);
		
		System.out.println("LISTA:");
		for (Usuario usu : lista) {
			System.out.println(usu);
		}
	}
	
	private Usuario buscarPorId(Integer id) {
		
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getId().equals(id)) {
				return lista.get(i);
			}
		}
		
		return null;
	}
	
	public void alterar(Usuario usuario) {
		
		Usuario usuBuscado = buscarPorId(usuario.getId());
		
		usuBuscado.setNome(usuario.getNome());
		usuBuscado.setEmail(usuario.getEmail());
		
	}
	
	public void excluir(int id) {
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getId().equals(id)) {
				lista.remove(i);
				return;
			}
		}
	}
}
