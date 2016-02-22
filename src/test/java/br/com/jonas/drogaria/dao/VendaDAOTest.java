package br.com.jonas.drogaria.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Cliente;
import br.com.jonas.drogaria.domain.Funcionario;
import br.com.jonas.drogaria.domain.Venda;

public class VendaDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Long codigoFuncionario = 11L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorcodigo(codigoFuncionario);
		
		Long codigoCliente = 14L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscarPorcodigo(codigoCliente);
		
		if (funcionario == null || cliente == null) {
			System.out.println("Funcionario ou Cliente nao encontrado");
		} else {
			VendaDAO vendaDAO = new VendaDAO();
			Venda venda = new Venda();
			
			venda.setHorario(new Date());
			venda.setCliente(cliente);
			venda.setFuncionario(funcionario);
			venda.setPrecoTotal(new BigDecimal("78.00"));

			vendaDAO.salvar(venda);
			System.out.println("Venda cadastrada com sucesso");
			
		}
	}
	
	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.listar();
		
		for (Venda venda : vendas) {
			System.out.println("");
			System.out.println("Nome do Cliente: " + venda.getCliente().getPessoa().getNome());
			System.out.println("Horario da venda: " + venda.getHorario());
			System.out.println("Codigo da venda: " + venda.getCodigo());
			System.out.println("Nome Do Funcionario que realizou a venda: " + venda.getFuncionario().getPessoa().getNome());
			System.out.println("Preco da venda: " + venda.getPrecoTotal());
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDeBusca = 17L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorcodigo(codigoDeBusca);
		
		if (venda == null) {
			System.out.println("Codigo da venda nao encontrada");
		} else {
			System.out.println("");
			System.out.println("Nome do Cliente: " + venda.getCliente().getPessoa().getNome());
			System.out.println("Horario da venda: " + venda.getHorario());
			System.out.println("Codigo da venda: " + venda.getCodigo());
			System.out.println("Nome Do Funcionario que realizou a venda: " + venda.getFuncionario().getPessoa().getNome());
			System.out.println("Preco da venda: " + venda.getPrecoTotal());
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoDeEdicao = 18L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorcodigo(codigoDeEdicao);
		
		if (venda == null) {
			System.out.println("Codigo da venda nao encontrada");
		} else {
			venda.setPrecoTotal(new BigDecimal("45.00"));
			vendaDAO.editar(venda);
			
			System.out.println("Venda alterada com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoExclusao = 17L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorcodigo(codigoExclusao);
		
		if (venda == null) {
			System.out.println("Codigo da venda nao encontrada");
		} else {
			vendaDAO.excluir(venda);
			System.out.println("Venda excluida com sucesso");
		}
	}

}
