package br.com.joule.teste;

import javax.persistence.EntityManager;

import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

public class TesteCurso {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		
		CursoDAO cursoDAO = new CursoDAOImpl(em);
		Curso curso = new Curso();
		
		curso.setNome("Eletricidade");
		curso.setDescricao("curso de eletricidade");
		

			try {
				cursoDAO.create(curso);
			} catch (DBCommitException e) {
				e.printStackTrace();
			}

				
		Curso curso2 = cursoDAO.buscarPorNome("Eletricidade");
		if (curso2 == null) {
			System.out.println("Curso n�o encontrado");
		}else {
			System.out.println("C�digo do curso: "+curso2.getId());
			System.out.println("Nome do curso: "+curso2.getNome());
			System.out.println("Descri��o do curso: "+curso2.getDescricao());
		}

	}
}