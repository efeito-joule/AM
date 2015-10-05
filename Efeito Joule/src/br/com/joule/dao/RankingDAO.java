package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Ranking;

public interface RankingDAO extends DAO<Ranking, Long> {

	Ranking buscarPorAluno(long aluno);
	
	List<Ranking> ListarTodos();

}
