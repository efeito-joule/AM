package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Matricula;

public interface MatriculaDAO extends DAO<Matricula, Long> {

	List<Matricula> buscarPorEmail(String email);
	
	Matricula buscarPorEmailCurso (String email, int codigo);
	
	List<Matricula> buscarPorAluno(long aluno);
	
}
