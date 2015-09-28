package br.com.joule.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "uf")
public class UF implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ufSequence", sequenceName = "UF_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ufSequence")
	private short id;
	
	@Column(name = "descricao")
	private String descricao;

	public UF() {
		super();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}