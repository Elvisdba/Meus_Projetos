package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

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
//			EstadoDAO estadoDAO = new EstadoDAO();
//			estados = estadoDAO.listar("nome");	
			
			EstadoBean estadoBean = new EstadoBean();
			estados = estadoBean.listaComRetorno();
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os Estados");
			e.printStackTrace();
		}
	}

	// listar
	@PostConstruct
	public void listar() {
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/cidade");
			
			// dentro do get vai tipo do retorno
			String json = webTarget.request().get(String.class);
			
			Gson gson = new Gson();
			Cidade[] vetorCidades = gson.fromJson(json, Cidade[].class);
			cidades = Arrays.asList(vetorCidades);
			
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidades = cidadeDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar");
			e.printStackTrace();
		}
	}
	
	/*
	 * Lista com retorno
	 * retorn Cidade
	 */
	public List<Cidade> listarComRetorno() {
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/cidade");
			
			String json = webTarget.request().get(String.class);
			
			Gson gson = new Gson();
			Cidade[] vetorCidades = gson.fromJson(json, Cidade[].class);
			cidades = Arrays.asList(vetorCidades);
			
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidades = cidadeDAO.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar");
			e.printStackTrace();
		}
		
		return cidades;
	}

	// salvar
	public void salvar() {
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/cidade");
			
			Gson gson = new Gson();
			String cidadeJson = gson.toJson(cidade);
			
			webTarget.request().post(Entity.json(cidadeJson));
			
			novo();
			cidades = listarComRetorno();
			
			
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidadeDAO.merge(cidade);

			
			
//			//por precalcao, atualizar a listagem de estado
//			EstadoDAO estadoDAO = new EstadoDAO();
//			estados = estadoDAO.listar("nome");

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

			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/cidade");
			WebTarget webTargetExcluir = webTarget.path("{codigo}").resolveTemplate("codigo", cidade.getCodigo());
			
			//requisicao para deletar
			webTargetExcluir.request().delete();
			
			//lista
			cidades = listarComRetorno();
		
			
//			CidadeDAO cidadeDAO = new CidadeDAO();
//			cidadeDAO.excluir(cidade);
//			cidades = cidadeDAO.listar();

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
			
//			EstadoDAO estadoDAO = new EstadoDAO();
//			estados = estadoDAO.listar("nome");
			
			EstadoBean estadoBean = new EstadoBean();
			estados = estadoBean.listaComRetorno();
			
			
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os Estados");
			e.printStackTrace();
		}
	}
}
