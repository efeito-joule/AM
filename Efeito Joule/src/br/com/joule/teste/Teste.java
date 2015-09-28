package br.com.joule.teste;

import br.com.joule.daoimpl.UsuarioDAOImpl;
import br.com.joule.model.Aluno;

public class Teste {

	public static void main(String[] args) {

	
		Aluno usuario = new Aluno();
		usuario.setNomeUsuario("efeito_joule");
		usuario.setEmail("efeitojouleoficial@gmail.com");
		
		UsuarioDAOImpl<Aluno> usuarioDAO = new UsuarioDAOImpl<Aluno>();
		usuarioDAO.save(usuario);
		
	}
}