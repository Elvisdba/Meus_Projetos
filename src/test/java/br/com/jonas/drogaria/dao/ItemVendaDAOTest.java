package br.com.jonas.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.ItemVenda;
import br.com.jonas.drogaria.domain.Produto;
import br.com.jonas.drogaria.domain.Venda;

public class ItemVendaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Long codigoVenda = 20L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorcodigo(codigoVenda);

		Long codigoProduto = 13L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorcodigo(codigoProduto);

		if (venda == null || produto == null) {
			System.out.println("Venda ou Produto nao encontrados");
		} else {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			ItemVenda itemVenda = new ItemVenda();

			itemVenda.setValorParcial(new BigDecimal("232.00"));
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("159"));
			itemVenda.setVenda(venda);

			itemVendaDAO.salvar(itemVenda);
			System.out.println("Item salvo com sucesso");
		}
	}

	@Test
	@Ignore
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> itensVenda = itemVendaDAO.listar();

		if (itensVenda == null) {
			System.out.println("Nao ha Itens Cadastrados");
		} else {
			for (ItemVenda itemVenda : itensVenda) {
				System.out.println("");
				System.out.println("Codigo: " + itemVenda.getCodigo());
				System.out.println("Quantidade: " + itemVenda.getQuantidade());
				System.out.println("Descricao do Produto: " + itemVenda.getProduto().getDescricao());
				System.out.println("Nome do funcionario que efetuou a venda: "
						+ itemVenda.getVenda().getFuncionario().getPessoa().getNome());
				System.out.println("Descricao do Fabricante: " + itemVenda.getProduto().getFabricante().getDescricao());
			}
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDeBusca = 21L;
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarPorcodigo(codigoDeBusca);

		if (itemVenda == null) {
			System.out.println("Codigo nao encontrado");
		} else {
			System.out.println("");
			System.out.println("Codigo: " + itemVenda.getCodigo());
			System.out.println("Quantidade: " + itemVenda.getQuantidade());
			System.out.println("Descricao do Produto: " + itemVenda.getProduto().getDescricao());
			System.out.println("Nome do funcionario que efetuou a venda: "
					+ itemVenda.getVenda().getFuncionario().getPessoa().getNome());
			System.out.println("Descricao do Fabricante: " + itemVenda.getProduto().getFabricante().getDescricao());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoEdicao = 21L;
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarPorcodigo(codigoEdicao);

		if (itemVenda == null) {
			System.out.println("Item da Venda nao encontrado");
		} else {

			itemVenda.setQuantidade(new Short("23"));
			itemVenda.setValorParcial(new BigDecimal("18.90"));

			itemVendaDAO.editar(itemVenda);
			System.out.println("Alterado com sucesso");
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigoExclusao = 22L;
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarPorcodigo(codigoExclusao);

		if (itemVenda == null) {
			System.out.println("Item da Venda nao encontrado");
		} else {
			itemVendaDAO.excluir(itemVenda);
			System.out.println("Item excluido com sucesso");
		}
	}

}
