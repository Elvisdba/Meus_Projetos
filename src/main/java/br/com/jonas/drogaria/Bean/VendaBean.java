package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.jonas.drogaria.domain.ItemVenda;
import br.com.jonas.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class VendaBean implements Serializable {

	private List<Produto> produtos;
	private List<ItemVenda> itemVendas;
	
	

	public List<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	public void setItemVendas(List<ItemVenda> itemVendas) {
		this.itemVendas = itemVendas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			itemVendas = new ArrayList<>();
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8081/Drogaria/rest/produto");
			
			String json = webTarget.request().get(String.class);
			
			Gson gson = new Gson();
			Produto[] vetorProduto = gson.fromJson(json, Produto[].class);
			produtos = Arrays.asList(vetorProduto);
			

			
//			ProdutoDAO produtoDAO = new ProdutoDAO();
//			produtos = produtoDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Produtos");
			e.printStackTrace();
		}
	}
	
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
		int achou = -1;
		for (int i = 0; i < itemVendas.size(); i++) {
			if (itemVendas.get(i).getProduto().equals(produto)) {
				achou = i;
			}
		}
		
		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			itemVenda.setValorParcial(produto.getPreco());
			itemVendas.add(itemVenda);
		}else {
			ItemVenda itemVenda = itemVendas.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
	}
	
	
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for (int i = 0; i < itemVendas.size(); i++) {
			if (itemVendas.get(i).getProduto().equals(itemVenda.getProduto())) {
				achou = i;
			}
		}
		
		if (achou > -1) {
			itemVendas.remove(achou);
		}
		
		
	}
	
}
