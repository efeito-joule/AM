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

@ManagedBean(name = "cadastroAlunoBean")
@RequestScoped
public class CadastroAlunoBean {

	private Aluno aluno;
	private String senha;
	private String confirmaSenha;
	private boolean masculino;
	private boolean feminino;
	private FacesMessage msg;
	private String message;
	private AlunoDAO alunoDAO;

	@PostConstruct
	public void init() {
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		alunoDAO = new AlunoDAOImpl(em);
		aluno = new Aluno();
	}
	
	public void cadastrar() {
		
		try {
			if(aluno.getNomeUsuario().isEmpty()) {
				message = "Preencha o campo Usu�rio!";
				
			} else if(aluno.getEmail().isEmpty()) {
				message = "Preencha o campo E-mail!";
				
			} else if(senha.isEmpty()) {
				message = "Preencha o campo Senha!";
				
			} else if(confirmaSenha.isEmpty()) {
				message = "Confirme a Senha!";
				
			} else if(!senha.equals(confirmaSenha)) {
				message = "As senhas n�o coincidem!";
				
			} else if(aluno.getNome().isEmpty()) {
				message = "Preencha o campo Nome!";
				
			} else if(aluno.getSobrenome().isEmpty()) {
				message = "Preencha o campo Sobrenome!";
				
			} else if(masculino == false && feminino == false) {
				message = "Informe um Sexo!";
				
			} else if(aluno.getDataNascimento() == null) {
				message = "Informe a Data de Nascimento!";
				
			} else if(alunoDAO.buscarEmail(aluno.getEmail())!=null) {
				message = "Este e-mail j� tem um cadastro!";
			}else {
				aluno.setSenha(senha);
				System.out.println("antes de criar o aluno");
				alunoDAO.create(aluno);
				message = "Cadastro efetuado com sucesso!";
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

	public boolean isMasculino() {
		return masculino;
	}

	public void setMasculino(boolean masculino) {
		this.masculino = masculino;
	}

	public boolean isFeminino() {
		return feminino;
	}

	public void setFeminino(boolean feminino) {
		this.feminino = feminino;
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