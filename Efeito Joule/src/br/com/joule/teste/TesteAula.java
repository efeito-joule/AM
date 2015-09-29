package br.com.joule.teste;

import javax.persistence.EntityManager;

import br.com.joule.dao.AulaDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.entity.Aula;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.exceptions.IdNotFoundException;
import br.com.joule.singleton.EMFactorySingleton;

public class TesteAula {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		Aula aula = new Aula();
		AulaDAO dao = new AulaDAOImpl(em);
		
		aula.setNome("aula 1");
		aula.setUrlVideo("http:ddd");;
		
		try {
			dao.create(aula);
			System.out.println("Aula criada no id "+aula.getId());
		} catch (DBCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.delete(aula.getId());
			System.out.println("Aula do id "+aula.getId()+" deletada");
		} catch (DBCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
