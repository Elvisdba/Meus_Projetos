/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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

	// http://localhost:8081/Drogaria/rest/cliente/codigo
	@Path("{codigo}")
	@GET
	public String buscarPorCodigo(@PathParam("codigo") Long codigo) {

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigo);

		Gson gson = new Gson();
		String clienteJson = gson.toJson(cliente);

		return clienteJson;

	}

}
