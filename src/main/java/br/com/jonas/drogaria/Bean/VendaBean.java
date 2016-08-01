package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import br.com.jonas.drogaria.dao.ClienteDAO;
import br.com.jonas.drogaria.dao.FuncionarioDAO;
import br.com.jonas.drogaria.dao.ProdutoDAO;
import br.com.jonas.drogaria.dao.VendaDAO;
import br.com.jonas.drogaria.domain.Cliente;
import br.com.jonas.drogaria.domain.Funcionario;
import br.com.jonas.drogaria.domain.ItemVenda;
import br.com.jonas.drogaria.domain.Produto;
import br.com.jonas.drogaria.domain.Venda;
import br.com.jonas.drogaria.util.Constants;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class VendaBean implements Serializable {
	private Venda venda;
	private List<Produto> produtos;
	private List<ItemVenda> itemVendas;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

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
	public void novo(){
		
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));
			
			itemVendas = new ArrayList<>();
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("erro ao tentar carregar a tela de vendas");
			e.printStackTrace();
		}
		
	}

	@PostConstruct
	public void listar() {
		try {

			itemVendas = new ArrayList<>();
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0"));

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
		} else {
			ItemVenda itemVenda = itemVendas.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();
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

		calcular();
	}

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int i = 0; i < itemVendas.size(); i++) {
			ItemVenda itemVenda = itemVendas.get(i);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getValorParcial()));
		}
	}

	public void finalizar() {

		try {

			venda.setHorario(new Date());
			venda.setCliente(null);
			venda.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Nao foi possivel buscar o funcionario");
			e.printStackTrace();
		}

	}
	
	public void salvar() {
		
		try {
			//signum converte bigdecimal para numero sem virgula
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addFlashGlobalInfo("informe pelo menos um item para venda");
				return;
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itemVendas);
			
			novo();
			
			Messages.addGlobalInfo("Venda salva com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("erro ao tentar salva a venda");
			System.out.println(e);
		}
	}

}
