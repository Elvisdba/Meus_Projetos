package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.EstadoDAO;
import br.com.jonas.drogaria.domain.Estado;

@Path("estado")
public class EstadoService {

	// http://localhost:8081/Drogaria/rest/estado
	@GET
	public String listar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.listar();

		Gson gson = new Gson();
		String estadosJson = gson.toJson(estados);

		return estadosJson;
	}

	// http://localhost:8081/Drogaria/rest/estado
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Estado estado = gson.fromJson(json, Estado.class);

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

		String estadoJson = gson.toJson(estado);

		return estadoJson;
	}

	// http://localhost:8081/Drogaria/rest/estado/{codigo}
	// http://localhost:8081/Drogaria/rest/estado/10 exemplo
	@Path("{codigo}")
	@GET
	public String buscaPorCodigo(@PathParam("codigo") Long codigo) {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigo);

		Gson gson = new Gson();
		String estadoJson = gson.toJson(estado);

		return estadoJson;
	}
	
	//http://localhost:8081/Drogaria/rest/estado
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Estado estado = gson.fromJson(json, Estado.class);
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.editar(estado);
		
		String estadoJson = gson.toJson(estado);
		
		return estadoJson;
	}
	
	
	
	//http://localhost:8081/Drogaria/rest/estado
	@DELETE
	public String excluir(String json) {
		
		Gson gson = new Gson();
		Estado estado = gson.fromJson(json, Estado.class);
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estado = estadoDAO.buscarPorcodigo(estado.getCodigo());
		
		estadoDAO.excluir(estado);
		
		String estadoJson = gson.toJson(estado);
		
		return estadoJson;
	}

}
