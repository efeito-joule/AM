package br.com.joule.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="T_SEJ_MATRICULA")
public class Matricula implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "matriculaSequence", sequenceName = "MATRICULA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matriculaSequence")
	@Column(name="cd_matricula")
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Calendar dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_termino")
	private Calendar dataTermino;
	
	@OneToOne
	@JoinColumn(name="cd_aluno")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="cd_curso")
	private Curso curso;

	public Matricula() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Aluno getUsuario() {
		return aluno;
	}

	public void setUsuario(Aluno usuario) {
		this.aluno = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}