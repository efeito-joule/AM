package br.com.joule.bean;


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
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
