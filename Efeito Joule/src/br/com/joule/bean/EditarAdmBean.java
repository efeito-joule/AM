package br.com.joule.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AdministradorDAO;
import br.com.joule.daoimpl.AdministradorDAOImpl;
import br.com.joule.entity.Administrador;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "editarAdmBean")
@RequestScoped
public class EditarAdmBean {

	private Administrador administrador;
	private String senha;
	private String confirmaSenha;
	private FacesMessage msg;
	private String message;
	private AdministradorDAO administradorDAO;

	@PostConstruct
	public void init() {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		administradorDAO = new AdministradorDAOImpl(em);
		administrador =(Administrador) LoginBean.pegaAdmSessao();
	}
	
	
public void atualizar() {
		
		try {
			if(administrador.getNomeUsuario().isEmpty()) {
				message = "Preencha o campo Usu·rio!";
				
			} else if(administrador.getEmail().isEmpty()) {
				message = "Preencha o campo E-mail!";
				
			} else if(senha.isEmpty()) {
				message = "Preencha o campo Senha!";
				
			} else if(confirmaSenha.isEmpty()) {
				message = "Confirme a Senha!";
				
			} else if(!senha.equals(confirmaSenha)) {
				message = "As senhas n√£o coincidem!";
				
			} else if(administrador.getNome().isEmpty()) {
				message = "Preencha o campo Nome!";
				
			} else if(administrador.getSobrenome().isEmpty()) {
				message = "Preencha o campo Sobrenome!";
				
			} else if(administrador.getDataNascimento() == null) {
				message = "Informe a Data de Nascimento!";
				
			}  else {
				administrador.setSenha(senha);
				administradorDAO.update(administrador);
				message = "Atualizado!";
			}
			msg = new FacesMessage(message);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Administrador getadministrador() {
		return administrador;
	}

	public void setadministrador(Administrador administrador) {
		this.administrador = administrador;
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