package br.com.joule.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.entity.Aluno;


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
	public boolean logar(String email, String senha) {
		try{
			em.createQuery("from Usuario u where u.login =" 
						  + ":l and u.senha= :s")
						.setParameter("l", email)
						.setParameter("s", senha).getSingleResult();
			return true;
			}catch(Exception e){
		return false;
	}
   }
}
