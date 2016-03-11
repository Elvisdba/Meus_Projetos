package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.FabricanteDAO;
import br.com.jonas.drogaria.domain.Fabricante;


/*
 * protocolo/ip/porta/projeto/rest/path
 */



@Path("fabricante")
public class FabricanteService {

	//http://localhost:8081/Drogaria/rest/fabricante
	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");
		
		Gson gson = new Gson();
		//converte objeto para json
		String Json = gson.toJson(fabricantes);
		
		return Json;
	}
	
	//http://localhost:8081/Drogaria/rest/fabricante/{codigo}
	//http://localhost:8081/Drogaria/rest/fabricante/10   exemplo
	@GET
	@Path("{codigo}")
	public String buscarPorCodigo(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigo);
		
		Gson gson = new Gson();
		String fabricanteJson = gson.toJson(fabricante);
		
		return fabricanteJson;
		
	}
	
	
	//somente para salvar
	//http://localhost:8081/Drogaria/rest/fabricante
	@POST
	public String salvar(String json) {
		
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		
		String fabricanteJson = gson.toJson(fabricante);
		
		return fabricanteJson;
	}
	
	//http://localhost:8081/Drogaria/rest/fabricante
	@PUT
	public String editar(String json){
		
		Gson gson = new Gson();
		//converte json para um objeto
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.editar(fabricante);
		
		String fabricanteJson = gson.toJson(fabricante);
		return fabricanteJson;
	}
	
	//http://localhost:8081/Drogaria/rest/fabricante/{codigo}
	//http://localhost:8081/Drogaria/rest/fabricante/10
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigo);
		
		fabricanteDAO.excluir(fabricante);
		
		Gson gson = new Gson();
		String fabricanteJson = gson.toJson(fabricante);
		return fabricanteJson;
		
	}
	
}
