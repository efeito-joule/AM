package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Historico;

public interface HistoricoDAO extends DAO<Historico, Long> {

	Historico buscarPorAulaAluno(long aula, long aluno);
	
	List<Historico> ListarTodosAula();
	
	List<Historico> ListarTodosGeral();

}
