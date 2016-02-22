package br.com.jonas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Feijao");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		
	}
	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar();
		
		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante.getDescricao());
		}
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDePesquisa = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigoDePesquisa);
		
		if (fabricante == null) {
			System.out.println("nunhum resutado encontrado");
		}else {
			System.out.println(fabricante.getCodigo()+ " - " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		//codigo a ser excluido
		Long codigoParaExcluir = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigoParaExcluir);
		
		if (fabricante == null) {
			System.out.println("nunhum resutado encontrado para exclusao");
		}else {
			fabricanteDAO.excluir(fabricante);
			System.out.println("Excluido com sucesso - " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoParaEdicao = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorcodigo(codigoParaEdicao);
		
		if (fabricante == null) {
			System.out.println("nunhum resutado encontrado para edicao");
		}else {
			
			System.out.println(fabricante.getDescricao() + " Alterado para: ");
			fabricante.setDescricao("Arroz");
			fabricanteDAO.editar(fabricante);
			System.out.println(fabricante.getDescricao());
			
		}
	}
	
	@Test
	@Ignore
	public void mergeIncluir() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante A");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
		
	}
	
	


}
