package br.com.joule.teste;

import javax.persistence.EntityManager;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.singleton.EMFactorySingleton;

public class TesteCurso {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		
		Curso curso = new Curso();
		CursoDAO dao = new CursoDAOImpl(em);
		curso.setNome("Mecanica");
		curso.setDescricao("curso de mecânica");
		
	
			try {
				dao.create(curso);
			} catch (DBCommitException e) {
				
				e.printStackTrace();
			}
		
		Curso curso2 = dao.findById(curso.getId());
		if (curso2 == null) {
			System.out.println("Curso não encontrado");
		}else {
			System.out.println("Código do curso: "+curso2.getId());
			System.out.println("Nome do curso: "+curso2.getNome());
			System.out.println("Descrição do curso: "+curso2.getDescricao());
		}

	}
}