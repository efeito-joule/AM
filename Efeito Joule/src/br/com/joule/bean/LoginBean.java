package br.com.joule.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

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
	private static Aluno aluno;
	private FacesContext fc;
	private HttpSession session;
	
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
			
		    aluno = alunoDAO.logar(usuarioOuEmail, senha);
			
			if(aluno != null) {
				
				try {
					fc = FacesContext.getCurrentInstance();
					
					fc.getExternalContext().getSessionMap().put("alunoLogado", aluno);  
					session = (HttpSession) fc.getExternalContext().getSession(true);  
					session.setAttribute("alunoLogado", aluno);  
					
					fc.getExternalContext().redirect("meusCursos.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				message = "Usu�rio/E-mail ou senha invalido!";
				
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public static Object pegaUsuarioSessao(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// contexto da requisicao
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true); 
		// pega usuario da sessao
		Object currentUser = session.getAttribute("alunoLogado"); 
		Aluno aluno = (Aluno) currentUser; 
		return aluno;
	}
	
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();  
		session = (HttpSession) fc.getExternalContext().getSession(true);  
		
		//expira a sess�o  	
		session.invalidate();  

		FacesContext context = FacesContext.getCurrentInstance();    
		context.getExternalContext().getSessionMap().remove("alunoLogado");    
		context.getExternalContext().getSessionMap().clear();
		context.getExternalContext().getSessionMap().equals(null);

		try {
			fc.getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
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