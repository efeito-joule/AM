package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.MatriculaDAO;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Curso;
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
	public Matricula buscarPorAlunoCurso(Aluno aluno, Curso curso) {
			TypedQuery<Matricula> query = em.createQuery(
				"from Matricula c where c.aluno like :aluno "
				+ "and c.curso like :curso",Matricula.class);
			query.setParameter("aluno", aluno);
			query.setParameter("curso",curso);
			try {
				return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;  
			}
	}
	
	@Override
	public List<Matricula> buscarPorAluno(long aluno) {
			TypedQuery<Matricula> query = em.createQuery(
				"from Matricula c where c.aluno.id like :aluno",Matricula.class);
			query.setParameter("aluno",aluno);
			try {
				return query.getResultList();
			} catch (NoResultException nre) {
				return null;  
			}
	}

}
