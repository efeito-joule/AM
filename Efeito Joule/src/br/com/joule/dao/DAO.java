package br.com.joule.dao;

import br.com.joule.exceptions.DBCommitException;
import br.com.joule.exceptions.IdNotFoundException;

public interface DAO<T,K>{
	void create(T entity) throws DBCommitException;
	void update(T entity) throws DBCommitException;
	void delete(K id) throws DBCommitException, 
								IdNotFoundException;
	T findById(K id);
	
}