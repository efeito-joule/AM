package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.RankingDAO;
import br.com.joule.entity.Ranking;

public class RankingDAOImpl extends DAOImpl<Ranking, Long> implements RankingDAO{

	public RankingDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ranking buscarPorAluno(long aluno) {
		TypedQuery<Ranking> query = em.createQuery(
				"from Ranking r where r.aluno.codigo like :aluno ",Ranking.class);
			query.setParameter("aluno", aluno);
			try {
				return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;  
			}
	}


	@Override
	public List<Ranking> ListarTodos() {
		TypedQuery<Ranking> curso= 
				  em.createQuery("from Ranking r where r.posicaoTotal < 11 order by r.posicaoTotal",Ranking.class);
			return curso.getResultList();
	}

}
