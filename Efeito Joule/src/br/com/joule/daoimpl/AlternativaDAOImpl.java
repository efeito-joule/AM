package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.AlternativaDAO;
import br.com.joule.entity.Alternativa;

public class AlternativaDAOImpl extends DAOImpl<Alternativa, Long> implements AlternativaDAO{

	public AlternativaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Alternativa> listarAlternativas(long questao) {
		TypedQuery<Alternativa> query =
				em.createQuery("from Alternativa a where "
				+ "a.questao like :questao",
				Alternativa.class);
			query.setParameter("questao", questao);
			try {
			return query.getResultList();
			} catch (NoResultException nre) {
				return null;
			}
	}

}
