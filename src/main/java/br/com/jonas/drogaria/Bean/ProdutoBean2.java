package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.FabricanteDAO;
import br.com.jonas.drogaria.dao.ProdutoDAO;
import br.com.jonas.drogaria.domain.Fabricante;
import br.com.jonas.drogaria.domain.Produto;
import br.com.jonas.drogaria.util.Constants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {
	
	private List<Produto> produtos;
	private Long codigoProduto;
	private ProdutoDAO produtoDAO;
	private Produto produto;
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDAO;
	
	


	
	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@PostConstruct
	public void iniciar(){
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
	}
	
	public void listar() {
		try {

			// usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(Constants.URL + "/produto");

			String json = webTarget.request().get(String.class);

			Gson gson = new Gson();
			Produto[] vetorProduto = gson.fromJson(json, Produto[].class);
			produtos = Arrays.asList(vetorProduto);

			// ProdutoDAO produtoDAO = new ProdutoDAO();
			// produtos = produtoDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Produtos");
			e.printStackTrace();
		}
	}
	
	public void carregarEdicao(){
		try {
			produto = produtoDAO.buscarPorcodigo(codigoProduto);
			
			fabricantes = fabricanteDAO.listar("descricao");
			
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados de edição");		}
	}



}
