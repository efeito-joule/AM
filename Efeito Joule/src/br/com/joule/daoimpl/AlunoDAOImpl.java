package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Pessoa;


public class AlunoDAOImpl extends DAOImpl<Aluno, Long> implements AlunoDAO {

	public AlunoDAOImpl(EntityManager em) {
		super(em);

	}

	@Override
	public Aluno buscarEmail(String email) {
		TypedQuery<Aluno> query =em.createQuery("from Aluno c where "
				+ "upper(c.email) like upper(:e)",Aluno.class)
		       .setParameter("e",email);
		try {
			return query.getSingleResult();
			} catch (NoResultException nre) {
				return null;
			}
	}
	
	@Override
	public Aluno logar(String usuarioOuEmail, String senha) {
			
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Aluno a ");
		sql.append("WHERE (a.nomeUsuario = '" + usuarioOuEmail + "'");
		sql.append(" OR a.email = '" + usuarioOuEmail + "') AND a.senha = '" + senha + "'");
		
		Pessoa aluno = null;
		
		try {
			Query query = em.createQuery(sql.toString());
		
			aluno = (Aluno) query.getSingleResult();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return (Aluno) aluno;
   }

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> buscaDadosUsuario(String usuarioOuEmail, String senha) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("FROM Aluno a ");
		sql.append("WHERE (a.nomeUsuario = '" + usuarioOuEmail + "'");
		sql.append(" OR a.email = '" + usuarioOuEmail + "') AND a.senha = '" + senha + "'");
		
		List<Aluno> alunos = null;
		
		try {
			Query query = em.createQuery(sql.toString());
			alunos =  query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return alunos;
	}
}
