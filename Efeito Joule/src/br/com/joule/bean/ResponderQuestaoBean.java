package br.com.joule.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.dao.HistoricoDAO;
import br.com.joule.dao.MatriculaDAO;
import br.com.joule.dao.QuestaoDAO;
import br.com.joule.dao.RankingDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.daoimpl.HistoricoDAOImpl;
import br.com.joule.daoimpl.MatriculaDAOImpl;
import br.com.joule.daoimpl.QuestaoDAOImpl;
import br.com.joule.daoimpl.RankingDAOImpl;
import br.com.joule.entity.Alternativa;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Historico;
import br.com.joule.entity.Matricula;
import br.com.joule.entity.Questao;
import br.com.joule.entity.Ranking;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "responderQuestaoBean")
@ViewScoped
public class ResponderQuestaoBean {

	private EntityManager em = null;
	private Historico historico;
	private HistoricoDAO histDAO;
	private MatriculaDAO matriculaDAO;
	private RankingDAO rankingDAO;
	private AulaDAO aulaDAO;
	private CursoDAO cursoDAO;
	private AlunoDAO alunoDAO;
	private QuestaoDAO questaoDAO;
	private Questao questao;
	private Aluno aluno;
	private Aula aula;
	private Curso curso;
	private Ranking ranking;
	private List<Matricula> matriculas;
	private List<Curso> cursos;
	private List<Aula> aulas;
	private List<Questao> questoes;
	private List<Alternativa> alternativas;
	private long idresposta;
	private String email;
	private int index;
	private Alternativa alternativa1;
	private Alternativa alternativa2;
	private Alternativa alternativa3;
	private Alternativa alternativa4;
	private Alternativa alternativa5;
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
	private String resposta;
	
	@PostConstruct
	public void init() {
		em = EMFactorySingleton.getInstance().createEntityManager();
		historico = new Historico();
		histDAO = new HistoricoDAOImpl(em);
		matriculaDAO = new MatriculaDAOImpl(em);
		aulaDAO = new AulaDAOImpl(em);
		rankingDAO = new RankingDAOImpl(em);
		cursoDAO = new CursoDAOImpl(em);
		alunoDAO = new AlunoDAOImpl(em);
		questaoDAO = new QuestaoDAOImpl(em);
		aluno = null;
		aula = new Aula();
		curso = new Curso();
		questao = new Questao();
		questoes = null;
		ranking = new Ranking();
		idresposta=0;
		resposta = null;
		cursos = new ArrayList<Curso>();
		alternativas = new ArrayList<Alternativa>();
		alternativa1 = new Alternativa();
		alternativa2 = new Alternativa();
		alternativa3 = new Alternativa();
		alternativa4= new Alternativa();
		alternativa5 = new Alternativa();
		index = 0;
	}
	
	public void carregarAluno(){
	
	FacesMessage msg;
	if (email=="") {
		msg=new FacesMessage("Informe o nome seu e-mail");
	}else {
		aluno = alunoDAO.buscarEmail(email);
		msg = new FacesMessage("Agora selecione um curso");
		if (aluno == null) {
			msg = new FacesMessage("Informe um e-mail correto");
		}else {
			matriculas = matriculaDAO.buscarPorAluno(aluno.getId());
			carregarCursos();
		}
	}
	FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void atualizaRanking(){
		StoredProcedureQuery query =  em.createNamedStoredProcedureQuery("proc");
		query.execute();
	}
	
	public List<Curso> carregarCursos(){
		FacesMessage msg;
		msg = new FacesMessage("Escolha um curso");
		for (Matricula matricula : matriculas) {
			cursos.add(matricula.getCurso());
		}
		if (cursos!=null) {
			msg = new FacesMessage("Escolha um curso");
			return cursos;
		}else {
			
			msg = new FacesMessage("Matricule-se em um curso primeiro");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}
	
	public List<Aula> carregarAulas(){
		FacesMessage msg;
		msg = new FacesMessage("Escolha uma aula");
		idresposta =Long.parseLong(resposta);
		curso = cursoDAO.findById(idresposta);
		aulas = aulaDAO.buscarPorCurso(curso);
		resposta = null;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return aulas;
	}

	public Questao carregarQuestoes(){
		idresposta =Long.parseLong(resposta);
		aula = aulaDAO.findById(idresposta);
		System.out.println("Estou aqui 1");
		questoes = questaoDAO.buscarPorAula(aula);
		System.out.println("Estou aqui 2");
		questao = questoes.get(index);
		System.out.println("Estou aqui 3");
		index = index +1;
		System.out.println("Estou aqui 4");
		FacesMessage msg;
		if (questao ==null) {
			System.out.println("Estou aqui 5");
			msg = new FacesMessage("Esta aula não possui mais questões");
			return null;
		}else {
			System.out.println("Estou aqui 6");
			alternativas = questao.getListaAlternativas();
			System.out.println("Estou aqui 7");
			alternativa1 = alternativas.get(0);
			System.out.println("Estou aqui 8");
			alternativa2 = alternativas.get(1);
			System.out.println("Estou aqui 9");
			alternativa3 = alternativas.get(2);
			System.out.println("Estou aqui 10");
			alternativa4 = alternativas.get(3);
			System.out.println("Estou aqui 11");
			alternativa5 = alternativas.get(4);
			System.out.println("Estou aqui 12");
			
			descricao01 = alternativa1.getDescricao();
			System.out.println("Estou aqui 13");
			descricao02 = alternativa2.getDescricao();
			System.out.println("Estou aqui 14");
			descricao03 = alternativa3.getDescricao();
			System.out.println("Estou aqui 15");
			descricao04 = alternativa4.getDescricao();
			System.out.println("Estou aqui 16");
			descricao05 = alternativa5.getDescricao();
			System.out.println("Estou aqui 17");
			
			
			msg = new FacesMessage("Responda esta questão");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("Estou aqui 18");
			return questao;
		}
	}
	
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public long getIdresposta() {
		return idresposta;
	}

	public void setIdresposta(long idresposta) {
		this.idresposta = idresposta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Alternativa getAlternativa1() {
		return alternativa1;
	}

	public void setAlternativa1(Alternativa alternativa1) {
		this.alternativa1 = alternativa1;
	}

	public Alternativa getAlternativa2() {
		return alternativa2;
	}

	public void setAlternativa2(Alternativa alternativa2) {
		this.alternativa2 = alternativa2;
	}

	public Alternativa getAlternativa3() {
		return alternativa3;
	}

	public void setAlternativa3(Alternativa alternativa3) {
		this.alternativa3 = alternativa3;
	}

	public Alternativa getAlternativa4() {
		return alternativa4;
	}

	public void setAlternativa4(Alternativa alternativa4) {
		this.alternativa4 = alternativa4;
	}

	public Alternativa getAlternativa5() {
		return alternativa5;
	}

	public void setAlternativa5(Alternativa alternativa5) {
		this.alternativa5 = alternativa5;
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