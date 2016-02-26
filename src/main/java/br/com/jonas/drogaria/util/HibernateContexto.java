package br.com.jonas.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	//quanado inicio o tomcat
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//forca a criacao de fabrica de sessao quando o tomcat for ligado
		HibernateUtil.getFabricaDeSessoes().openSession();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//destroi a fabrica de sessoes
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

}
