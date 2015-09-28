package br.com.joule.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "alternativa")
public class Alternativa implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "alternativaSequence", sequenceName = "ALTERNATIVA_SEQ")
	private long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "resposta")
	private boolean resposta;

	public Alternativa() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isResposta() {
		return resposta;
	}

	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}
}