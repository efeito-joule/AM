package br.com.joule.dao;


import java.util.List;

import br.com.joule.entity.Aluno;

public interface AlunoDAO extends DAO<Aluno, Long>{

	public Aluno buscarEmail(String email);
	
	public Aluno logar(String usuarioOuEmail, String senha);
	public List<Aluno> buscaDadosUsuario(String usuarioOuEmail, String senha);
}
