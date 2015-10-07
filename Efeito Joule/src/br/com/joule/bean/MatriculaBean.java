package br.com.joule.bean;

import java.util.ArrayList;
import java.util.Calendar;
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
	private CursoDAO cursoDAO;;
	
	@PostConstruct
	public void init(){
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		matriculaDAO = new MatriculaDAOImpl(em);
		cursoDAO = new CursoDAOImpl(em);
		curso = new Curso();
		matricula = new Matricula();
		cursos = new ArrayList<Curso>();
		outroscursos = new ArrayList<Curso>();
		aluno =(Aluno) LoginBean.pegaUsuarioSessao();
		carregarCursos();
		carregarOutrosCursos();
		
	}
	public List<Curso> carregarCursos(){
		matriculas = matriculaDAO.buscarPorAluno(aluno.getId());
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
	
	public List<Curso> carregarOutrosCursos(){
		FacesMessage msg;
		outroscursos = cursoDAO.list();
		
		if (outroscursos!=null) {
			msg = new FacesMessage("Escolha um curso");
			return outroscursos;
		}else {
			msg = new FacesMessage("É preciso o cadastro de cursos");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}
	
	
	
	
	public Matricula buscarPorCodigo(long id){
		return matriculaDAO.findById(id);
	}

	public List<Matricula> buscarPorEmail(String email){
		return matriculaDAO.buscarPorEmail(email);
	}
	
	/*public void cadastrar(Matricula matricula, String email, int codCurso) throws SOAPException{
		//Buscar as matrículas do aluno
		List <Matricula> matriculas = dao.buscarPorEmail(email);
		//Verificar se este aluno tem alguma matrícula
		if (!matriculas.isEmpty()) {
			// listar as matrículas do aluno
			for (Matricula mts : matriculas) {
				//Conferir se o aluno já está matriculado neste curso
				if (codCurso == mts.getCurso().getId()) {
					throw new SOAPException("Aluno já cadastrado neste curso");
				}
			}
		}
		
		//Cadastrar a matrícula
		try {
			Calendar dataTermino = Calendar.getInstance();
			dataTermino.set(Calendar.MONTH, dataTermino.get(Calendar.MONTH)+6);
			matricula.setDataTermino(dataTermino);
			dao.create(matricula);
		} catch (DBCommitException e) {
			throw new SOAPException("Erro ao cadastrar");
		}	
	}*/
	
	public void cadastrar(){
		FacesMessage msg;
		if (matriculaDAO.buscarPorEmail(matricula.getUsuario().getEmail()) == null){
			try {
				matriculaDAO.create(matricula);
				msg = new FacesMessage("Matriculado com sucesso!");
			} catch (DBCommitException e) {
				e.printStackTrace();
				msg = new FacesMessage("Erro ao matricular!");
			}
		}else{
			msg = new FacesMessage("Email já existente!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void deletar(int codigo)  {

			try {
				matriculaDAO.delete(matricula.getId());
			} catch (DBCommitException | IdNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	
	// Renovar a matrícula por mais seis meses
	public void renovar (String email, int codCurso) {
		try {
			Matricula matricula = matriculaDAO.buscarPorEmailCurso(email, codCurso);
			Calendar dataTermino = matricula.getDataTermino();
			//Uma matrícula só pode ser renovada quando a data de termino expirar
			if ( Calendar.getInstance().before(dataTermino)) {
			
			}
			dataTermino.set(Calendar.MONTH, dataTermino.get(Calendar.MONTH)+6);
			matricula.setDataTermino(dataTermino);
			matriculaDAO.update(matricula);
		} catch (DBCommitException e) {
			
		}
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
	
	
	
}

