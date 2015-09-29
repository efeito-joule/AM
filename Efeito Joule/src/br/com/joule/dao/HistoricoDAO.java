package br.com.joule.dao;

import br.com.joule.entity.Historico;

public interface HistoricoDAO extends DAO<Historico, Long> {

	Historico buscarPorAulaAluno(int aula, int aluno);

}
