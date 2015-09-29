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
	private CursoDAO dao;
	private String nomeBusca;
	private List<Curso> lista;
	private long codigo; 
	

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
			} catch (DBCommitException e) {
				msg = new FacesMessage("Erro ao cadastrar!");
				e.printStackTrace();
			}
		}else {
			msg = new FacesMessage("Um curso com o mesmo nome já foi cadastrado!");
		}	
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluir(){
		FacesMessage msg;
		try {
			dao.delete(codigo);;
			msg = new FacesMessage("Curso excluido!");
			lista = dao.list(); //Atualizar a tabela...
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	//Busca o curso pelo Nome
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
		
		// Método para atualizar o curso
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

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}
