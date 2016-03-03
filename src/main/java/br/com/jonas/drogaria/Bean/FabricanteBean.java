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
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Array;

import br.com.jonas.drogaria.dao.FabricanteDAO;
import br.com.jonas.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	// salvar
	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			novo();
			fabricantes = fabricanteDAO.listar();
			Messages.addGlobalInfo("Fabricante salvo com sucesso");

		} catch (Exception e) {
			Messages.addGlobalError("Nao foi possivel salvar Fabricante");
			e.printStackTrace();
		}
	}

	// listar
	@PostConstruct
	public void listar() {
		try {
			
			//chamando um DAO
			//FabricanteDAO fabricanteDAO = new FabricanteDAO();
			//fabricantes = fabricanteDAO.listar();
			
			
			
			
			
			//chama um service
			Client client = ClientBuilder.newClient();
			WebTarget url = client.target("http://localhost:8081/Drogaria/rest/fabricante");
			
			//dentro do get vai tipo do retorno
			String stringJson = url.request().get(String.class);
			
			Gson gson = new Gson();
			Fabricante[] vetorFabricantes = gson.fromJson(stringJson, Fabricante[].class);
			fabricantes = Arrays.asList(vetorFabricantes);

		} catch (Exception e) {
			Messages.addGlobalError("Nao foi possivel listar os Fabricantes");
			e.printStackTrace();
		}
	}

	// excluir
	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);

			fabricantes = fabricanteDAO.listar();
			Messages.addGlobalInfo("Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir");
			e.printStackTrace();
		}
	}
	
	//editar
	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}

}
