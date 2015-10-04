package br.com.joule.teste;


import javax.persistence.EntityManager;

import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

public class TesteAula {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		Aula aula = new Aula();
		AulaDAO dao = new AulaDAOImpl(em);
		Curso curso = new Curso();
		CursoDAO cursoDAO = new CursoDAOImpl(em);
		
		curso = cursoDAO.buscarPorNome("Eletricidade");
		
		try {
			cursoDAO.create(curso);
		} catch (DBCommitException e1) {
		
			e1.printStackTrace();
		}
		
		aula.setNome("Eletricidade");
		aula.setUrlVideo("http:ddd");
		aula.setCurso(curso);
		
		try {
			dao.create(aula);
			System.out.println("Aula criada no id "+aula.getId());
		} catch (DBCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
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
		
		System.out.println("");
		System.out.println("Buscar por codigo");
		*/
	}
}
