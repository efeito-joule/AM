package br.com.joule.dao;


import java.util.List;

import br.com.joule.entity.Aula;
import br.com.joule.entity.Questao;

public interface QuestaoDAO extends DAO<Questao, Long> {

	Questao buscarPorPergunda(String pergunta);

	List<Questao> buscarPorAula(Aula aula);
	
}
