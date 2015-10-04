package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Aula;

public interface AulaDAO extends DAO <Aula, Long> {
	
	List<Aula> list();
	
	Aula buscarPorNome(String nome);
	
	List<Aula> buscarNomes(String nome);
}
