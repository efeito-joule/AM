package br.com.joule.daoimpl;

import javax.persistence.EntityManager;

import br.com.joule.dao.UsuarioDAO;
import br.com.joule.entity.Aluno;

public class UsuarioDAOImpl extends DAOImpl<Aluno, Integer> implements UsuarioDAO{

	public UsuarioDAOImpl(EntityManager em) {
		super(em);
	}
}