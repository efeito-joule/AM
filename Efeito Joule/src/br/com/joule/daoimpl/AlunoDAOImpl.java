package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Pessoa;


public class AlunoDAOImpl extends DAOImpl<Aluno, Long> implements AlunoDAO {

	public AlunoDAOImpl(EntityManager em) {
		super(em);

	}

	@Override
	public List<Aluno> buscarEmail(String email) {
		return em.createQuery("from Aluno c where "
				+ "upper(c.email) like upper(:e)",Aluno.class)
		       .setParameter("e","%"+email+"%").getResultList();
			 
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
}
