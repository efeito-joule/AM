package br.com.joule.dao;


import java.util.List;

import br.com.joule.entity.Administrador;

public interface AdministradorDAO extends DAO<Administrador, Long>{

	public Administrador buscarEmail(String email);
	
	public Administrador logar(String usuarioOuEmail, String senha);
	
	public List<Administrador> buscaDadosUsuario(String usuarioOuEmail, String senha);
}
