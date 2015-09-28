package br.com.joule.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "cursoBean")
@RequestScoped
public class CursoBean {

	private Curso curso;
	private CursoDAO cursoDAO;
	private String nomeBusca;
	private List<Curso> lista;
	private long codigo; //Remoção
	

	@PostConstruct
	public void init() {
		curso = new Curso();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		cursoDAO = new CursoDAOImpl(em);
		lista = cursoDAO.list();
	}
	
	public void cadastrar() {
		FacesMessage msg;
		try {
			cursoDAO.create(curso);
			msg = new FacesMessage("Curso cadastrado!");
		} catch (DBCommitException e) {
			msg = new FacesMessage("Erro ao cadastrar!");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluir(){
		FacesMessage msg;
		try {
			cursoDAO.delete(codigo);;
			msg = new FacesMessage("Curso excluido!");
			lista = cursoDAO.list(); //Atualizar a tabela...
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	public List<Curso> getLista() {
		return lista;
	}

	public void setLista(List<Curso> lista) {
		this.lista = lista;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}
