package br.com.joule.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.joule.daoimpl.UsuarioDAOImpl;
import br.com.joule.model.Aluno;

@ManagedBean(name = "cadastroUsuarioController")
@RequestScoped
public class CadastroUsuarioController {

	private Aluno usuario;
	private UsuarioDAOImpl<Aluno> usuarioDAO;

	@PostConstruct
	public void init() {
		usuario = new Aluno();
		usuarioDAO = new UsuarioDAOImpl<Aluno>();
	}
	
	public void save() {
		
		usuarioDAO.save(usuario);
	}
	
	public Aluno getUsuario() {
		return usuario;
	}

	public void setUsuario(Aluno usuario) {
		this.usuario = usuario;
	}
}