package br.com.joule.teste;

import javax.persistence.EntityManager;

import br.com.joule.singleton.EMFactorySingleton;
import br.com.joule.daoimpl.UsuarioDAOImpl;
import br.com.joule.entity.Aluno;
import br.com.joule.exceptions.DBCommitException;

public class Teste {

	public static void main(String[] args) {

	
		Aluno usuario = new Aluno();
		usuario.setNomeUsuario("efeito_joule");
		usuario.setEmail("efeitojouleoficial@gmail.com");
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();	
		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(em);
		try {
			usuarioDAO.create(usuario);
		} catch (DBCommitException e) {
			
			e.printStackTrace();
		}
		
	}
}