/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
	

}
