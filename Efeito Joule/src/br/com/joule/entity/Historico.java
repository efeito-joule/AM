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

@Entity
@Table(name="T_SEJ_HISTORICO")
public class Historico implements Serializable {

	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="seqHistorico", sequenceName="SEQ_HISTORICO",allocationSize=1)
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
	
	@Column(name="nr_posicao")
	private int posicao;

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

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
