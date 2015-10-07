package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.joule.dao.AdministradorDAO;
import br.com.joule.entity.Administrador;
import br.com.joule.entity.Pessoa;


public class AdministradorDAOImpl extends DAOImpl<Administrador, Long> implements AdministradorDAO {

	public AdministradorDAOImpl(EntityManager em) {
		super(em);

	}

	@Override
	public Administrador buscarEmail(String email) {
		TypedQuery<Administrador> query =em.createQuery("from Administrador c where "
				+ "upper(c.email) like upper(:e)",Administrador.class)
		       .setParameter("e",email);
		try {
			return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;
			}
	}
	
	@Override
	public Administrador logar(String usuarioOuEmail, String senha) {
			
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Administrador a ");
		sql.append("WHERE (a.nomeUsuario = '" + usuarioOuEmail + "'");
		sql.append(" OR a.email = '" + usuarioOuEmail + "') AND a.senha = '" + senha + "'");
		
		Pessoa aluno = null;
		
		try {
			Query query = em.createQuery(sql.toString());
		
			aluno = (Administrador) query.getSingleResult();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return (Administrador) aluno;
   }

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrador> buscaDadosUsuario(String usuarioOuEmail, String senha) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Administrador a ");
		sql.append("WHERE (a.nomeUsuario = '" + usuarioOuEmail + "'");
		sql.append(" OR a.email = '" + usuarioOuEmail + "') AND a.senha = '" + senha + "'");
		
		List<Administrador> administradores = null;
		
		try {
			Query query = em.createQuery(sql.toString());
			administradores =  query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return administradores;
	}
}
