package br.com.joule.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {
	
	private String usuarioOuEmail;
	private String senha;
	private String message;
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		em = EMFactorySingleton.getInstance().createEntityManager();
	}
	
	public void login() {
		if(usuarioOuEmail.isEmpty()) {
			message = "Informe um usuario ou um e-mail!";
			
		} else if(senha.isEmpty()) {
			message = "Informe a senha!";
			
		} else {
			
			AlunoDAO alunoDAO = new AlunoDAOImpl(em);
			
			Aluno aluno = alunoDAO.logar(usuarioOuEmail, senha);
			
			if(aluno != null) {
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("meusCursos.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				message = "Usuário/E-mail ou senha invalido!";
			}
		}
		
		FacesMessage msg = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuarioOuEmail() {
		return usuarioOuEmail;
	}

	public void setUsuarioOuEmail(String usuarioOuEmail) {
		this.usuarioOuEmail = usuarioOuEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}