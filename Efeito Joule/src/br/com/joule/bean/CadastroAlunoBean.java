package br.com.joule.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Endereco;
import br.com.joule.entity.Telefone;
import br.com.joule.entity.TipoPessoa;
import br.com.joule.entity.UF;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "cadastroAlunoBean")
@RequestScoped
public class CadastroAlunoBean {

	private Aluno aluno;
	private Telefone telefone;
	private Endereco endereco;
	private TipoPessoa tipoPessoa;
	private UF uf;
	private String senha;
	private String confirmaSenha;
	private boolean masculino;
	private boolean feminino;
	private short ufSelect;
	private FacesMessage msg;
	private String message;
	private AlunoDAO alunoDAO;

	@PostConstruct
	public void init() {
		aluno = new Aluno();
		telefone = new Telefone();
		endereco = new Endereco();
		uf = new UF();
		uf.setId(Short.parseShort("1"));
		uf.setDescricao("SP");
		
		tipoPessoa = new TipoPessoa();
		tipoPessoa.setId(Short.parseShort("1"));
		tipoPessoa.setDescricao("aluno");
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		alunoDAO = new AlunoDAOImpl(em);
	}
	
	public void save() {
		
		try {
			if(aluno.getNomeUsuario().isEmpty()) {
				message = "Preencha o campo Usuário!";
				
			} else if(aluno.getEmail().isEmpty()) {
				message = "Preencha o campo E-mail!";
				
			} else if(senha.isEmpty()) {
				message = "Preencha o campo Senha!";
				
			} else if(confirmaSenha.isEmpty()) {
				message = "Confirme a Senha!";
				
			} else if(!senha.equals(confirmaSenha)) {
				message = "As senhas não coincidem!";
				
			} else if(aluno.getNome().isEmpty()) {
				message = "Preencha o campo Nome!";
				
			} else if(aluno.getSobrenome().isEmpty()) {
				message = "Preencha o campo Sobrenome!";
				
			} else if(masculino == false && feminino == false) {
				message = "Informe um Sexo!";
				
			} else if(aluno.getDataNascimento() == null) {
				message = "Informe a Data de Nascimento!";
				
			} else if(telefone.getDdd() == 0) {
				message = "informe o DDD!"; 
				
			} else if(telefone.getNumero().isEmpty()) {
				message = "Informe o Telefone!";
				
			} else {
				aluno.setTelefone(telefone);
//				endereco.setUf(uf);
				aluno.setEndereco(endereco);
//				aluno.setTipoPessoa(tipoPessoa);
				aluno.setSenha(senha);
				
				alunoDAO.create(aluno);
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("meusCursos.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public short getUfSelect() {
		return ufSelect;
	}

	public void setUfSelect(short ufSelect) {
		this.ufSelect = ufSelect;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}