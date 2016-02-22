package br.com.jonas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Parana");
		estado.setSigla("PR");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.listar();

		for (Estado estado : estados) {
			System.out.println(estado.getNome());
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDePesquisa = 5L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigoDePesquisa);

		if (estado == null) {
			System.out.println("nenhum resultado encontrado");
		} else {
			System.out.println(estado.getCodigo() + " - " + estado.getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		// codigo a ser deletado
		Long codigoParaExcluir = 6L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigoParaExcluir);

		if (estado == null) {
			System.out.println("nenhum resultado encontrado para remocao");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Removido - " + estado.getNome());

		}
	}

	@Test
	@Ignore
	public void editar() {
		// codigo a ser editado
		Long codigoParaEdicao = 9L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigoParaEdicao);

		if (estado == null) {
			System.out.println("nenhum resultado encontrado para edicao");
		} else {
			estado.setSigla("SP");
			estadoDAO.editar(estado);
			System.out.println("Editado - " + estado.getSigla() + " - " + estado.getNome());
		}

	}

}
