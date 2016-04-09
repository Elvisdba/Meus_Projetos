/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	public String listar() {
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

	// verificar atributo data
	// http://localhost:8081/Drogaria/rest/cliente/{codigo}
	// http://localhost:8081/Drogaria/rest/cliente/5
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigo);
		
		clienteDAO.excluir(cliente);

		Gson gson = new Gson();
		String clienteJson = gson.toJson(cliente);
		return clienteJson;

	}

	// verificar atributo data
	// http://localhost:8081/Drogaria/rest/cliente
	@PUT
	public String editar(String json) {

		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(json, Cliente.class);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.editar(cliente);

		String clienteJson = gson.toJson(cliente);

		return clienteJson;

	}

	// verificar atributo data
	// http://localhost:8081/Drogaria/rest/cliente
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(json, Cliente.class);

		ClienteDAO clienteDAO = new ClienteDAO();	
		clienteDAO.salvar(cliente);

		String clienteJson = gson.toJson(cliente);
		
//		Gson gson = new GsonBuilder().setDateFormat("MM-dd-yyyy").create();
//		Cliente cliente = gson.fromJson(json, Cliente.class);
//		
//		ClienteDAO clienteDAO = new ClienteDAO();	
//		clienteDAO.salvar(cliente);
//		
//		String clienteJson = gson.toJson(cliente);

		return clienteJson;

	}

}
