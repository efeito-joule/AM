package br.com.joule.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.exceptions.DBCommitException;

@ManagedBean(name = "aulaBean")
@ViewScoped
public class AulaBean {

	private Aula aula;
	private AulaDAO dao;
	private String nomeBusca;
	private List<Aula> lista;
	private String nomeCurso;
	private CursoDAO cursoDAO;
	private Curso curso;
	private long id;

	@PostConstruct
	public void init() {
		aula = new Aula();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new AulaDAOImpl(em);
		lista = dao.list();
		cursoDAO = new CursoDAOImpl(em);
		curso = new Curso();
	}

	public void cadastrar() {
		FacesMessage msg;
		if (curso==null) {
			msg = new FacesMessage("Busque um curso");
		}else{	
			if (dao.buscarPorNome(aula.getNome().toUpperCase())== null) {
			try {
				aula.setNome(aula.getNome().toUpperCase());
				aula.setCurso(curso);
				dao.create(aula);
				msg = new FacesMessage("Aula cadastrada!");
				aula = new Aula();
				lista = dao.list();
			} catch (DBCommitException e) {
				msg = new FacesMessage("Erro ao cadastrar!");
				e.printStackTrace();
			}
		}else {
			msg = new FacesMessage("Uma aula com o mesmo nome já foi cadastrada!");
		}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluir(Aula a){
		FacesMessage msg;
		try {
			dao.delete(a.getId());;
			msg = new FacesMessage("Excluido!");
			lista = dao.list();
			aula = new Aula();
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir! ");
			lista = dao.list();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void buscar(){
		FacesMessage msg;
		if(nomeBusca==null){
			msg=new FacesMessage("Informe o nome da aula para a busca");
		}else{
			if (lista==null) {
				msg= new FacesMessage("Nenhuma aula encontrada para a busca " + nomeBusca);
			}
			lista = dao.buscarNomes(nomeBusca);
			msg= new FacesMessage("Busca por: " + nomeBusca);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void atualizar() {
		FacesMessage msg;
		try {
			aula.setNome(aula.getNome().toUpperCase());
			dao.update(aula);
			msg=new FacesMessage("Aula Atualizada");
			lista = dao.list();
		} catch (DBCommitException e) {
			e.printStackTrace();
			msg=new FacesMessage("Aula Não Atualizada");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/*public String editar() {
		aula = dao.findById(getId());
		
		return "editarAula.xhtml";
	}*/
	
	public String editar(Aula a){
		aula = a;
		return "editarAula.xhtml";
	}
	
	
	public void buscarCurso(){
		FacesMessage msg;
		if(nomeCurso==""){
			msg=new FacesMessage("Informe o nome da aula para a busca");
		}else{
			curso=cursoDAO.buscarPorNome(nomeCurso);			
			msg= new FacesMessage("Busca por: " + nomeCurso);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	public List<Aula> getLista() {
		return lista;
	}

	public void setLista(List<Aula> lista) {
		this.lista = lista;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}