package br.com.jonas.drogaria.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Funcionario;
import br.com.jonas.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() {

		Long codigoPessoa = 10L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoPessoa);

		if (pessoa == null) {
			System.out.println("Codigo da pessoa nao encontrada");
		} else {
			Funcionario funcionario = new Funcionario();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			
			funcionario.setDataAdmissao(new Date());
			funcionario.setCarteiraTrabalho("988242");
			funcionario.setPessoa(pessoa);

			funcionarioDAO.salvar(funcionario);
			System.out.println("Funcionario salvo");

		}
	}

	@Test
	@Ignore
	public void listar() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> funcionarios = funcionarioDAO.listar();

		if (funcionarios == null) {
			System.out.println("Nao a dados cadastrados");
		} else {

			for (Funcionario funcionario : funcionarios) {
				System.out.println("Nome do funcionario " + funcionario.getPessoa().getNome());
				System.out.println("Nome do funcionario " + funcionario.getCarteiraTrabalho());
				System.out.println("Nome do funcionario " + funcionario.getCodigo());

			}
		}
	}
	
	@Test
	@Ignore
	public void bucarPorCodigo() {
		Long codigoDeBusca = 11L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorcodigo(codigoDeBusca);
		
		if (funcionario == null) {
			System.out.println("Funcionario nao cadastrados");
		} else {

			System.out.println("Nome: " + funcionario.getPessoa().getNome());
			System.out.println("Codigo: " + funcionario.getCodigo());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoParaExclusao = 9L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorcodigo(codigoParaExclusao);
		
		if (funcionario == null) {
			System.out.println("Funcionario nao encontrado");
		} else {
			funcionarioDAO.excluir(funcionario);
			System.out.println("Funcionario " + funcionario.getPessoa().getNome() + " excluido com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void editar() {

		Long cogidoFuncionario = 11L;
		//chave estrangeira
		Long codigoPessoa = 10L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorcodigo(cogidoFuncionario);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoPessoa);
		
		if (pessoa == null || funcionario == null) {
			System.out.println("Funcionario ou Pessoa nao encontrado");
		} else {
			funcionario.setPessoa(pessoa);
			funcionario.setCarteiraTrabalho("999999");
			funcionarioDAO.editar(funcionario);
			System.out.println("Funcionario " + funcionario.getPessoa().getNome() + " alterado com sucesso");
			

		}
	}

}
