package br.com.joule.entity;
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
@Table(name = "T_SEJ_TIPO_PESSOA")
public class TipoPessoa implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tipoPessoaSequence", sequenceName = "TIPO_PESSOA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoPessoaSequence")
	private short id;

	@Column(name = "descricao")
	private String descricao;

	public TipoPessoa() {
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