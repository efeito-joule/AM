package br.com.joule.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.dao.MatriculaDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.daoimpl.MatriculaDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Matricula;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "assistirBean")
@ViewScoped
public class AssistirBean {

	private EntityManager em = null;
	private MatriculaDAO matriculaDAO;
	private AulaDAO aulaDAO;
	private CursoDAO cursoDAO;
	private Aluno aluno;
	private Aula aula;
	private Curso curso;
	private List<Matricula> matriculas;
	private List<Curso> cursos;
	private List<Aula> aulas;
	private long idResposta;
	private String resposta;
	private String video;

	
	@PostConstruct
	public void init() {
		em = EMFactorySingleton.getInstance().createEntityManager();
		matriculaDAO = new MatriculaDAOImpl(em);
		aulaDAO = new AulaDAOImpl(em);
		cursoDAO = new CursoDAOImpl(em);
		aula = new Aula();
		curso = new Curso();
		resposta = null;
		cursos = new ArrayList<Curso>();
		video= null;
		aluno =(Aluno) LoginBean.pegaAlunoSessao();
		carregarCursos();
	}
	
	public List<Curso> carregarCursos(){
		FacesMessage msg;
		matriculas = matriculaDAO.buscarPorAluno(aluno.getId());
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
		if (resposta=="") {
			msg = new FacesMessage("Informe um curso, se você ainda não fez"
					+ " uma mátricula vá para a página meus cursos!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		idResposta =Long.parseLong(resposta);
		curso = cursoDAO.findById(idResposta);
		aulas = aulaDAO.buscarPorCurso(curso);
		resposta = null;
		if (aulas==null) {
			msg = new FacesMessage("Desculpe mas este curso são"
					+ " possui aulas cadastradas");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		return aulas;
	}
	
	public String mostrarAula(){
		video=null;
		FacesMessage msg;
		if (resposta=="") {
			msg = new FacesMessage("Informe uma aula!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		idResposta =Long.parseLong(resposta);
		aula = aulaDAO.findById(idResposta);
		video = aula.getUrlVideo();
		return video;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(long idResposta) {
		this.idResposta = idResposta;
	}
	
}
