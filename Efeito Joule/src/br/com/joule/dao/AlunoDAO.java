package br.com.joule.dao;


import br.com.joule.entity.Aluno;

public interface AlunoDAO extends DAO<Aluno, Long>{

	public Aluno buscarEmail(String email);
	
	public Aluno logar(String usuarioOuEmail, String senha);
}
