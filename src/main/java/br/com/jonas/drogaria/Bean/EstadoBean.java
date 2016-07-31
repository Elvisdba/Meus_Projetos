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

import br.com.jonas.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo() {
		estado = new Estado();
	}

	// salvar
	public void salvar() {
		try {
			// chamada ao DAO
			// EstadoDAO estadoDAO = new EstadoDAO();
			// estadoDAO.merge(estado);

			// chamada a service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/estado");

			Gson gson = new Gson();
			String estadoJson = gson.toJson(estado);

			// salvar
			// post so aceita Entity
			webTarget.request().post(Entity.json(estadoJson));

			// listagem
			// dentro do get vai tipo do retorno
			estados = listaComRetorno();
			novo();

			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}

	// listar
	@PostConstruct
	public void listar() {
		try {
			// chamada ao DAO
			// EstadoDAO estadoDAO = new EstadoDAO();
			// estados = estadoDAO.listar();

			// chamada a Service
			Client client = ClientBuilder.newClient();
			WebTarget webTarger = client.target("http://localhost:8080/Drogaria/rest/estado");

			// dentro do get vai tipo do retorno
			String json = webTarger.request().get(String.class);

			Gson gson = new Gson();
			Estado[] vetorEstados = gson.fromJson(json, Estado[].class);
			estados = Arrays.asList(vetorEstados);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	
	/*
	 * lista com retorno
	 * return Estado
	 */
	public List<Estado> listaComRetorno() {
		try {
			// chamada ao DAO
			// EstadoDAO estadoDAO = new EstadoDAO();
			// estados = estadoDAO.listar();

			// chamada a Service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/estado");

			// dentro do get vai tipo do retorno
			String json = webTarget.request().get(String.class);

			Gson gson = new Gson();
			Estado[] vetorEstados = gson.fromJson(json, Estado[].class);
			estados = Arrays.asList(vetorEstados);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
		
		return estados;
	}
	

	// excluir
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			// usando servico
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client.target("http://localhost:8080/Drogaria/rest/estado");
			WebTarget webTargetExcluir = webTarget.path("{codigo}").resolveTemplate("codigo", estado.getCodigo());
			
			//requisicao para deletar
			webTargetExcluir.request().delete();
			
			//lista
			estados = listaComRetorno();

			// EstadoDAO estadoDAO = new EstadoDAO();
			// estadoDAO.excluir(estado);
			// estados = estadoDAO.listar();

			Messages.addGlobalInfo("Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir");
			e.printStackTrace();
		}
	}

	// editar
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

	}

}
