package br.com.joule.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.model.Alternativa;
import br.com.joule.model.Aula;
import br.com.joule.model.Questao;

@ManagedBean(name = "cadastroAulaController")
@RequestScoped
public class CadastroAulaController {

	private Aula aula;
	private Questao questao;
	private AulaDAOImpl<Aula> aulaDAOImpl;
	private String alternativa01;
	private boolean resposta01;
	private String alternativa02;
	private boolean resposta02;
	private String alternativa03;
	private boolean resposta03;
	private String alternativa04;
	private boolean resposta04;
	private String alternativa05;
	private boolean resposta05;

	@PostConstruct
	public void init() {
		aula = new Aula();
		questao = new Questao();
		aulaDAOImpl = new AulaDAOImpl<Aula>();
	}

	public void cadastrar() {
		
		List<Alternativa> listaAlternativa = new ArrayList<Alternativa>();
		
		Alternativa alternativa = new Alternativa();
		alternativa.setDescricao(alternativa01);
		alternativa.setResposta(resposta01);
		
		listaAlternativa.add(alternativa);

		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao(alternativa02);
		alternativa2.setResposta(resposta02);
		
		listaAlternativa.add(alternativa2);
		
		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao(alternativa03);
		alternativa3.setResposta(resposta03);
		
		listaAlternativa.add(alternativa3);
		
		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao(alternativa04);
		alternativa4.setResposta(resposta04);
		
		listaAlternativa.add(alternativa4);
		
		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao(alternativa05);
		alternativa5.setResposta(resposta05);
		
		listaAlternativa.add(alternativa5);
		
		questao.setListaAlternativas(listaAlternativa);
		
		List<Questao> listaQuestoes = new ArrayList<Questao>();
		listaQuestoes.add(questao);
		
		aula.setQuestoes(listaQuestoes);
		
		aulaDAOImpl.save(aula);
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getAlternativa01() {
		return alternativa01;
	}

	public void setAlternativa01(String alternativa01) {
		this.alternativa01 = alternativa01;
	}

	public boolean isResposta01() {
		return resposta01;
	}

	public void setResposta01(boolean resposta01) {
		this.resposta01 = resposta01;
	}

	public String getAlternativa02() {
		return alternativa02;
	}

	public void setAlternativa02(String alternativa02) {
		this.alternativa02 = alternativa02;
	}

	public boolean isResposta02() {
		return resposta02;
	}

	public void setResposta02(boolean resposta02) {
		this.resposta02 = resposta02;
	}

	public String getAlternativa03() {
		return alternativa03;
	}

	public void setAlternativa03(String alternativa03) {
		this.alternativa03 = alternativa03;
	}

	public boolean isResposta03() {
		return resposta03;
	}

	public void setResposta03(boolean resposta03) {
		this.resposta03 = resposta03;
	}

	public String getAlternativa04() {
		return alternativa04;
	}

	public void setAlternativa04(String alternativa04) {
		this.alternativa04 = alternativa04;
	}

	public boolean isResposta04() {
		return resposta04;
	}

	public void setResposta04(boolean resposta04) {
		this.resposta04 = resposta04;
	}

	public String getAlternativa05() {
		return alternativa05;
	}

	public void setAlternativa05(String alternativa05) {
		this.alternativa05 = alternativa05;
	}

	public boolean isResposta05() {
		return resposta05;
	}

	public void setResposta05(boolean resposta05) {
		this.resposta05 = resposta05;
	}
}