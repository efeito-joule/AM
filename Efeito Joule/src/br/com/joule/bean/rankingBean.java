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

import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.HistoricoDAO;
import br.com.joule.dao.MatriculaDAO;
import br.com.joule.dao.RankingDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.HistoricoDAOImpl;
import br.com.joule.daoimpl.MatriculaDAOImpl;
import br.com.joule.daoimpl.RankingDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Historico;
import br.com.joule.entity.Matricula;
import br.com.joule.entity.Ranking;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "rankingBean")
@ViewScoped
public class RankingBean {

	private EntityManager em = null;
	private Historico historico;
	private int posicaoAulaAluno;
	private int posicaoTotalAluno;
	private HistoricoDAO histDAO;
	private MatriculaDAO matriculaDAO;
	private RankingDAO rankingDAO;
	private AulaDAO aulaDAO;
	private Aluno aluno;
	private Aula aula;
	private Curso curso;
	private Ranking ranking;
	private List<Integer> posicoesTotais;
	private List<Integer> posicoesAulas;
	private List<Ranking> rankingsGeral;
	private List<Historico> historicosAula;
	private List<Matricula> matriculas;
	private List<Curso> cursos;
	private List<Aula> aulas;
	private long id;
	
	
	@PostConstruct
	public void init() {
		historico = new Historico();
		em = EMFactorySingleton.getInstance().createEntityManager();
		histDAO = new HistoricoDAOImpl(em);
		matriculaDAO = new MatriculaDAOImpl(em);
		aulaDAO = new AulaDAOImpl(em);
		rankingDAO = new RankingDAOImpl(em);
		aluno = new Aluno();
		aula = new Aula();
		curso = new Curso();
		ranking = new Ranking();
		rankingsGeral = rankingDAO.ListarTodos();
		//matriculas = matriculaDAO.buscarPorAluno(aluno);
		aulas = null;
		id = 1;
		matriculas = matriculaDAO.buscarPorAluno(id);
		cursos = new ArrayList<Curso>();
		
	}
	
	public void atualizaRanking(){
		StoredProcedureQuery query =  em.createNamedStoredProcedureQuery("proc");
		query.execute();
	}
	
	public List<Aula> carregarAulas(){
		aulas = aulaDAO.buscarPorCurso(curso);
		System.out.println("Existem aulas parei aqui");
		return aulas;
	}
	
	public List<Curso> carregarCursos(){
		FacesMessage msg;
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

	public void mostrarRanking(){
		atualizaRanking();
		historico = histDAO.buscarPorAulaAluno(aula.getId(), aluno.getId());
		posicaoAulaAluno = historico.getPosicaoAula();
		posicaoTotalAluno = ranking.getPosicaoTotal();
		historicosAula = histDAO.ListarTodosAula();
	}
	
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public int getPosicaoAula() {
		return posicaoAulaAluno;
	}

	public void setPosicaoAula(int posicaoAula) {
		this.posicaoAulaAluno = posicaoAula;
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

	public int getPosicaoAulaAluno() {
		return posicaoAulaAluno;
	}

	public void setPosicaoAulaAluno(int posicaoAulaAluno) {
		this.posicaoAulaAluno = posicaoAulaAluno;
	}

	public int getPosicaoTotalAluno() {
		return posicaoTotalAluno;
	}

	public void setPosicaoTotalAluno(int posicaoTotalAluno) {
		this.posicaoTotalAluno = posicaoTotalAluno;
	}

	public List<Integer> getPosicoesTotais() {
		return posicoesTotais;
	}

	public void setPosicoesTotais(List<Integer> posicoesTotais) {
		this.posicoesTotais = posicoesTotais;
	}

	public List<Integer> getPosicoesAulas() {
		return posicoesAulas;
	}

	public void setPosicoesAulas(List<Integer> posicoesAulas) {
		this.posicoesAulas = posicoesAulas;
	}

	public List<Historico> getHistoricosAula() {
		return historicosAula;
	}

	public void setHistoricosAula(List<Historico> historicosAula) {
		this.historicosAula = historicosAula;
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

	public List<Ranking> getRankingsGeral() {
		return rankingsGeral;
	}

	public void setRankingsGeral(List<Ranking> rankingsGeral) {
		this.rankingsGeral = rankingsGeral;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
	
}