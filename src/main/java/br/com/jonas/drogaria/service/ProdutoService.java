/**
 * 
 */
package br.com.jonas.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.ProdutoDAO;
import br.com.jonas.drogaria.domain.Produto;

/**
 * @author jonascosta
 *
 */

@Path("produto")
public class ProdutoService {
	
	//http://localhost:8081/Drogaria/rest/produto
	@GET
	public String listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar("descricao");
		
		Gson gson = new Gson();
		String produtosJson = gson.toJson(produtos);
		
		return produtosJson;
	}
	
	//http://localhost:8081/Drogaria/rest/produto
	@POST
	public String salvar(String json) {
		
		//converte json para objeto
		Gson gson = new Gson();
		Produto produto = gson.fromJson(json, Produto.class);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produto = produtoDAO.merge(produto);
		
		//converte ojbeto em Json
		String produtoJson = gson.toJson(produto);
		return produtoJson;
		
		
	}
	

}
