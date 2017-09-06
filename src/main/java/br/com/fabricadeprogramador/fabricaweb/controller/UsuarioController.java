package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

@WebServlet(urlPatterns = "/usucontroller")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<Usuario> lista = new ArrayList<Usuario>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String json = "[";
		for (int i = 0; i < lista.size(); i++) {

			json += "{ nome:" + lista.get(i).getNome() + "  , email: " + lista.get(i).getEmail() + "  }";
			if (i < lista.size() - 1)
				json += ",";
		}
		json += "]";

		resp.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Captura os parâmetros vindos da requisição
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		Usuario usuario = new Usuario(nome, email);

		lista.add(usuario);

		resp.getWriter().println("{ nome:" + usuario.getNome() + "  , email: " + usuario.getEmail() + "  } ");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Captura os parâmetros vindos da requisição
		String posicao = req.getParameter("i");
		// capturando o indice do objeto a ser alterado
		Integer i = Integer.parseInt(posicao);

		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		// Acessando o objeto e alterando os dados
		Usuario usu = lista.get(i);
		usu.setNome(nome);
		usu.setEmail(email);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int index = Integer.parseInt(req.getParameter("i"));

		lista.remove(index);
	}

}
