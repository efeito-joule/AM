package br.com.joule.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_SEJ_CURSO")
public class Curso implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cursoSequence", sequenceName = "CURSO_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cursoSequence")
	@Column(name="cd_curso")
	private long id;
	
	@Column(name="nm_curso", nullable=false,length=200)
	private String nome;
	
	@Column(name="ds_descricao", nullable=false)
	private String descricao;
	
	@OneToMany(mappedBy="curso", fetch=FetchType.LAZY)
	@Column(name="cd_aula")
	private List<Aula> aulas;

	public Curso() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
}