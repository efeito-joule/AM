package br.com.joule.bean;


import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean
@RequestScoped
public class AlunoBean {

	private Aluno aluno;
	
	private AlunoDAO dao;
	
	private List<Aluno> alunos;

	private String email;
	
	private long id;


	@PostConstruct
	private void init(){
		EntityManager em= EMFactorySingleton.getInstance().createEntityManager();
		aluno=new Aluno();
		dao=new AlunoDAOImpl(em);
		aluno.setDataNascimento(Calendar.getInstance());
		alunos=dao.buscarEmail(email);
	}
	
	public void cadastrar(){
		FacesMessage msg;
		//buscar aluno por e-mail e verificar se já existe
		if (dao.buscarEmail(aluno.getEmail().toLowerCase()).isEmpty()) {
		try {
			aluno.setEmail(aluno.getEmail().toLowerCase());
			dao.create(aluno);
			msg=new FacesMessage("Aluno Cadastrado!");
		} catch (DBCommitException e) {
			e.printStackTrace();
			msg=new FacesMessage("Erro...");
		}
		}else {
			msg=new FacesMessage("E-mail já cadastrado");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deletar() throws Exception{
		FacesMessage msg;
		try {
			dao.delete(id);
			msg=new FacesMessage("Aluno excluido!");
		} catch (DBCommitException e) {
			msg=new FacesMessage("Erro...");
			e.printStackTrace();
		} 
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void buscar(){
		FacesMessage msg;
		email.toLowerCase();
		alunos=dao.buscarEmail(email);
		if(email==null){
			msg=new FacesMessage("Informe seu e-mail");
		}else{
			msg= new FacesMessage("Seu email é: " +email);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void atualizar(){
		FacesMessage msg;

		try {
			aluno.setEmail(aluno.getEmail().toLowerCase());
			dao.update(aluno);
			msg=new FacesMessage("Aluno Atualizado");
			alunos=dao.buscarEmail(email);
		} catch (DBCommitException e) {
			e.printStackTrace();
			msg=new FacesMessage("Aluno não Atualizado");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
