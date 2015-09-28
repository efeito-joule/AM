package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.joule.dao.AulaDAO;
import br.com.joule.entity.Aula;

public class AulaDAOImpl extends DAOImpl<Aula, Long> implements AulaDAO{

	public AulaDAOImpl(EntityManager em) {
		super(em);

	}

	@Override
	public List<Aula> list() {
		  TypedQuery<Aula> aula= 
				  em.createQuery("from Aula",Aula.class);
		return aula.getResultList();
	}

	@Override
	public List<Aula> buscarPorNome(String nome) {
		TypedQuery<Aula> query =
				em.createQuery("from Aula a where "
				+ "a.nome like :nome",
				Aula.class);
			query.setParameter("nome", "%"+nome+"%");
			try {
			return query.getResultList();
			} catch (NoResultException nre) {
				return null;
			}
	}
	
}
