package br.com.jonas.drogaria.dao;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.jonas.drogaria.domain.Caixa;

public class CaixaDAOTest {

	@Test
	@Ignore
	public void salvar() throws ParseException{
		
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("15/12/2015"));
		caixa.setValorAbertura(new java.math.BigDecimal("100.00"));
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		
	}
	
	@Test
	@Ignore
	public void buscar() throws ParseException{
		
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2015"));
		Assert.assertNull(caixa);
		System.out.println(caixa);
		
		
	}
}
