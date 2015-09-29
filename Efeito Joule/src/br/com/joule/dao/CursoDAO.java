package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Curso;

public interface CursoDAO extends DAO<Curso, Long> {

	List<Curso> list();
	
	List<Curso> buscarPorNome(String nome);

}
