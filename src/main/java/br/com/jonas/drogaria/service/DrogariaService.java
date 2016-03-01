package br.com.jonas.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8081/Drogaria/rest/[nome]
@Path("drogaria")
public class DrogariaService {
	
	@GET
	public String exibir() {
		return "curso de Java";
	}

}
