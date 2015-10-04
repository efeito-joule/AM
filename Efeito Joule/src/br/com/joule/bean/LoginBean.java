package br.com.joule.bean;

<<<<<<< HEAD
import java.io.IOException;

import javax.annotation.PostConstruct;
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
	private AlunoDAO alunoDAO;
	private String message;
	private static Aluno aluno;
	
	@PostConstruct
	public void init() {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		alunoDAO = new AlunoDAOImpl(em);
	}
	
	public void login() {
		
		if(usuarioOuEmail.isEmpty()) {
			message = "Informe o usuÃ¡rio ou e-mail!";
		} else if(senha.isEmpty()) {
			message = "Informe a senha!";
		} else {

			aluno = alunoDAO.logar(usuarioOuEmail, senha);
			
			if(aluno != null) {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("meusCursos.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}  
			} else {
				message = "UsuÃ¡rio/E-mail ou senha errados!";
			}
		}
	}
	
	public static int tipoPessoa() {
		return aluno.getTipoPessoa().getId();
	}
	
	public String getUsuarioOuEmail() {
		return usuarioOuEmail;
	}

	public void setUsuarioOuEmail(String usuarioOuEmail) {
		this.usuarioOuEmail = usuarioOuEmail;
=======

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
	
	private String login;
	
	private String senha;
	
	public String logar(){
		if (login.equals(login) && senha.equals(senha)){
			return "home";
		}else{
			
			FacesMessage msg = 
					new FacesMessage("Usuário e/ou senha inválidos");
			FacesContext.getCurrentInstance()
									.addMessage(null, msg);
			return "index";
		}
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
>>>>>>> ebf2c88001a96b29b338a862f83b1107057c14be
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
<<<<<<< HEAD

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
=======
	
	
}
>>>>>>> ebf2c88001a96b29b338a862f83b1107057c14be
