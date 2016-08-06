package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.jonas.drogaria.domain.Historico;
import br.com.jonas.drogaria.domain.Produto;
import br.com.jonas.drogaria.service.ProdutoService;
import br.com.jonas.drogaria.util.Constants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	private Historico historico;
	private Produto produto;
	private Boolean exibiPaineDados;

	
	public Boolean getExibiPaineDados() {
		return exibiPaineDados;
	}

	public void setExibiPaineDados(Boolean exibiPaineDados) {
		this.exibiPaineDados = exibiPaineDados;
	}

	
	
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@PostConstruct
	public void novo() {
		produto = new Produto();
		historico = new Historico();
		exibiPaineDados = false;
		
		
	}
	
	//busca por codigo
	public void buscar(){
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(Constants.URL + "/produto/" + produto.getCodigo());
			
			String json = webTarget.request().get(String.class);
			Gson gson = new Gson();
			
			Produto resultado = gson.fromJson(json, Produto.class);
			
			if(resultado == null){
				exibiPaineDados = false;
				Messages.addFlashGlobalWarn("Não existe produto cadastrado para o Código informado");
			}else{
				produto = resultado;
				exibiPaineDados = true;
			}
			
			
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar buscar o Produto");
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);
			
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(Constants.URL + "/historico");
			
			Gson gson = new Gson();
			String historicoJson = gson.toJson(historico);

			// salvar
			// post so aceita Entity
			webTarget.request().post(Entity.json(historicoJson));
			
			Messages.addGlobalInfo("Descricao salva com sucesso");
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar salvar o Historico");
			e.printStackTrace();
		}
	}

}
