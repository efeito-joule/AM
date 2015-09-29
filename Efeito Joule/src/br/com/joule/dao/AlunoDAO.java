package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Aluno;

public interface AlunoDAO extends DAO<Aluno, Long>{

	public List<Aluno> buscarEmail(String email);
	
	boolean logar(String email, String senha);
}
