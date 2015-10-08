package br.com.joule.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "editarAlunoBean")
@RequestScoped
public class EditarAlunoBean {

	private Aluno aluno;
	private String senha;
	private String confirmaSenha;
	private FacesMessage msg;
	private String message;
	private AlunoDAO alunoDAO;

	@PostConstruct
	public void init() {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		alunoDAO = new AlunoDAOImpl(em);
		aluno =(Aluno) LoginBean.pegaAlunoSessao();
	}
	
	
public void atualizar() {
		
		try {
			if(aluno.getNomeUsuario().isEmpty()) {
				message = "Preencha o campo Usu·rio!";
				
			} else if(aluno.getEmail().isEmpty()) {
				message = "Preencha o campo E-mail!";
				
			} else if(senha.isEmpty()) {
				message = "Preencha o campo Senha!";
				
			} else if(confirmaSenha.isEmpty()) {
				message = "Confirme a Senha!";
				
			} else if(!senha.equals(confirmaSenha)) {
				message = "As senhas n√£o coincidem!";
				
			} else if(aluno.getNome().isEmpty()) {
				message = "Preencha o campo Nome!";
				
			} else if(aluno.getSobrenome().isEmpty()) {
				message = "Preencha o campo Sobrenome!";
				
			} else if(aluno.getDataNascimento() == null) {
				message = "Informe a Data de Nascimento!";
				
			}  else {
				aluno.setSenha(senha);
				
				alunoDAO.update(aluno);
			}
			msg = new FacesMessage(message);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FacesMessage getMsg() {
		return msg;
	}

	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}
	
}