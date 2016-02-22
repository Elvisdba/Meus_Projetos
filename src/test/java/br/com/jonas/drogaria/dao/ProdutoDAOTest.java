package br.com.jonas.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Fabricante;
import br.com.jonas.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoFabricante = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigoFabricante);

		if (fabricante == null) {
			System.out.println("Fabricante nao encontrado");
		} else {
			Produto produto = new Produto();
			ProdutoDAO produtoDAO = new ProdutoDAO();

			produto.setDescricao("Feijao Preto");
			produto.setFabricante(fabricante);
			produto.setPreco(new BigDecimal("2.50"));
			produto.setQuantidade(new Short("2"));

			produtoDAO.salvar(produto);
			System.out.println("Produto salvo com sucesso");

		}
	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();

		if (produtos == null) {
			System.out.println("Nao a produtos cadastrados");
		} else {
			for (Produto produto : produtos) {
				System.out.println("");
				System.out.println("Fabricante: " + produto.getFabricante().getDescricao());
				System.out.println("Descricao do produto: " + produto.getDescricao());
				System.out.println("Preco: " + produto.getPreco());
				System.out.println("Quantidade: " + produto.getQuantidade());
			}
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDeBusca = 12L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorcodigo(codigoDeBusca);
		
		if (produto == null) {
			System.out.println("produto nao encontrado");
		} else {
			System.out.println("Descricao: " + produto.getDescricao());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Fabricante: " + produto.getFabricante().getDescricao());
			System.out.println("Preco: " + produto.getPreco());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoDeExclusao = 12L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorcodigo(codigoDeExclusao);
		
		if (produto == null) {
			System.out.println("produto nao encontrado");
		} else {
			produtoDAO.excluir(produto);
			System.out.println("Produto " + produto.getDescricao() + " excluido");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoFabricante = 3L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigoFabricante);
		
		Long codigoProduto = 13L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorcodigo(codigoProduto);
		
		if (produto == null || fabricante == null) {
			System.out.println("produto ou fabricante nao encontrado");
		} else {
			
			
			produto.setDescricao("Feijao Marrom tipo 1");
			produto.setFabricante(fabricante);
			
			produtoDAO.editar(produto);
			System.out.println("Produto alterado com sucesso");
		}
	}

}
