package br.com.joule.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.HistoricoDAO;
import br.com.joule.entity.Historico;

public class HistoricoDAOImpl extends DAOImpl<Historico, Long> implements HistoricoDAO{

	public HistoricoDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Historico buscarPorAulaAluno(int aula, int aluno) {
		TypedQuery<Historico> query = em.createQuery(
				"from Historico h where h.aula.codigo like :aula "
				+ "and h.aluno.codigo like :aluno",Historico.class);
			query.setParameter("aula", aula);
			query.setParameter("aluno", aluno);
			try {
				return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;  
			}
	}
}
