package br.com.joule.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.joule.entity.Questao;

@Entity
@Table(name = "T_SEJ_ALTERNATIVA")
public class Alternativa implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "alternativaSequence", sequenceName = "ALTERNATIVA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alternativaSequence")
	@Column(name="cd_alternativa")
	private long id;
	
	@Column(name="ds_descricao", nullable=false)
	private String descricao;
	
	@Column(name="vl_resposta", nullable=false)
	private boolean resposta;
	
	@ManyToOne()
	@JoinColumn(name="cd_questao")
	private Questao questao;

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
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
}