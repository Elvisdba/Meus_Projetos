package br.com.jonas.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8081/Drogaria/rest
@ApplicationPath("rest")
public class DrogariaResourcesConfig extends ResourceConfig {

	// aonde vao estar meus servicos
	public DrogariaResourcesConfig() {
		packages("br.com.jonas.drogaria.service");
	}
}
