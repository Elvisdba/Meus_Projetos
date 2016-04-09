package br.com.jonas.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jonas.drogaria.domain.ItemVenda;
import br.com.jonas.drogaria.domain.Venda;
import br.com.jonas.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			
			sessao.save(venda);
			
			for (int i = 0; i < itensVenda.size(); i++) {
				ItemVenda itemVenda = itensVenda.get(i);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
			}
			
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
