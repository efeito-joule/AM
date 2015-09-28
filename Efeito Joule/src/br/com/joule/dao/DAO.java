package br.com.joule.dao;

import java.util.List;

public interface DAO<E> {

	public void save(E object);
	public void update(E object);
	public void remove(E object);
	public List<E> findAll();
}