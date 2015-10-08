package br.com.joule.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.joule.dao.CursoDAO;
import br.com.joule.dao.MatriculaDAO;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.daoimpl.MatriculaDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Matricula;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.exceptions.IdNotFoundException;
import br.com.joule.singleton.EMFactorySingleton;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="matriculaBean")
@ViewScoped
public class MatriculaBean {

	private Matricula matricula;
	private Aluno aluno;
	private Curso curso;
	private MatriculaDAO matriculaDAO;
	private List<Matricula> matriculas;
	private List<Curso> cursos;
	private List<Curso> outroscursos;
	private CursoDAO cursoDAO;
	Calendar dataInicio;
	Calendar dataTermino;
	Date data;
	SimpleDateFormat sdf;
	
	@PostConstruct
	public void init(){
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		matriculaDAO = new MatriculaDAOImpl(em);
		cursoDAO = new CursoDAOImpl(em);
		curso = new Curso();
		matricula = new Matricula();
		cursos = new ArrayList<Curso>();
		outroscursos = new ArrayList<Curso>();
		aluno =(Aluno) LoginBean.pegaAlunoSessao();
		carregarCursos();
		carregarOutrosCursos();
		dataInicio = Calendar.getInstance();
		dataTermino = Calendar.getInstance();
		data = dataTermino.getTime();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	public List<Curso> carregarCursos(){
		matriculas = matriculaDAO.buscarPorAluno(aluno.getId());
		FacesMessage msg;
		if (matriculaDAO.buscarPorAluno(aluno.getId())==null) {
			msg = new FacesMessage("Matricule-se em um curso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		for (Matricula matricula : matriculas) {
			cursos.add(matricula.getCurso());
		}
		
		return cursos;
	}
	
	public List<Curso> carregarOutrosCursos(){
		outroscursos = cursoDAO.list();
		for (Curso curso : cursos) {
			outroscursos.remove(curso);
		}
		if (outroscursos!=null) {
			return outroscursos;
		}else {
			return null;
		}
	}
	
	public Matricula buscarPorCodigo(long id){
		return matriculaDAO.findById(id);
	}

	public List<Matricula> buscarPorEmail(String email){
		return matriculaDAO.buscarPorEmail(email);
	}
	
	public void cadastrar(Curso curso){
			dataTermino.set(Calendar.MONTH, dataTermino.get(Calendar.MONTH)+6);
			matricula.setDataTermino(dataTermino);
			
			matricula.setDataInicio(dataInicio);
			matricula.setCurso(curso);
			matricula.setUsuario(aluno);
			FacesMessage msg;
			try {
				matriculaDAO.create(matricula);
				msg = new FacesMessage("Matricula efetuada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (DBCommitException e) {
				e.printStackTrace();
			}
			cursos = new ArrayList<Curso>();
			outroscursos = new ArrayList<Curso>();
			curso = new Curso();
			matricula = new Matricula();
			carregarCursos();
			carregarOutrosCursos();
			
	}
	
	public void deletar(Curso curso)  {
		matricula = matriculaDAO.buscarPorAlunoCurso(aluno, curso);
		
		FacesMessage msg;	
		try {
			matriculaDAO.delete(matricula.getId());
			msg = new FacesMessage("Matricula excluida com sucesso!");
				
		} catch (DBCommitException | IdNotFoundException e) {
			msg = new FacesMessage("Desculpe mas esta matrícula já não pode"
					+ " ser excluída");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		cursos = new ArrayList<Curso>();
		outroscursos = new ArrayList<Curso>();
		curso = new Curso();
		matricula = new Matricula();
		carregarCursos();
		carregarOutrosCursos();
	}
	
	
	// Renovar a matrícula por mais seis meses
	public void renovar (Curso curso) {
		FacesMessage msg;
			matricula = matriculaDAO.buscarPorAlunoCurso(aluno, curso);
			dataTermino = matricula.getDataTermino();
			//Uma matrícula só pode ser renovada quando a data de termino expirar
			if ( Calendar.getInstance().before(dataTermino)) {
				data = dataTermino.getTime();
				msg = new FacesMessage("Sua matrícula ainda vai durar até o dia: "+sdf.format(data));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else {
				dataTermino.set(Calendar.MONTH, dataTermino.get(Calendar.MONTH)+6);
				matricula.setDataTermino(dataTermino);
				try {
					matriculaDAO.update(matricula);
				} catch (DBCommitException e) {
					e.printStackTrace();
				}
			}
			cursos = new ArrayList<Curso>();
			outroscursos = new ArrayList<Curso>();
			curso = new Curso();
			matricula = new Matricula();
			carregarCursos();
			carregarOutrosCursos();
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
	public List<Curso> getOutroscursos() {
		return outroscursos;
	}
	public void setOutroscursos(List<Curso> outroscursos) {
		this.outroscursos = outroscursos;
	}
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendar getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
}

