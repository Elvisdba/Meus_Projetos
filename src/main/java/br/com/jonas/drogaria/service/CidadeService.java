package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.CidadeDAO;
import br.com.jonas.drogaria.domain.Cidade;

@Path("cidade")
public class CidadeService {

	// http://localhost:8081/Drogaria/rest/cidade
	@GET
	public String listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar();

		Gson gson = new Gson();
		String cidadesJson = gson.toJson(cidades);

		return cidadesJson;

	}

	// http://localhost:8081/Drogaria/rest/cidade/{codigo}
	// http://localhost:8081/Drogaria/rest/cidade/10 exemplo
	@GET
	@Path("{codigo}")
	public String buscarPorCodigo(@PathParam("codigo") Long codigo) {

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigo);

		Gson gson = new Gson();
		String cidadeJson = gson.toJson(cidade);

		return cidadeJson;
	}

	// http://localhost:8081/Drogaria/rest/cidade
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Cidade cidade = gson.fromJson(json, Cidade.class);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.merge(cidade);

		String clienteJson = gson.toJson(cidade);
		return clienteJson;
	}

	// http://localhost:8081/Drogaria/rest/cidade/{codigo}
	//http://localhost:8081/Drogaria/rest/cidade/2
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigo);

		cidadeDAO.excluir(cidade);

		Gson gson = new Gson();
		String cidadeJson = gson.toJson(cidade);

		return cidadeJson;
	}

	// http://localhost:8081/Drogaria/rest/cidade
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Cidade cidade = gson.fromJson(json, Cidade.class);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.editar(cidade);

		String cidadeJson = gson.toJson(cidade);

		return cidadeJson;

	}

}
