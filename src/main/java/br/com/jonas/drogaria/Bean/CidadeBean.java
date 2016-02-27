package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jonas.drogaria.dao.CidadeDAO;
import br.com.jonas.drogaria.dao.EstadoDAO;
import br.com.jonas.drogaria.domain.Cidade;
import br.com.jonas.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void novo() {
		cidade = new Cidade();
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");	
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os Estados");
			e.printStackTrace();
		}
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
			
			//por precalcao, atualizar a listagem de estado
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

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

			Messages.addGlobalInfo("Cidade removida com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir cidade");
			e.printStackTrace();
		}
	}

	// editar
	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os Estados");
			e.printStackTrace();
		}
	}
}
