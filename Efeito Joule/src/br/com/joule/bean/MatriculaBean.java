package br.com.joule.bean;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.soap.SOAPException;

import br.com.joule.dao.MatriculaDAO;
import br.com.joule.daoimpl.MatriculaDAOImpl;
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

	private MatriculaDAO dao;
	private Matricula matricula;
	
	@PostConstruct
	public void init(){
		matricula = new Matricula();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new MatriculaDAOImpl(em);
	}

	public Matricula buscarPorCodigo(long id){
		return dao.findById(id);
	}

	public List<Matricula> buscarPorEmail(String email){
		return dao.buscarPorEmail(email);
	}
	
	/*public void cadastrar(Matricula matricula, String email, int codCurso) throws SOAPException{
		//Buscar as matr�culas do aluno
		List <Matricula> matriculas = dao.buscarPorEmail(email);
		//Verificar se este aluno tem alguma matr�cula
		if (!matriculas.isEmpty()) {
			// listar as matr�culas do aluno
			for (Matricula mts : matriculas) {
				//Conferir se o aluno j� est� matriculado neste curso
				if (codCurso == mts.getCurso().getId()) {
					throw new SOAPException("Aluno j� cadastrado neste curso");
				}
			}
		}
		//Cadastrar a matr�cula
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
		if (dao.buscarPorEmail(matricula.getUsuario().getEmail()) == null){
			try {
				dao.create(matricula);
				msg = new FacesMessage("Matriculado com sucesso!");
			} catch (DBCommitException e) {
				e.printStackTrace();
				msg = new FacesMessage("Erro ao matricular!");
			}
		}else{
			msg = new FacesMessage("Email j� existente!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void deletar(int codigo)  throws SOAPException{
		try {
			dao.delete(matricula.getId());
		} catch (DBCommitException e) {
			throw new SOAPException("Erro ao deletar");
		} catch (IdNotFoundException e) {
			throw new SOAPException("C�digo do matricula n�o encontrado");
		}
	}
	
	/*public void atualizar (Matricula matricula) throws SOAPException{
		try {
			dao.update(matricula);
		} catch (DBCommitException e) {
			throw new SOAPException("Erro ao atualizar");
		}
	}*/
	
	// Renovar a matr�cula por mais seis meses
	public void renovar (String email, int codCurso) throws SOAPException{
		try {
			Matricula matricula = dao.buscarPorEmailCurso(email, codCurso);
			Calendar dataTermino = matricula.getDataTermino();
			//Uma matr�cula s� pode ser renovada quando a data de termino expirar
			if ( Calendar.getInstance().before(dataTermino)) {
				throw new SOAPException("N�o � permitido renovar uma matr�cula que ainda n�o expirou");
			}
			dataTermino.set(Calendar.MONTH, dataTermino.get(Calendar.MONTH)+6);
			matricula.setDataTermino(dataTermino);
			dao.update(matricula);
		} catch (DBCommitException e) {
			throw new SOAPException("Erro ao atualizar");
		}
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
}

