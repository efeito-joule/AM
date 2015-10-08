package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Aluno;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Matricula;

public interface MatriculaDAO extends DAO<Matricula, Long> {

	List<Matricula> buscarPorEmail(String email);
	
	Matricula buscarPorAlunoCurso(Aluno aluno, Curso curso);
	
	List<Matricula> buscarPorAluno(long aluno);
	
}
