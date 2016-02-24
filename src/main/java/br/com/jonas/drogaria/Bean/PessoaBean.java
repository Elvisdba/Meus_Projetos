package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.drogaria.dao.PessoaDAO;
import br.com.jonas.drogaria.domain.Cidade;
import br.com.jonas.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void novo() {
		pessoa = new Pessoa();

	}

	// listar
	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar");
			e.printStackTrace();
		}
	}

	// excluir
	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Pessoa removida com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Nao foi possivel remover a Pessoa");
			e.printStackTrace();
		}

	}

}
