package br.com.joule.teste;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import javax.persistence.EntityManager;

import br.com.joule.entity.Alternativa;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Questao;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.dao.QuestaoDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.daoimpl.QuestaoDAOImpl;

public class TesteQuestaoAlternativa {

	public static void main(String[] args) {
		
		Questao questao = new Questao();
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		QuestaoDAO dao = new QuestaoDAOImpl(em);
		Aula aula = new Aula();
		AulaDAO aulaDAO = new AulaDAOImpl(em);
		Curso curso = new Curso();
		CursoDAO cursoDAO = new CursoDAOImpl(em);
		List<Questao> questoes = new ArrayList<Questao>();
		
		//deletar a questao de codigo 4
		/*
		try {
			dao.delete(4);
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}*/
		
		final boolean resposta01;
		final boolean resposta02;
		final boolean resposta03;
		final boolean resposta04;
		final boolean resposta05;

		resposta01=false;
		resposta02=false;
		resposta03=false;
		resposta04=false;
		resposta05=true;
		
		curso = cursoDAO.buscarPorNome("Eletricidade");
		aula = aulaDAO.buscarPorNome("Eletricidade");
		aula.setCurso(curso);
		questao.setAula(aula);
		questao.setDescricao("Entender o conceito de trajetória nível 8");
		questao.setPergunta("Qual das opções abaixo não corresponde a uma medida de de trajetória?");
		
		Alternativa alternativa = new Alternativa();
		alternativa.setDescricao("12 mestros");
		alternativa.setResposta(resposta01);
		alternativa.setQuestao(questao);
		
		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao("12 centímetros");
		alternativa2.setResposta(resposta02);
		alternativa2.setQuestao(questao);
		
		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao("12 cm");
		alternativa3.setResposta(resposta03);
		alternativa3.setQuestao(questao);
		
		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao("12 Km");
		alternativa4.setResposta(resposta04);
		alternativa4.setQuestao(questao);
		
		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao("12 min");
		alternativa5.setResposta(resposta05);
		alternativa5.setQuestao(questao);
		
		List<Alternativa> alternativas = new ArrayList<Alternativa>(); 
		alternativas.add(alternativa);
		alternativas.add(alternativa2);
		alternativas.add(alternativa3);
		alternativas.add(alternativa4);
		alternativas.add(alternativa5);
		
		questao.setListaAlternativas(alternativas);
		
		if (!(resposta01==true || resposta02==true
							|| resposta03==true || resposta04==true
									|| resposta05==true)) {
			System.out.println("Indique uma alternativa correta");
		}else{
			
			
		try {				
			
			dao.create(questao);
			questao = new Questao();
			System.out.println("Questao cadastrada!");
							
		} catch (DBCommitException e) {
				System.out.println("Erro ao cadastrar!");
				e.printStackTrace();
			}
		}
		
		questoes = dao.buscarPorAula(aula);
		for (Questao q : questoes) {
			System.out.println("pergunta da questao"+q.getPergunta());
		}

		}
	
	}
