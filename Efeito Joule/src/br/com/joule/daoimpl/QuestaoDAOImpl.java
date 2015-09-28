package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.QuestaoDAO;
import br.com.joule.entity.Questao;

public class QuestaoDAOImpl extends DAOImpl<Questao, Long> implements QuestaoDAO {

	public QuestaoDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Questao buscarPorPergunda(String pergunta) {
		TypedQuery<Questao> query =
				em.createQuery("from Questao q where "
				+ "q.pergunta = :pergunta",
				Questao.class);
			query.setParameter("pergunta", pergunta);
		try {
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;  
		}
	}

	@Override
	public List<Questao> buscarPorAula(int codigo) {
		TypedQuery<Questao> query =
				em.createQuery("from Questao q where "
				+ "q.aula.codigo = :codigo",
				Questao.class);
			query.setParameter("codigo", codigo);
			return query.getResultList();
	}

}
