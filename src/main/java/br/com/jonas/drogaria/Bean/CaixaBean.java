package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.jonas.drogaria.dao.CaixaDAO;
import br.com.jonas.drogaria.dao.FuncionarioDAO;
import br.com.jonas.drogaria.domain.Caixa;
import br.com.jonas.drogaria.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable{
	
	private ScheduleModel scheduleModel;
	private Caixa caixa;
	private List<Funcionario> funcionarios;
	private List<Caixa> listaDeCaixa;
	

	public List<Caixa> getListaDeCaixa() {
		return listaDeCaixa;
	}

	public void setListaDeCaixa(List<Caixa> listaDeCaixa) {
		this.listaDeCaixa = listaDeCaixa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return scheduleModel;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.scheduleModel = caixas;
	}
	
	@PostConstruct
	public void listar(){
		scheduleModel = new DefaultScheduleModel();
		
		CaixaDAO caixaDAO = new CaixaDAO();
		listaDeCaixa = caixaDAO.listar();
	
		if(listaDeCaixa != null){
			for (Caixa caixas : listaDeCaixa) {
				DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();
				defaultScheduleEvent.setEndDate(caixas.getDataFechamento());
				defaultScheduleEvent.setStartDate(caixas.getDataAbertura());
				defaultScheduleEvent.setTitle(caixas.getFuncionario().getPessoa().getNome());
				defaultScheduleEvent.setAllDay(true);
				defaultScheduleEvent.setEditable(false);
				scheduleModel.addEvent(defaultScheduleEvent);
			}
		}
		
		
		
	}
	
	public void novo(SelectEvent evento){
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
	}
	
	public void salvar(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(calendar.DATE, 1);
		caixa.setDataAbertura(calendar.getTime());//adiciona 1 dia a data
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}
	

}
