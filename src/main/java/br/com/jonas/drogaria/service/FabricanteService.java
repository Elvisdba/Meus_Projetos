package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
		String Json = gson.toJson(fabricantes);
		
		return Json;
	}
	
	//http://localhost:8081/Drogaria/rest/fabricante/Numero-codigo
	@GET
	@Path("{codigo}")
	public String buscarPorCodigo(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigo);
		
		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		
		return json;
		
	}
	
	//http://localhost:8081/Drogaria/rest/fabricante
	@POST
	public String salvar(String json) {
		
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
		
		String fabricanteJsonRetorno = gson.toJson(fabricante);
		
		return fabricanteJsonRetorno;
	}
	
	
	
	
}
