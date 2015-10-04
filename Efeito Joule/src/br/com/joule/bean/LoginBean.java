package br.com.joule.bean;

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
			message = "Informe o usuário ou e-mail!";
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
				message = "Usuário/E-mail ou senha errados!";
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
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}