package br.com.joule.daoimpl;

import javax.persistence.EntityManager;

import br.com.joule.dao.DAO;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.exceptions.IdNotFoundException;

import java.lang.reflect.ParameterizedType;

public class DAOImpl <T, K> implements DAO<T, K>{

	protected EntityManager em;
	
	private Class<T> classe;

	@SuppressWarnings("unchecked")
	public DAOImpl(EntityManager em) {
		this.em = em;
		classe = 
			(Class<T>) ((ParameterizedType)
					getClass().getGenericSuperclass())
						.getActualTypeArguments()[0];
	}

	@Override
	public void create(T entity) throws DBCommitException {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {			
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao persistir");
		}
	}

	@Override
	public void update(T entity) throws DBCommitException {
		try{
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao atualizar");
		}
	}

	@Override
	public void delete(K id) throws DBCommitException, IdNotFoundException {
		T entity = findById(id);
		if (entity == null) 
			throw new IdNotFoundException();
		try{
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao Remover");
		}
	}

	@Override
	public T findById(K id) {
		return em.find(classe, id);
	}
}
