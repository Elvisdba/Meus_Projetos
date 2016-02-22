package br.com.jonas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Cidade;
import br.com.jonas.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoCidade = 4L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigoCidade);

		if (cidade == null) {
			System.out.println("cidade nao encontrada");
		} else {

			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = new Pessoa();

			pessoa.setBairro("Sagrada Familia");
			pessoa.setCelular("51-91039302");
			pessoa.setCep("94198256");
			pessoa.setCidada(cidade);
			pessoa.setComplemento("casa");
			pessoa.setCpf("83851135092");
			pessoa.setEmail("jonas.costa1987@gmail.com");
			pessoa.setNome("Natalia");
			pessoa.setNumero((short) 61);
			pessoa.setRg("1091094416");
			pessoa.setRua("Rua Boqueirao");
			pessoa.setTelefone("51-34316460");

			pessoaDAO.salvar(pessoa);
			System.out.println("Salvo com sucesso");

		}

	}

	@Test
	@Ignore
	public void listar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> pessoas = pessoaDAO.listar();

		if (pessoas == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			for (Pessoa pessoa : pessoas) {
				System.out.println("Nome: " + pessoa.getNome());
				System.out.println("Telefone: " + pessoa.getTelefone());
				System.out.println("ID: " + pessoa.getCodigo());
				System.out.println("Cidade: " + pessoa.getCidada().getNome());
				System.out.println("Estado: " + pessoa.getCidada().getEstado().getSigla());
			}
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		Long codigoDePesquisa = 7L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoDePesquisa);

		if (pessoa == null) {
			System.out.println("Registro nao encontrado");
		} else {
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("Nome: " + pessoa.getCpf());
			System.out.println("Nome: " + pessoa.getCodigo());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigoParaExclusao = 7L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoParaExclusao);

		if (pessoa == null) {
			System.out.println("Pessoa nao encontrada para exclusao");
		} else {
			pessoaDAO.excluir(pessoa);
			System.out.println("Excluido com sucesso");
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 6L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscarPorcodigo(codigoCidade);

		Long codigoParaEdicao = 8L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoParaEdicao);

		if (pessoa == null) {
			System.out.println("Pessoa nao encontrada para edicao");
		} else {
			pessoa.setEmail("jonas.costa@1987@gmail.com");
			pessoa.setCidada(cidade);
			pessoaDAO.editar(pessoa);

			System.out.println("Editado com sucesso");
		}

	}

}
