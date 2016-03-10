/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.ClienteDAO;
import br.com.jonas.drogaria.domain.Cliente;

/**
 * @author jonascosta
 *
 */

@Path("cliente")
public class ClienteService {
	
	// http://localhost:8081/Drogaria/rest/cliente
	@GET
	public String salvar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listar();
		
		Gson gson = new Gson();
		String clientesJson = gson.toJson(clientes);
		
		return clientesJson;
	}

}
