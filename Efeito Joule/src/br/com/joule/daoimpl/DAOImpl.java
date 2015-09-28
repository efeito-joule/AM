package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.joule.dao.DAO;
import br.com.joule.util.JPAUtil;

public abstract class DAOImpl<E> implements DAO<E> {

	@Override
	public void save(E object) {
		EntityManager em = JPAUtil.getEntityManager();
		
		EntityTransaction etx = em.getTransaction();
		
		try {
			etx.begin();
			em.persist(object);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(E object) {
		
	}

	@Override
	public void remove(E object) {
		
	}

	@Override
	public List<E> findAll() {
		return null;
	}

}