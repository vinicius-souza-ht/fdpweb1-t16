package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.fabricaweb.helper.JsonHelper;
import br.com.fabricadeprogramador.fabricaweb.model.Usuario;
import br.com.fabricadeprogramador.fabricaweb.repository.UsuarioRepository;

@WebServlet(urlPatterns = "/usucontroller")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private JsonHelper helper = new JsonHelper();
	
	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json;
		
		try {
			
			json = helper.gerarJsonLista(usuarioRepository.buscarTodos());
			resp.getWriter().print(json);
			
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Captura os parâmetros vindos da requisição
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		Usuario usuario = new Usuario(nome, email);

		usuarioRepository.cadastrar(usuario);

		try {
			resp.getWriter().println(helper.gerarJson(usuario));
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Captura os parâmetros vindos da requisição
		String posicao = req.getParameter("i");
		// capturando o indice do objeto a ser alterado
		Integer id = Integer.parseInt(posicao);

		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		// Acessando o objeto e alterando os dados
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setEmail(email);

		usuarioRepository.alterar(usuario);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int index = Integer.parseInt(req.getParameter("i"));

		usuarioRepository.excluir(index);
//		lista.remove(index);
	}

}
