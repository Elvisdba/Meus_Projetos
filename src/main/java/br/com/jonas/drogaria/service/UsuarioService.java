/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.UsuarioDAO;
import br.com.jonas.drogaria.domain.Usuario;

/**
 * @author jonascosta
 *
 */
@Path("usuario")
public class UsuarioService {

	// http://localhost:8081/Drogaria/rest/usuario
	@GET
	public String listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.listar();

		Gson gson = new Gson();
		String usuarioJson = gson.toJson(usuarios);

		return usuarioJson;

	}

	// http://localhost:8081/Drogaria/rest/usuario/{codigo}
	// http://localhost:8081/Drogaria/rest/usuario/10 exemplo
	@GET
	@Path("{codigo}")
	public String buscarPorCodigo(@PathParam("codigo") Long codigo) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarios = usuarioDAO.buscarPorcodigo(codigo);

		Gson gson = new Gson();
		String usuarioJson = gson.toJson(usuarios);

		return usuarioJson;
	}

	// http://localhost:8081/Drogaria/rest/usuario
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.merge(usuario);

		String usuarioJson = gson.toJson(usuario);
		return usuarioJson;
	}

	// http://localhost:8081/Drogaria/rest/usuario/{codigo}
	// http://localhost:8081/Drogaria/rest/usuario/2
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorcodigo(codigo);

		usuarioDAO.excluir(usuario);

		Gson gson = new Gson();
		String usuarioJson = gson.toJson(usuario);

		return usuarioJson;
	}

	// http://localhost:8081/Drogaria/rest/usuario
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.editar(usuario);

		String usuarioJson = gson.toJson(usuario);

		return usuarioJson;

	}
}
