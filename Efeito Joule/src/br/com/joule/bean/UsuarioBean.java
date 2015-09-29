package br.com.joule.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.joule.dao.UsuarioDAO;
import br.com.joule.daoimpl.UsuarioDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	private Aluno usuario;
	private UsuarioDAO usuarioDAO;

	@PostConstruct
	public void init() {
		usuario = new Aluno();
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		usuarioDAO = new UsuarioDAOImpl(em);
	}
	
	public void save() {
		
		try {
			usuarioDAO.create(usuario);
		} catch (DBCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Aluno getUsuario() {
		return usuario;
	}

	public void setUsuario(Aluno usuario) {
		this.usuario = usuario;
	}
}