package br.com.joule.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.xml.soap.SOAPException;

import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.dao.AulaDAO;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.entity.Aula;
import br.com.joule.exceptions.DBCommitException;

@ManagedBean(name = "aulaBean")
@RequestScoped
public class AulaBean {

	private Aula aula;
	private AulaDAO dao;
	private String nomeBusca;
	private List<Aula> lista;
	private long codigo; 

	@PostConstruct
	public void init() {
		aula = new Aula();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new AulaDAOImpl(em);
		lista = dao.list();
	}

	public void cadastrar() {
		FacesMessage msg;
		if (dao.buscarPorNome(aula.getNome().toUpperCase())== null) {
			try {
				aula.setNome(aula.getNome().toUpperCase());
				dao.create(aula);
				msg = new FacesMessage("Aula cadastrada!");
			} catch (DBCommitException e) {
				msg = new FacesMessage("Erro ao cadastrar!");
				e.printStackTrace();
			}
		}else {
			msg = new FacesMessage("Uma aula com o mesmo nome já foi cadastrada!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluir(){
		FacesMessage msg;
		try {
			dao.delete(codigo);;
			msg = new FacesMessage("Excluido!");
			lista = dao.list(); //Atualizar a tabela...
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro ao excluir!");
			lista = dao.list();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String buscar(){
		FacesMessage msg;
		if(nomeBusca==""){
			msg=new FacesMessage("Informe o nome da aula para a busca");
		}else{
			if (lista==null) {
				msg= new FacesMessage("Nenhuma aula encontrada para a busca " + nomeBusca);
			}
			lista = dao.buscarNomes(nomeBusca);
			msg= new FacesMessage("Busca por: " + nomeBusca);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "cadastroAula";
	}
	
	public void atualizar() throws SOAPException{
		try {
			aula.setNome(aula.getNome().toUpperCase());
			dao.update(aula);
		} catch (DBCommitException e) {
			e.printStackTrace();
		}
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


	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}