package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
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
	public List<Curso> buscarPorNome(String nome) {
		return em.createQuery("from Curso c where "
				+ "upper(c.nome) like upper(:n)",Curso.class)
				.setParameter("n","%"+nome+"%").getResultList();
	}


}
