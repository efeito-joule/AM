package br.com.joule.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_SEJ_HISTORICO")
@SequenceGenerator(name="seqHistorico", sequenceName="SEQ_HISTORICO",allocationSize=1)
@NamedStoredProcedureQuery(name = "proc", procedureName = "Ranking", parameters = {})
public class Historico implements Serializable {

	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(generator="seqHistorico", strategy=GenerationType.SEQUENCE)
	@Column(name="cd_historico") 
	private long id;
	
	@ManyToOne
	@JoinColumn(name="cd_aula")
	private Aula aula;
	
	@ManyToOne
	@JoinColumn(name="cd_aluno")
	private Aluno aluno;
	
	@Column(name="nr_erro")
	private int numErro;
	
	@Column(name="nr_acerto")
	private int numAcerto;
	
	@Column(name="nr_posAula")
	private int posicaoAula;
	
	@Column(name="nr_posTotal")
	private int posicaoTotal;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public int getNumErro() {
		return numErro;
	}

	public void setNumErro(int numErro) {
		this.numErro = numErro;
	}

	public int getNumAcerto() {
		return numAcerto;
	}

	public void setNumAcerto(int numAcerto) {
		this.numAcerto = numAcerto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPosicaoAula() {
		return posicaoAula;
	}

	public void setPosicaoAula(int posicaoAula) {
		this.posicaoAula = posicaoAula;
	}

	public int getPosicaoTotal() {
		return posicaoTotal;
	}

	public void setPosicaoTotal(int posicaoTotal) {
		this.posicaoTotal = posicaoTotal;
	}
	
	
}
