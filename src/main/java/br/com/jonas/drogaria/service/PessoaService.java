/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.PessoaDAO;
import br.com.jonas.drogaria.domain.Pessoa;

/**
 * @author jonascosta
 *
 */
@Path("pessoa")
public class PessoaService {

	// http://localhost:8081/Drogaria/rest/pessoa
	@GET
	public String listar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> pessoas = pessoaDAO.listar();

		Gson gson = new Gson();
		String pessoasJson = gson.toJson(pessoas);

		return pessoasJson;

	}

	// http://localhost:8081/Drogaria/rest/pessoa
	@POST
	public String salvar(String json) {

		Gson gson = new Gson();
		Pessoa pessoa = gson.fromJson(json, Pessoa.class);

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.merge(pessoa);

		String pessoaJson = gson.toJson(pessoa);

		return pessoaJson;

	}

	// http://localhost:8081/Drogaria/rest/pessoa/{codigo}
	// http://localhost:8081/Drogaria/rest/pessoa/10 exemplo
	@Path("{codigo}")
	@GET
	public String buscaPorCodigo(@PathParam("codigo") Long codigo) {

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigo);

		Gson gson = new Gson();
		String pessoaJson = gson.toJson(pessoa);

		return pessoaJson;

	}
	
	
	//http://localhost:8081/Drogaria/rest/pessoa
	@PUT
	public String editar(String json) {
		
		Gson gson = new Gson();
		Pessoa pessoa = gson.fromJson(json, Pessoa.class);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.editar(pessoa);
		
		String pessoaJson = gson.toJson(pessoa);
		
		return pessoaJson;
		
	}

}
