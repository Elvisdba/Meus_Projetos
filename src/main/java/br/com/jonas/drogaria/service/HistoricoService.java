package br.com.jonas.drogaria.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.HistoricoDao;
import br.com.jonas.drogaria.domain.Historico;

@Path("historico")
public class HistoricoService {

	// http://localhost:8081/Drogaria/rest/historico
	@POST
	public String salvar(String json) {

		// converte json para objeto
		Gson gson = new Gson();
		Historico historico = gson.fromJson(json, Historico.class);

		HistoricoDao historicoDAO = new HistoricoDao();
		historico = historicoDAO.merge(historico);

		// converte ojbeto em Json
		String historicoJson = gson.toJson(historico);
		return historicoJson;

	}

}
