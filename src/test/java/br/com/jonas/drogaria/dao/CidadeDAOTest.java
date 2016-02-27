package br.com.jonas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Cidade;
import br.com.jonas.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar() {
		/*
		 * exemplo com chave estrangeira
		 */
		Long codigoEstado = 15L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigoEstado);


		if (estado == null) {
			System.out.println("estado nao encontrado");
		} else {
			Cidade cidade = new Cidade();
			cidade.setNome("Bombinhas");
			cidade.setEstado(estado);

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);
			
			System.out.println("cidade: " + cidade.getNome());
			System.out.println("Estado: " + cidade.getEstado().getNome());
			System.out.println("Sigla: " + cidade.getEstado().getSigla());
			System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
		}
	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar();

		if (cidades == null) {
			System.out.println("Nao a cidades cadastradas");
		} else {
			for (Cidade cidade : cidades) {
				System.out.println("Nome da cidade: " + cidade.getNome());
				System.out.println("Sigle do estado: " + cidade.getEstado().getSigla());
				System.out.println("");
			}
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Long codigoDePesquisa = 4L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigoDePesquisa);

		if (cidade == null) {
			System.out.println("Cidade nao encontrada");
		} else {
			System.out.println("Cidade de: " + cidade.getNome());
			System.out.println("Localizada no estado de: " + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigoDePesquisa = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigoDePesquisa);

		if (cidade == null) {
			System.out.println("Cidade nao encontrada para exclusao");
		} else {
			cidadeDAO.excluir(cidade);
			System.out.println("Cidade " + cidade.getNome() + " excluido com sucesso");
		}
	}

	@Test
	@Ignore
	public void editar() {
		/*
		 * exemplo com chave estrangeira
		 */
		Long codigoDoEstado = 9L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorcodigo(codigoDoEstado);

		Long codigoDaCidade = 6L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigoDaCidade);

		if (cidade == null || estado == null) {
			System.out.println("Cidade ou Estado nao encontrada para editar");
		} else {

			cidade.setNome("Guarulhos");
			cidade.setEstado(estado);
			cidadeDAO.editar(cidade);

			System.out.println("Alterado com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorEstado() {
		Long estadoCodigo = 2L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);

		if (cidades == null) {
			System.out.println("Nao a cidades cadastradas");
		} else {
			for (Cidade cidade : cidades) {
				System.out.println("Nome da cidade: " + cidade.getNome());
				System.out.println("Sigle do estado: " + cidade.getEstado().getSigla());
				System.out.println("");
			}
		}
	}
	
	
}
