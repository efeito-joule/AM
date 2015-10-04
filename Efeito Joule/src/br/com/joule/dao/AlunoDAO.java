package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Aluno;

public interface AlunoDAO extends DAO<Aluno, Long>{

	public List<Aluno> buscarEmail(String email);
	
	public Aluno logar(String usuarioOuEmail, String senha);
}
