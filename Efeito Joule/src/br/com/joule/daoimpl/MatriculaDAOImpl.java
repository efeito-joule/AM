package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.MatriculaDAO;
import br.com.joule.entity.Matricula;

public class MatriculaDAOImpl extends DAOImpl<Matricula, Long> implements MatriculaDAO{

	public MatriculaDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Matricula> buscarPorEmail(String email) {
			TypedQuery<Matricula> query =
				em.createQuery("from Matricula c where "
				+ "c.aluno.email = :email",
				Matricula.class);
			query.setParameter("email", email);
			return query.getResultList();
	}

	@Override
	public Matricula buscarPorEmailCurso(String email, int codigo) {
			TypedQuery<Matricula> query = em.createQuery(
				"from Matricula c where c.aluno.email like :email "
				+ "and c.curso.codigo like :codigo",Matricula.class);
			query.setParameter("email", email);
			query.setParameter("codigo",codigo);
			try {
				return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;  
			}
	}

}
