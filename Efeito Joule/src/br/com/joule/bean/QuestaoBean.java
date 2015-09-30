package br.com.joule.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.QuestaoDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.QuestaoDAOImpl;
import br.com.joule.entity.Alternativa;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Questao;
import br.com.joule.exceptions.DBCommitException;

@ManagedBean(name = "questaoBean")
@ViewScoped
public class QuestaoBean {

	private Questao questao;
	private QuestaoDAO dao;
	private AulaDAO aulaDAO;
	private String nomeAula;
	private List<Alternativa> alternativas;
	private List<Questao> questoes;
	private Aula aula;
	private String descricao01;
	private boolean resposta01;
	private String descricao02;
	private boolean resposta02;
	private String descricao03;
	private boolean resposta03;
	private String descricao04;
	private boolean resposta04;
	private String descricao05;
	private boolean resposta05;

	@PostConstruct
	public void init() {
		questao = new Questao();
		aula = new Aula();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new QuestaoDAOImpl(em);
		aulaDAO = new AulaDAOImpl(em);
		questoes = null;
		alternativas = null;
	}

	public void cadastrar() {
		FacesMessage msg;
		questao.setAula(aula);

		alternativas = new ArrayList<Alternativa>();
				
		Alternativa alternativa = new Alternativa();
		alternativa.setDescricao(descricao01);
		alternativa.setResposta(resposta01);
		alternativa.setQuestao(questao);
				
		alternativas.add(alternativa);

		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao(descricao02);
		alternativa2.setResposta(resposta02);
		alternativa2.setQuestao(questao);
				
		alternativas.add(alternativa2);
				
		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao(descricao03);
		alternativa3.setResposta(resposta03);
		alternativa3.setQuestao(questao);
				
		alternativas.add(alternativa3);
				
		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao(descricao04);
		alternativa4.setResposta(resposta04);
		alternativa4.setQuestao(questao);
				
		alternativas.add(alternativa4);
				
		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao(descricao05);
		alternativa5.setResposta(resposta05);
		alternativa5.setQuestao(questao);
				
		alternativas.add(alternativa5);

				
			if (!(resposta01==true || resposta02==true
						|| resposta03==true || resposta04==true
								|| resposta05==true)) {
					msg = new FacesMessage("Indique uma alternativa correta");
			}else{
					if (descricao01=="" || descricao02==""
						|| descricao03=="" || descricao04==""
						|| descricao05=="") {
					msg = new FacesMessage("Digite todas as alternativas");
			}else {
				try {
					questao.setPergunta(questao.getPergunta().toUpperCase());
						
					questao.setListaAlternativas(alternativas);
					dao.create(questao);
					questao = new Questao();
					alternativa = new Alternativa();
					alternativa2 = new Alternativa();
					alternativa3 = new Alternativa();
					alternativa4 = new Alternativa();
					alternativa5 = new Alternativa();
					msg = new FacesMessage("Questao cadastrada!");
						
					} catch (DBCommitException e) {
						msg = new FacesMessage("Erro ao cadastrar!");
						e.printStackTrace();
					}
				}
			}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/*
	public void excluir(){
		FacesMessage msg;
		try {
			dao.delete(codigo);;
			msg = new FacesMessage("Excluido!");
			questoes = buscarPorAula(aula.getId());
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	*/
	public void buscarAula(){
		FacesMessage msg;
		if(nomeAula==""){
			msg=new FacesMessage("Informe o nome da aula para a busca");
		}else{
			aula=aulaDAO.buscarPorNome(nomeAula);			
			msg= new FacesMessage(null);
			if (aula!=null) {
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/*
	public void atualizar() {
		FacesMessage msg;
		questao.setAula(aula);
		if (aula==null) {
			msg = new FacesMessage("Escolha uma aula");
		}else {
			if (buscarPorPergunta(questao.getPergunta().toUpperCase()) != null) {
				
				alternativas = new ArrayList<Alternativa>();
				
				Alternativa alternativa = new Alternativa();
				alternativa.setDescricao(descricao01);
				alternativa.setResposta(resposta01);
				
				alternativas.add(alternativa);

				Alternativa alternativa2 = new Alternativa();
				alternativa2.setDescricao(descricao02);
				alternativa2.setResposta(resposta02);
				
				alternativas.add(alternativa2);
				
				Alternativa alternativa3 = new Alternativa();
				alternativa3.setDescricao(descricao03);
				alternativa3.setResposta(resposta03);
				
				alternativas.add(alternativa3);
				
				Alternativa alternativa4 = new Alternativa();
				alternativa4.setDescricao(descricao04);
				alternativa4.setResposta(resposta04);
				
				alternativas.add(alternativa4);
				
				Alternativa alternativa5 = new Alternativa();
				alternativa5.setDescricao(descricao05);
				alternativa5.setResposta(resposta05);
				
				alternativas.add(alternativa5);
				if (alternativas.isEmpty()) {
					msg = new FacesMessage("Digite as alternativas");
				}else {
					try {
						questao.setPergunta(questao.getPergunta().toUpperCase());
						
						questao.setListaAlternativas(alternativas);
						dao.update(questao);
						questoes = buscarPorAula(aula.getId());
						msg = new FacesMessage("Questao atualizada!");
					} catch (DBCommitException e) {
						msg = new FacesMessage("Erro ao atualizar!");
						e.printStackTrace();
					}
				}
			}else {
				msg = new FacesMessage("Já existe uma pergunta igual a esta cadastrada");
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);		
	}
	*/
	
	
	public Questao buscarPorPergunta(String pergunta){
		return dao.buscarPorPergunda(pergunta);
	}
	
	public List<Questao> buscarPorAula(Aula aula){
		return dao.buscarPorAula(aula);
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getNomeAula() {
		return nomeAula;
	}

	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public String getDescricao01() {
		return descricao01;
	}

	public void setDescricao01(String descricao01) {
		this.descricao01 = descricao01;
	}

	public boolean isResposta01() {
		return resposta01;
	}

	public void setResposta01(boolean resposta01) {
		this.resposta01 = resposta01;
	}

	public String getDescricao02() {
		return descricao02;
	}

	public void setDescricao02(String descricao02) {
		this.descricao02 = descricao02;
	}

	public boolean isResposta02() {
		return resposta02;
	}

	public void setResposta02(boolean resposta02) {
		this.resposta02 = resposta02;
	}

	public String getDescricao03() {
		return descricao03;
	}

	public void setDescricao03(String descricao03) {
		this.descricao03 = descricao03;
	}

	public boolean isResposta03() {
		return resposta03;
	}

	public void setResposta03(boolean resposta03) {
		this.resposta03 = resposta03;
	}

	public String getDescricao04() {
		return descricao04;
	}

	public void setDescricao04(String descricao04) {
		this.descricao04 = descricao04;
	}

	public boolean isResposta04() {
		return resposta04;
	}

	public void setResposta04(boolean resposta04) {
		this.resposta04 = resposta04;
	}

	public String getDescricao05() {
		return descricao05;
	}

	public void setDescricao05(String descricao05) {
		this.descricao05 = descricao05;
	}

	public boolean isResposta05() {
		return resposta05;
	}

	public void setResposta05(boolean resposta05) {
		this.resposta05 = resposta05;
	}

}