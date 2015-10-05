package br.com.joule.bean;


import java.util.Calendar;

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
	
	private Aluno aluno1;

	private String email;
	
	private long id;


	@PostConstruct
	private void init(){
		EntityManager em= EMFactorySingleton.getInstance().createEntityManager();
		aluno=new Aluno();
		dao=new AlunoDAOImpl(em);
		aluno.setDataNascimento(Calendar.getInstance().getTime());
		aluno1=dao.buscarEmail(email);
	}
	
	public void cadastrar(){
		FacesMessage msg;
		//buscar aluno por e-mail e verificar se já existe
		if (dao.buscarEmail(aluno.getEmail().toLowerCase())==null) {
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
		aluno1=dao.buscarEmail(email);
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
			aluno1=dao.buscarEmail(email);
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

	public Aluno getAluno1() {
		return aluno1;
	}

	public void setAluno1(Aluno aluno1) {
		this.aluno1 = aluno1;
	}
	

}
