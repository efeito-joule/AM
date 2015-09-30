package br.com.joule.teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.joule.entity.Alternativa;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Questao;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.QuestaoDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.QuestaoDAOImpl;

public class TesteQuestaoAlternativa {

	public static void main(String[] args) {
		
		Questao questao = new Questao();
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		QuestaoDAO dao = new QuestaoDAOImpl(em);
		Aula aula = new Aula();
		AulaDAO aulaDAO = new AulaDAOImpl(em);
		
		//deletar a questao de codigo 4
		/*
		try {
			dao.delete(4);
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}*/
		aula = aulaDAO.buscarPorNome("Eletricidade");
		questao.setAula(aula);
		questao.setDescricao("Entender o conceito de trajetória nível 8");
		questao.setPergunta("Qual das opções abaixo não corresponde a uma medida de de trajetória?");
		
		Alternativa alternativa = new Alternativa();
		alternativa.setDescricao("12 mestros");
		alternativa.setResposta(false);
		alternativa.setQuestao(questao);
		
		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao("12 centímetros");
		alternativa2.setResposta(false);
		alternativa2.setQuestao(questao);
		
		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao("12 cm");
		alternativa3.setResposta(false);
		alternativa3.setQuestao(questao);
		
		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao("12 Km");
		alternativa4.setResposta(false);
		alternativa4.setQuestao(questao);
		
		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao("12 min");
		alternativa5.setResposta(true);
		alternativa5.setQuestao(questao);
		
		List<Alternativa> alternativas = new ArrayList<Alternativa>(); 
		alternativas.add(alternativa);
		alternativas.add(alternativa2);
		alternativas.add(alternativa3);
		alternativas.add(alternativa4);
		alternativas.add(alternativa5);
		
		questao.setListaAlternativas(alternativas);
		
	
		try {
			dao.create(questao);
			System.out.println("Questão cadastrada");
		} catch (DBCommitException e) {
			
			e.printStackTrace();
		}
	
	}
}
