package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.CursoDAO;
import br.com.joule.entity.Curso;

public class CursoDAOImpl extends DAOImpl<Curso, Long> implements CursoDAO {

	public CursoDAOImpl(EntityManager em) {
		super(em);
	
	}

	@Override
	public List<Curso> list() {
	  TypedQuery<Curso> curso= 
			  em.createQuery("from Curso",Curso.class);
		return curso.getResultList();
	}

	@Override
	public Curso buscarPorNome(String nome) {
		TypedQuery<Curso> query =
				em.createQuery("from Curso a where "
				+ "a.nome = :nome",
				Curso.class);
			query.setParameter("nome", nome);
			try {
				return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;
			}
	}
	
	@Override
	public List<Curso> buscarNomes(String nome) {
		TypedQuery<Curso> query =
				em.createQuery("from Curso a where "
				+ "a.nome like :nome",
				Curso.class);
			query.setParameter("nome", "%"+nome+"%");
			try {
			return query.getResultList();
			} catch (NoResultException nre) {
				return null;
			}
	}

}
