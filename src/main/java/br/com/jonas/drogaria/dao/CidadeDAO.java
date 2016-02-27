package br.com.jonas.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jonas.drogaria.domain.Cidade;
import br.com.jonas.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			
			//procura dentro da cidade
			Criteria consulta = sessao.createCriteria(Cidade.class);
			//onde a propriedae estado.codigo é estadoCodigo
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			
			//ordena pelo campo nome
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;

		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
	}
 }
}
