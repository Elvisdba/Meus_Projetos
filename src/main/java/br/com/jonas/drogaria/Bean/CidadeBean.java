package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.drogaria.dao.CidadeDAO;
import br.com.jonas.drogaria.domain.Cidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	private List<Cidade> cidades;

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void novo() {
		cidade = new Cidade();
	}

	// listar
	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar");
			e.printStackTrace();
		}
	}

	// salvar
	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);

			novo();
			cidades = cidadeDAO.listar();

			Messages.addGlobalInfo("Cidade Salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar");
			e.printStackTrace();
		}
	}

	// excluir
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);

			cidades = cidadeDAO.listar();

			Messages.addGlobalInfo("Cidade excluida com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir cidade");
			e.printStackTrace();
		}
	}

	// editar
	public void editar(ActionEvent evento) {
		cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
	}
}
