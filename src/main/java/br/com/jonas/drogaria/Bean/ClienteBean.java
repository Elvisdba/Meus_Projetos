package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.drogaria.dao.ClienteDAO;
import br.com.jonas.drogaria.dao.PessoaDAO;
import br.com.jonas.drogaria.domain.Cliente;
import br.com.jonas.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;
	
	
	
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	
	
	//novo
	public void novo() {
		try {
			cliente = new Cliente();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar listar a pessoa");
			e.printStackTrace();
		}
	}
	
	//listar
	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar clientes");
			e.printStackTrace();
		}
	}
	
	//salvar
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			
			novo();
			clientes = clienteDAO.listar("dataDoCadastro");
			Messages.addFlashGlobalInfo("Cliente Armazenado com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar armazenar cliente");
			e.printStackTrace();
		}
	}
	
	//excluir
	public void excluir(ActionEvent evento) {
		try {
			cliente =  (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			
			clientes = clienteDAO.listar();
			Messages.addFlashGlobalInfo("Cliente excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar excluir cliente");
			e.printStackTrace();
		}
	}
	
	//editar
	public void editar(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		
	}

}
