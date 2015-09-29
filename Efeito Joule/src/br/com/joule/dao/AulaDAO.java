package br.com.joule.dao;

import java.util.List;

import br.com.joule.entity.Aula;

public interface AulaDAO extends DAO <Aula, Long> {
	
	List<Aula> list();
	
	List<Aula> buscarPorNome(String nome);
	
}
