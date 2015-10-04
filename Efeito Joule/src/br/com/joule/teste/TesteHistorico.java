package br.com.joule.teste;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.HistoricoDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.HistoricoDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Historico;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

public class TesteHistorico {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		HistoricoDAO historicoDAO = new HistoricoDAOImpl(em);
		Historico historico = new Historico();
		
		Aluno aluno = new Aluno();
		Aula aula = new Aula();
		
		AulaDAO aulaDAO = new AulaDAOImpl(em);
		AlunoDAO alunoDAO = new AlunoDAOImpl(em);
		
		aluno.setNome("vanks7");
		aluno.setEmail("vanks7.e@gmail.com");
		aluno.setSenha("xxx");
		try {
			alunoDAO.create(aluno);
			System.out.println("Aluno criado");
		} catch (DBCommitException e1) {
			System.out.println("Erro ao criar o aluno");
			e1.printStackTrace();
		}
		
		long idAula = 2;
		
		aluno = alunoDAO.findById(aluno.getId());
		aula = aulaDAO.findById(idAula);
		
		historico.setAluno(aluno);
		historico.setAula(aula);
		
		historico.setNumAcerto(9);
		historico.setNumErro(1);
		
		System.out.println("aluno: "+historico.getAluno().getNome());
		System.out.println("aluno: "+historico.getAula().getNome());
		System.out.println("Acertos: "+historico.getNumAcerto());
		System.out.println("Erros: "+historico.getNumErro());
		
			try {
				historicoDAO.create(historico);
				System.out.println("Histórico criado");
			} catch (DBCommitException e) {
				System.out.println("erro ao criar o histórico");
				e.printStackTrace();
			}
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("proc");
		query.execute();
		
		System.out.println("Numero de erros do hist: "+historico.getNumErro());
		System.out.println("Numero de acertos do hist: "+historico.getNumAcerto());
		
	}
}
