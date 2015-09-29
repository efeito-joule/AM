package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Alternativa;

public interface AlternativaDAO extends DAO<Alternativa, Long> {

	List<Alternativa> listarAlternativas(long questao);
}
