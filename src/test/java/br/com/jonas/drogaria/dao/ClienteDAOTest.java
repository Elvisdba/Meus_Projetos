package br.com.jonas.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Cliente;
import br.com.jonas.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws ParseException{
		//salvando uma pessoa como cliente
		Long codigoPessoa = 10L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoPessoa);
		
		if (pessoa == null) {
			System.out.println("Pessoa nao encontrada");
		} else {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			
			cliente.setDataDoCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2015"));
			cliente.setLiberado(true);
			cliente.setPessoa(pessoa);
			
			clienteDAO.salvar(cliente);
			System.out.println("cliente salvo com sucesso");
			
		}
	}
	
	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listar();
		
		if (clientes == null) {
			System.out.println("Nao ha clientes cadastrados");
		} else {
			for (Cliente cliente : clientes) {
				System.out.println("");
				System.out.println("Nome do cliente: " + cliente.getPessoa().getNome());
				System.out.println("Data de cadastro: " + cliente.getDataDoCadastro());
				System.out.println("Status: " + cliente.getLiberado());
			}
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoBusca = 14L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigoBusca);
		
		if (cliente == null) {
			System.out.println("Cliente nao encontrado");
		} else {
			System.out.println("Nome do cliente: " + cliente.getPessoa().getNome());
			System.out.println("Data de cadastro: " + cliente.getDataDoCadastro());
			System.out.println("Status: " + cliente.getLiberado());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoParaExcluir = 15L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigoParaExcluir);
		
		if (cliente == null) {
			System.out.println("Cliente nao encontrado para exclusao");
		} else {
			clienteDAO.excluir(cliente);
			System.out.println("Cliente: " + cliente.getPessoa().getNome() + " excluido com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCliente = 14L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigoCliente);
		
		Long codigoPessoa = 8L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoPessoa);
		
		if (cliente == null || pessoa == null) {
			System.out.println("Cliente ou Pessoa nao encontrado");
		} else {
			cliente.setLiberado(true);
			clienteDAO.editar(cliente);
			System.out.println("Cliente alterado com sucesso");
			
		}
	}
	

}
