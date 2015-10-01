package br.com.joule.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean(name = "cursoBean")
@ViewScoped
public class CursoBean {

	private Curso curso;
	private CursoDAO dao;
	private String nomeBusca;
	private List<Curso> lista;
	
	@PostConstruct
	public void init() {
		curso = new Curso();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new CursoDAOImpl(em);
		lista = dao.list();
	}
	
	public void cadastrar() {
		FacesMessage msg;
		if (dao.buscarPorNome(curso.getNome().toUpperCase())==null) {
			try {
				curso.getNome().toUpperCase();
				dao.create(curso);
				msg = new FacesMessage("Curso cadastrado!");
				curso = new Curso();
				lista = dao.list();
			} catch (DBCommitException e) {
				msg = new FacesMessage("Erro ao cadastrar!");
				e.printStackTrace();
			}
		}else {
			msg = new FacesMessage("Um curso com o mesmo nome já foi cadastrado!");
		}	
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluir(Curso curso){
		FacesMessage msg;
		try {
			dao.delete(curso.getId());;
			msg = new FacesMessage("Curso excluido!");
			lista = dao.list(); 
			curso = new Curso();
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir!");
			lista = dao.list();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void buscar(){
		FacesMessage msg;
		try {
			if(nomeBusca == null){
				msg=new FacesMessage("Informe o nome do curso");
			}else{
					msg=new FacesMessage("O curso escolhido foi: " + nomeBusca);
					lista =dao.buscarNomes(nomeBusca);
			}
			} catch (Exception e) {
					e.printStackTrace();
					msg=new FacesMessage("Curso não encontrado");
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		public String editar(Curso c){
			curso = c;
			return "editarCurso.xhtml";
		}
		
		public void atualizar(){
				FacesMessage msg;
				try {
					curso.getNome().toUpperCase();
					dao.update(curso);
					msg=new FacesMessage("Curso Atualizado");
					lista=dao.list();
				} catch (DBCommitException e) {
					e.printStackTrace();
					msg=new FacesMessage("Curso não Atualizado");
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

}
